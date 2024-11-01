package com.digisphere.propertize.application.user.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptUtil {

    public static String execute(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
