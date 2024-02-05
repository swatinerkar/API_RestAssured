package dateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static DateUtil dateUtil;
    private DateUtil(){}
    public static DateUtil getInstance(){
        if(dateUtil == null){
            synchronized (DateUtil.class){
                if(dateUtil == null)
                    dateUtil = new DateUtil();
            }
        }
        return dateUtil;
    }

    public String getDate(String dateFormat){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String dateInString = sdf.format(date);
        return dateInString;
    }
}
