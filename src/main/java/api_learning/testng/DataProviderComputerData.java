package api_learning.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.computer.ComputerData;
import test_data.computer.DataObjectBuilder;

public class DataProviderComputerData {

    @Test(dataProvider = "computerData")
    public void testDataPovider(ComputerData computerData){
        System.out.println(computerData);

    }

    @DataProvider
    public ComputerData[] computerData(){
        String fileLocation = "/src/main/java/test_data/computer/CheapComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation,ComputerData[].class);
    }


}