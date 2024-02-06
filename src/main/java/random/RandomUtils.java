package random;

/**
 * Business layer on top of Faker Util
 * This class will help to implement business requirement
 * and this layer will force user to use methods present in this class instead of directly calling methods in FakerUtils class
 */
public final class RandomUtils {
    private RandomUtils(){}
    public static int getRandomNumber(){
        return FakerUtil.getNumber(200, 1000);
    }

    public static String getDeptName(){
        return FakerUtil.getDeptName();
    }


    public static String getFullName(){
        return FakerUtil.getFullName();
    }
    public static String getTitle(){
        return FakerUtil.getTitle();
    }
}
