package helpers;

public class Environment {
    // config is common for all the tests
    public final static String
            //
        url = System.getProperty("url", "https://facebook.com"),
        email = System.getProperty("email", "cheshi.mantu@gmail.com"),
        password = System.getProperty("password", "doNotKeepYourPasswordHere");
}

