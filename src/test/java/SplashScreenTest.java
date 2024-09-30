import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class SplashScreenTest extends AppiumConfig {

    @Test
    public void firstLaunch() {
        String version = new SplashScreen(driver).getTextVersion();
        Assert.assertTrue(version.contains("1.0.0"));
    }

    @Test
    public void splashScreenTime() {
        long expectedTime = 3000;
        SplashScreen splashScreen = new SplashScreen(driver);
        long startTime = System.currentTimeMillis();
        splashScreen.waitForSplashScreenToAppear();
        splashScreen.waitForSplashScreenToDisappear();
        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        System.out.println("SplashScreen time: " + resultTime + " ms.");
        Assert.assertTrue(resultTime <= expectedTime, "SplashScreen time exceeded expected limit!");
    }
}
