package test_data;

import com.google.gson.Gson;
import test_data.computer.ComputerData;
import test_data.computer.DataObjectBuilder;

import java.util.Arrays;

public class TestGSON {

    //Convert from JSON to Java Object

    //Convert form Java Object to GSON

    public static void main(String[] args) {
 //       exploreGsonFeature();
        testDataBuilder();

    }

    private static void testDataBuilder() {
        String fileLocation = "/src/test/java/test_data/computer/CheapComputerDataList.json";
        ComputerData[] computerData= DataObjectBuilder.buildDataObjectFrom(fileLocation,ComputerData[].class);//tra ve array
        System.out.println(Arrays.toString(computerData));
    }

    private static void exploreGsonFeature() {
        String JSONString = " {\n" +
                "    \"processorType\": \"2.2GHz\",\n" +
                "    \"ram\": \"8GB\",\n" +
                "    \"os\": \"Ubuntu\",\n" +
                "    \"hdd\": \"400 GB\",\n" +
                "    \"software\": \"Microsoft Office\"\n" +
                "  }";
        Gson gson = new Gson();
        ComputerData computerData=gson.fromJson(JSONString, ComputerData.class);
        System.out.println(computerData);

        System.out.println(gson.toJson(computerData));
    }
}
