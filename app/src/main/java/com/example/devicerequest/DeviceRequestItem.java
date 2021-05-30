package com.example.devicerequest;

public class DeviceRequestItem {
    private String name;
    private String intent;
    private String code;
    private String subject;

    public DeviceRequestItem(String name, String intent, String code, String subject) {
        this.name = name;
        this.intent = intent;
        this.code = code;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public String getIntent() {
        return intent;
    }

    public String getCode() {
        return code;
    }

    public String getSubject() {
        return subject;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
