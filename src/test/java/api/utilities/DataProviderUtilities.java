package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderUtilities {

	@DataProvider(name="data")
	public String [][] getData() throws IOException{
		String path="C:\\Users\\Dell\\eclipse-workspace\\Project3_FrameWork_RestAssured\\testDatas\\restData.xlsx";
		
		XLUtilities xl=new XLUtilities(path);
		
		int totalrows=xl.getRowCount("Sheet1");
		int totalcell=xl.getCellCount("Sheet1", 1);
		
		String logIndata[][]=new String[totalrows][totalcell];
		for(int r=1;r<=totalrows;r++) {
			for(int c=0;c<totalcell;c++) {
				logIndata[r-1][c]=xl.getCellData("Sheet1", r, c);
			}
		}
		
		return logIndata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
		
		String path="C:\\Users\\Dell\\eclipse-workspace\\Project3_FrameWork_RestAssured\\testDatas\\restData.xlsx";
		XLUtilities xl=new XLUtilities(path);
		
		int rownum=xl.getRowCount("Sheet1");
		
		String apidata[]=new String[rownum];
		
		for(int i=1; i<=rownum;i++) {
			apidata[i-1]=xl.getCellData("Sheet1", i, 1);
		}
		return apidata;
		
	}
}
