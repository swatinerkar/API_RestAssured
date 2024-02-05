package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.FileConstants;
import dateUtil.DateUtil;
import fileUtils.FileReader;

public final class ExtentsReportUtils {

    public ExtentsReportUtils(){}
    private static ExtentReports extent;

    public static ExtentTest getTest() {
        return test;
    }

    public static void setTest(ExtentTest test) {
        ExtentsReportUtils.test = test;
    }

    private static ExtentTest test;

    public static void initReport(){
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(FileReader.getInstance().REPORT+"_"+ DateUtil.getInstance().getDate("dd-MM-yyyy_ss-mm-HH"));
        extent.attachReporter(sparkReporter);
    }

    public static void tearDown(){
        extent.flush();
    }

    public static void createTest(String name) {
        test = extent.createTest(name);
    }

    public static void addAuthor(String[] authors){
        for(String author: authors)
            getTest().assignAuthor("Author:  "+author);
    }

    public static void addTestCategory(String[] testCategories){
        for(String testCategory: testCategories)
            getTest().assignCategory("Test Category:  "+testCategory);
    }

}
