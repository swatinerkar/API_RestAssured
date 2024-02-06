package listeners;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.*;
import reports.ExtentLogger;
import reports.ExtentsReportUtils;

public class TestListeners implements ITestListener, ISuiteListener {
    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
    @Override
    public void onTestStart(ITestResult result) {
        ExtentLogger.createTest(result.getName());
//        get the author and category of test case if mentioned at test level
        /*try {
            String[] authors = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).authors();
            ExtentsReportUtils.addAuthor(authors);
        }
        catch(NullPointerException e){
            ExtentLogger.warn("Framework Annotations: authors are not provided");
        }
        try {
            String[] categories = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).testCategories();
            ExtentsReportUtils.addTestCategory(categories);
        }
        catch (NullPointerException e){
            ExtentLogger.warn("Framework Annotations: testCategories are not provided");
        }*/
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getName()+": Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getMethod().getMethodName() +" : "+result.getThrowable()+" : "+result.getThrowable().getMessage());
    }

    @Override
    public void onStart(ISuite suite) {
        ExtentsReportUtils.initReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentsReportUtils.tearDown();
    }

}
