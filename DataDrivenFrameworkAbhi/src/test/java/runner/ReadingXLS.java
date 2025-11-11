package runner;

import org.json.simple.JSONObject;

import util.Xls_Reader;

public class ReadingXLS {

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\jsons\\xls_data\\excelForTesting.xlsx";
//		System.out.println(filePath);
		Xls_Reader xls = new Xls_Reader(filePath);
//		System.out.println(xls.getColumnCount("DealsSuite"));
//		System.out.println(xls.getColumnCountByRownum("DealsSuite",1));		
		String sheetName = "DealsSuite";
		String flagName = "deletedeal";		
//		JSONObject data= new  ReadingXLS().getTestData(sheetName, flagName, 2, filePath);
//		System.out.println(data);
//		int y = new  ReadingXLS().getTestDataSets(filePath, flagName, sheetName);
//		System.out.println("y: "+y);
		
	}
	
	public JSONObject getTestData(String sheetName, String flagName, int iterationNumber, String xlsFilePath) 
	{
		int FlagRowNum = 0;
		Xls_Reader xls = new Xls_Reader(xlsFilePath);
		for(int i=1; i<=xls.getRowCount(sheetName);i++) 
		{
			String cellData=xls.getCellData(sheetName, 0, i);
			if(cellData.equals(flagName)) 
			{
				FlagRowNum = i;
				break;
			}
		}		
		int colNameRowNum = FlagRowNum + 1;
		int dataStartRowNum = FlagRowNum + 2;
		int colCount = xls.getColumnCountByRownum(sheetName,colNameRowNum);
		int dataEndRowNum = 0;
		int z = dataStartRowNum;
		while(true) 
		{
			String cellData=xls.getCellData(sheetName, 0, z);
			if(cellData.equals("")) 
			{
				dataEndRowNum= z;
				break;
			}
			z++;
		}
		dataEndRowNum = dataEndRowNum - 1;
		System.out.println("dataEndRowNum: "+dataEndRowNum);
//		int iterationNumber = 2;
		int index = 1;
		iterationNumber=iterationNumber;
		for(int i=dataStartRowNum; i<=dataEndRowNum; i++) 
		{
			if(index == iterationNumber) 
			{
				JSONObject json = new JSONObject(); 
				for(int y= 0;y<colCount;y++) 
				{
					String colName = xls.getCellData(sheetName, y, colNameRowNum);
					String cellData=xls.getCellData(sheetName, y, i);
//					System.out.println(colName+" "+cellData);
					json.put(colName, cellData);
				}
//				break;
				return json;
			}
			else 
			{
				index++;
			}			
		}		
		return new JSONObject();
	}
	
	public int getTestDataSets(String xlsFilePath, String dataFlag, String sheetName) 
	{
		int FlagRowNum = 0;
		Xls_Reader xls = new Xls_Reader(xlsFilePath);
		for(int i=1; i<=xls.getRowCount(sheetName);i++) 
		{
			String cellData=xls.getCellData(sheetName, 0, i);
			if(cellData.equals(dataFlag)) 
			{
				FlagRowNum = i;
				break;
			}
		}		
		int colNameRowNum = FlagRowNum + 1;
		int dataStartRowNum = FlagRowNum + 2;
		int colCount = xls.getColumnCountByRownum(sheetName,colNameRowNum);
		int dataEndRowNum = 0;
		int z = dataStartRowNum;
		while(true) 
		{
			String cellData=xls.getCellData(sheetName, 0, z);
			if(cellData.equals("")) 
			{
				dataEndRowNum= z;
				break;
			}
			z++;
		}
		dataEndRowNum = dataEndRowNum - 1;
		int TotalRows = dataEndRowNum - colNameRowNum;
		return TotalRows; 		
	}

}
