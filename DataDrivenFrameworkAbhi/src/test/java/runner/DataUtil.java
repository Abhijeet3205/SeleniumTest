package runner;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataUtil {
	
	public Map<String,String> loadClassMethods() throws IOException, ParseException 
	{
		Map<String,String> classMethodMap = new HashMap<String, String>();
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\jsons\\classmethods.json";
//		System.out.println(path);
		FileReader reader = new FileReader(path);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(reader);
//		System.out.println(json.toJSONString());
		JSONArray classDetails = (JSONArray) json.get("classdetails");
		for(int i =0; i<classDetails.size();i++) 
		{
			JSONObject classDetail = (JSONObject) classDetails.get(i);
//			System.out.println(classDetail.toJSONString());
			String className = (String) classDetail.get("class");
			JSONArray methods = (JSONArray) classDetail.get("methods");
			for(int y=0; y<methods.size();y++) 
			{
				String methodName = (String) methods.get(y);
//				System.out.println(methodName);
				classMethodMap.put(methodName, className);
			}
		}		
//		System.out.println(classMethodMap);
		return classMethodMap;
	}
	
	public int getTestDataSets(String filePath, String dataFlag) throws IOException, ParseException 
	{
		int dataSets=0;
		FileReader reader = new FileReader(filePath);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(reader);
		JSONArray dataDetails = (JSONArray) json.get("testdata");
		for(int dSetId=0;dSetId<dataDetails.size();dSetId++) 
		{
			JSONObject testData = (JSONObject) dataDetails.get(dSetId);
			String flag = (String) testData.get("flag");
			if(flag.equals(dataFlag)) 
			{
				JSONArray dataSet = (JSONArray) testData.get("data");
				dataSets=dataSet.size();
				return dataSets;
			}
		}
		
		return dataSets;
	}
	
	public JSONObject getTestData(String filePath, String dataFlag, int iteration) throws IOException, ParseException
	{
		int dataSets=0;
		FileReader reader = new FileReader(filePath);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(reader);
		JSONArray dataDetails = (JSONArray) json.get("testdata");
		for(int dSetId=0;dSetId<dataDetails.size();dSetId++) 
		{
			JSONObject testData = (JSONObject) dataDetails.get(dSetId);
			String flag = (String) testData.get("flag");
			if(flag.equals(dataFlag)) 
			{
				JSONArray dataSet = (JSONArray) testData.get("data");
				JSONObject data = (JSONObject) dataDetails.get(iteration);
				return data;
			}
		}
		return null;
	}

}
