package fileUtils;

import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public final class FilesUtil {
    private FilesUtil(){}
    @SneakyThrows
    public static String readJsonAndGetAsString(String filePath) {
        return new String(Files.readAllBytes(Paths.get(FileReader.getInstance().REQUEST_BODY_PATH+filePath)));
    }

    public static JSONObject getJsonObjectFromString(String jsonInString){
        return new JSONObject(jsonInString);
    }

    @SneakyThrows
    public static void saveJsonResponseInFile(String filePath, Response response){
        Files.write(Paths.get(filePath), response.asByteArray());
    }
}
