package random;

import com.github.javafaker.Faker;

/**
 * Third party library - server layer implementation
 * This is if in future there is change in faker methods we just need to change in this file.
 */
public class FakerUtil {


    private FakerUtil(){}
    private static final Faker faker = new Faker();

    static int getNumber(int startNumber, int endNumber){
        return faker.number().numberBetween(startNumber,endNumber);
    }

    static String getFirstName(){
        return faker.name().firstName();
    }

    static String getTitle(){
        return faker.name().title();
    }

    static String getFullName(){ return faker.name().fullName(); }

    static String getDeptName(){ return faker.job().field(); }
}
