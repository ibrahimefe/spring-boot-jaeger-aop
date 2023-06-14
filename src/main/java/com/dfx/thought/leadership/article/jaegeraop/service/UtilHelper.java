package com.dfx.thought.leadership.article.jaegeraop.service;

import java.util.Date;

public class UtilHelper {

    private UtilHelper() {
    }

    public static String getDateNow() {
        return  new Date(System.currentTimeMillis()).toString();
    }
}
