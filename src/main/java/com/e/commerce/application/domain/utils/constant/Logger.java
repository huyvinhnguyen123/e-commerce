package com.e.commerce.application.domain.utils.constant;

public class Logger {
    private static final String LOGIN_SUCCESS = "Authenticate Successfully";
    private static final String LOGIN_FAIL = "Authenticate Failure";

    private Logger() {}

    public static String loginSuccess() {
        return LOGIN_SUCCESS;
    }
    public static String loginFail() {
        return LOGIN_FAIL;
    }

    public static String requestApi(String name) {
        return name + "is requesting...";
    }

    public static String findListObjectSuccess(String name) {
        return "List " + name + " Successfully Found";
    }

    public static String createListObjectSuccess(String name) {
        return "List " + name + " Successfully Created";
    }

    public static String createListObjectFail(String name) {
        return "List " + name + " Failure Created";
    }

    public static String findObjectSuccess(String name) {
        return name + " Successfully Found";
    }

    public static String createObjectSuccess(String name) {
        return name + " Successfully Created";
    }

    public static String updateObjectSuccess(String name) {
        return name + " Successfully Updated";
    }

    public static String deleteObjectSuccess(String name) {
        return name + " Successfully Deleted";
    }

    public static String createObjectFail(String name) {
        return name + " Failure Created";
    }

    public static String updateObjectFail(String name) {
        return name + " Failure Updated";
    }

    public static String deleteObjectFail(String name) {
        return name + " Failure Deleted";
    }
}
