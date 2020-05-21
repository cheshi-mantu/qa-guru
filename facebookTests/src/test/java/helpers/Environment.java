package helpers;

public class Environment {
    // config is common for all the tests
    public final static String
            //
        url = System.getProperty("url", "http://facebook.com"),
        email = System.getProperty("email", "cheshi.mantu@gmail.com"),
        password = System.getProperty("password", "j3qq4h7h2v2hch4");
}

