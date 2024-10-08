package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppiumConfig {

    public static AppiumDriver<MobileElement> driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554"); // Pix6
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("appPackage", "com.sheygam.contactapp");
        capabilities.setCapability("appActivity", ".SplashActivity");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\PROJECTS\\APK\\contacts-android.apk");
        driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }
    @AfterMethod
    public void tearDown(){
        if(driver != null){driver.quit();}

    }

    public void swicthDataAndWiFi(boolean value) throws IOException, InterruptedException {
        String[] mobileDataCommand = {"adb", "shell", "svc", "data", value ? "enable" : "disable"};
        String[] wifiCommandSvc = {"adb", "shell", "svc", "wifi", value ? "enable" : "disable"};// Old versions
        String[] wifiCommandCmd = {"adb", "shell", "cmd", "wifi", "set-wifi-enabled", value ? "enabled" : "disabled"};
        String[] wifiCommandBroadcast = {"adb", "shell", "am", "broadcast", "-a", "io.appium.settings.wifi", "--es", "setstatus", value ? "enable" : "disable"};
        String[] wifiCommandSettings = {"adb", "shell", "settings", "put", "global", "wifi_on", value ? "1" : "0"};

        // Выполнение всех команд (или только необходимых в зависимости от устройства)
        executeCommand(mobileDataCommand);
        executeCommand(wifiCommandSvc);
        executeCommand(wifiCommandCmd);
        executeCommand(wifiCommandBroadcast);
        executeCommand(wifiCommandSettings);

    }
    //*************WiFi and DATA************
    public void executeCommand(String[]command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        Process process = processBuilder.start();
        process.waitFor();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String line;
            while((line= reader.readLine()) != null){
                System.out.println(line);
            }
        }
    }









}
