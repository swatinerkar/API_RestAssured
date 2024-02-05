package reports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public final class ExtentLogger {
    private ExtentLogger(){}

    public static void pass(String message){
        ExtentsReportUtils.getTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
    }

    public static void fail(String message){
        ExtentsReportUtils.getTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    }

    public static void info(String message){
        ExtentsReportUtils.getTest().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
    }

    public static void warn(String message){
        ExtentsReportUtils.getTest().warning(MarkupHelper.createLabel(message, ExtentColor.ORANGE));
    }

    public static void logResponse(String message){
        info("RESPONSE: ");
        ExtentsReportUtils.getTest().pass(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
    }

    public static void logRequest(RequestSpecification requestSpecification){
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
        info("REQUEST: ");
        query.getHeaders().forEach(e-> ExtentsReportUtils.getTest().info(e.getName()+":"+e.getValue()));
        ExtentsReportUtils.getTest().pass(MarkupHelper.createCodeBlock(query.getBody(), CodeLanguage.JSON));
    }

    public static void createTest(String name) {
        ExtentsReportUtils.createTest(name);
    }
}