package kz.smartideagroup.pillikan.common.remote.exceptions_util;

public class ExceptionModel {
    private int code;
    private int status;
    private String message;
    public int code() {
        return code;
    }
    public int status() {
        return status;
    }
    public String message() {
        return message;
    }
}
