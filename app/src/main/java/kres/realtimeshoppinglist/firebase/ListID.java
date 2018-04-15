package kres.realtimeshoppinglist.firebase;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ListID {

    public static String generate() {
        SecureRandom random = new SecureRandom();
        String fullRandomString = new BigInteger(32, random).toString(32);

        if (fullRandomString.length() >= 6) {
            fullRandomString = fullRandomString.substring(0, 6);
        }

        return fullRandomString.toUpperCase();
    }

}
