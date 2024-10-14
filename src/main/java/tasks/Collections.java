package tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Collections {

    static Map<Integer, String> myMap = new HashMap();

    public void setMyMap(Map<Integer, String> myMap) {
        this.myMap = myMap;
    }
    public static void method01(){
        myMap.put(1,"testData12");
        myMap.put(2,"testData2");
        myMap.put(3,"testData3");
        myMap.put(4,"testData");
        myMap.put(5,"testData");
    }
    public static void getAllData(){
        System.out.println("Get by key 1 "+myMap.containsKey(1));
        System.out.println("Get by key 1 "+myMap.get(1));

        System.out.println("Get by value: "+myMap.containsValue("testData2"));
        System.out.println("Get by value: "+myMap.get(4));
        System.out.println("Get by value duplicate : "+myMap.containsValue("testData"));
    }

    public static void main(String[] args) {
        method01();
        getAllData();
        System.out.println("******************************************");
        HashMap<String, Integer> phoneBook = new HashMap<>();

        phoneBook.put("John", 123456);
        phoneBook.put("Jane", 789012);
        phoneBook.put("Tom", 345678);
        phoneBook.put("Mary", 901234);
        System.out.println("GET 1 "+phoneBook.get("John"));

        Integer johnPhoneNumber = phoneBook.get("John");
        System.out.println("John's phone number: " + johnPhoneNumber);

        if (phoneBook.containsKey("Jane")) {
            System.out.println("Jane is in the phone book.");
        }

        if (phoneBook.containsValue(345678)) {
            System.out.println("Phone number 345678 is in the phone book.");
        }

        for (HashMap.Entry<String, Integer> entry : phoneBook.entrySet()) {
            System.out.println("Name: " + entry.getKey() + ", Phone Number: " + entry.getValue());
        }

        phoneBook.remove("Tom");
        System.out.println("Phone book after removing Tom: " + phoneBook);
    }




        public static String getLoadedData(WebDriver driver) {
            WebElement loadButton = driver.findElement(By.id("load-button"));
            loadButton.click();
            try{
                WebDriverWait wait = new WebDriverWait(driver, 1);
                wait.until(ExpectedConditions.elementToBeClickable(loadButton));
                return driver.findElement(By.id("content")).getAttribute("innerHTML");
            }catch (Exception e){
                return null;
            }    }


}
