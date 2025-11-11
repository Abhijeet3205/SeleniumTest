package runner;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRunner {

	public static void main(String[] args) throws IOException, ParseException {
		Map<String, String> classMethods = new DataUtil().loadClassMethods();
		System.out.println("classMethods " + classMethods);
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\jsons\\testconfig.json";
		FileReader reader = new FileReader(path);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(reader);
		String parallelSuites = (String) json.get("parallelsuites");
		TestNGRunner testNG = new TestNGRunner(Integer.parseInt(parallelSuites));

		JSONArray testSuites = (JSONArray) json.get("testsuites");
		for (int i = 0; i < testSuites.size(); i++) {
			JSONObject testDetailJSON = (JSONObject) testSuites.get(i);
			String runmode = (String) testDetailJSON.get("runmode");
			String name = (String) testDetailJSON.get("name");
			
			// Uncomment below in case of getting data from json file
			String testdatajsonfile = System.getProperty("user.dir") + "\\src\\test\\resources\\jsons\\"+ (String) testDetailJSON.get("testdatajsonfile");
			
			//use below when extracting data from excel file
			//String testdatajsonfile = System.getProperty("user.dir") + "\\src\\test\\resources\\jsons\\"+ (String) testDetailJSON.get("testdataxlsfile");
			
			String suitfilename = (String) testDetailJSON.get("suitfilename");
			String paralleltests = (String) testDetailJSON.get("paralleltests");
			if (runmode.equals("Y")) {
				boolean pTests = false;
				if (paralleltests.equals("Y")) {
					pTests = true;
				}
				testNG.createSuite(name, pTests);
				testNG.addListener("listener.MyTestNGListener");

				String suitePath = System.getProperty("user.dir") + "\\src\\test\\resources\\jsons\\" + suitfilename;
				System.out.println(suitePath);
				FileReader readerSuite = new FileReader(suitePath);
				JSONParser suiteParser = new JSONParser();
				JSONObject suiteJson = (JSONObject) suiteParser.parse(readerSuite);
				System.out.println(suiteJson.toJSONString());

				JSONArray suiteTestCases = (JSONArray) suiteJson.get("testcases");
				for (int sTID = 0; sTID < suiteTestCases.size(); sTID++) {
					JSONObject suiteTestCase = (JSONObject) suiteTestCases.get(sTID);

					String tName = (String) suiteTestCase.get("name");
					JSONArray parameterNames = (JSONArray) suiteTestCase.get("parameternames");
					JSONArray executions = (JSONArray) suiteTestCase.get("executions");
					for (int eId = 0; eId < executions.size(); eId++) {
						JSONObject testCase = (JSONObject) executions.get(eId);
						String executionname = (String) testCase.get("executionname");
						String tRunMode = (String) testCase.get("runmode");
						String dataflag = (String) testCase.get("dataflag");
						// now we have to calculate the sets of data
						int dataSets = new DataUtil().getTestDataSets(testdatajsonfile, dataflag); // Uncomment for reading data from  json
						//int dataSets = new  ReadingXLS().getTestDataSets(testdatajsonfile, dataflag, name.replaceAll(" ", ""));
						System.out.println("dataSets + " + dataSets);
						if (tRunMode != null && tRunMode.equals("Y")) {
							for (int dSId = 0; dSId < dataSets; dSId++) {
								JSONArray parametervalues = (JSONArray) testCase.get("parametervalues");
								JSONArray methods = (JSONArray) testCase.get("methods");

								// add to testNG
								testNG.addTest(tName + " - " + executionname + "It." +(dSId+1));
								for (int pId = 0; pId < parameterNames.size(); pId++) {
									testNG.addTestParameter((String) parameterNames.get(pId),
											(String) parametervalues.get(pId));
								}
								testNG.addTestParameter("dataflag", dataflag);
								testNG.addTestParameter("datafilePath",testdatajsonfile);
								testNG.addTestParameter("iteration",String.valueOf(dSId) );
								testNG.addTestParameter("suitename",name.replaceAll(" ", "") ); // only for xlsx

								List<String> includedMethods = new ArrayList<String>();
								for (int mId = 0; mId < methods.size(); mId++) {
									String method = (String) methods.get(mId);
									String methodClass = classMethods.get(method);
									System.out.println("++ " + method + " - " + methodClass);
									if (mId == methods.size() - 1 || !((String) classMethods.get((String) methods.get(mId + 1))).equals(methodClass)) {
										// if it comes here this means that method is from different class
										includedMethods.add(method);
										testNG.addTestClass(methodClass, includedMethods);
										includedMethods = new ArrayList<String>();
									} else {
										// if it comes here this means that method is from same class
										includedMethods.add(method);
									}
								}
							}
						}

					}

				}
				testNG.run();
			}
		}
	}

}
