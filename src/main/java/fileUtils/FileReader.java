package fileUtils;

public class FileReader {

    private static final FileReader fileReader = new FileReader();

    public static FileReader getInstance() {
        return fileReader;
    }

    public final String SCHEMA_PATH = System.getProperty("user.dir")+"/src/test/resources/Schemas";
    public final String REPORT = "reports/extentReports/report.html";

}
