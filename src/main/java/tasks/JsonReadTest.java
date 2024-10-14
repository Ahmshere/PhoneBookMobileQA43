/*
package tasks;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertNotNull;


public class JsonReadTest
{
    private static HashMap<String, String> job1 = new HashMap<>();

    @BeforeClass
    public static void setUp()
    {
        job1.put("clickId", "clickId");
        job1.put("country", "country");
        job1.put("date", "day");
        job1.put("money", "money");
    }

    @Test
    public void verifyContentIsAvailable()
    {
        assertNotNull(readJson("testJob1.json"));
    }

    @Test
    public void verifyJsonObjectWasParsedCorrectly()
    {
        String jsonAsString = readJson("testJob1.json");
        assertNotNull(jsonAsString);
        JsonArray jsonArray = parseJsonToObject(jsonAsString);
        assert jsonArray.isJsonArray();
    }

    @Test
    public void verifyContentExistsInDB()
    {
        String job1ResponseAsString = readJson("testJob1.json");
        JsonArray job1AsJsonArray = parseJsonToObject(job1ResponseAsString);
        List<RawData> rawDataList = insertToDb(1, job1AsJsonArray);
        rawDataList.forEach(System.out::println);
        assertNotNull(rawDataList);
    }

    private List<RawData> insertToDb(int jobId, JsonArray jsonArray)
    {
        List<RawData> rawDataList = new ArrayList<>();
        jsonArray.forEach(e -> {
            String clickId = getJsonElementByPath(null, e, job1.get("clickId")).getAsString();
            String day = getJsonElementByPath(null, e, job1.get("date")).getAsString();
            String money = getJsonElementByPath(null, e, job1.get("money")).getAsString();
            String country = getJsonElementByPath(null, e, job1.get("country")).getAsString();
            RawData rawData = new RawData(jobId, Long.parseLong(clickId), country, day, Double.parseDouble(money));
            rawDataList.add(rawData);
        });
        return rawDataList;
    }

    private JsonArray parseJsonToObject(String jsonString)
    {
        return new JsonParser().parse(jsonString).getAsJsonArray();
    }

    private String readJson(String fileName)
    {
        String content = null;
        ClassLoader classLoader = RawDataTest.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found! ");
            }
            else {
                content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class RawData
    {
        Integer jobId;
        Long clickId;
        String country;
        String date;
        Double money;

        @Override
        public String toString()
        {
            return "RawData{" + "jobId=" + jobId + ", clickId=" + clickId + ", country='" + country + '\'' + ", date='" + date + '\'' + ", money=" + money + '}';
        }
    }

}*/
