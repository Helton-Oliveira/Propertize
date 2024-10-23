package com.digisphere.propertize.user.utils;

public class CorporateEmailCreator {

    public static String create(String collaboratorName) {
        var email = collaboratorName.replace(" ", ".");

        return email.toLowerCase().concat("@propertize.com");
    };
}
