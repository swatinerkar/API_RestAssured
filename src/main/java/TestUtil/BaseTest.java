package TestUtil;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import requestUtils.Request;

public class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void setup(){
        Request.init().clearAll();
        Request.init().setBaseUrl();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        Request.init().clearAll();
    }
}
