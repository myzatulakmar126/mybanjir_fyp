package com.example.mybanjir;

import java.util.HashMap;
import java.util.Map;

public class rescuer {
    private String resId;
    private String resEmail;
    private String resPswd;
    private String resrepass;
    private String resIcNum;
    private String resAddress;
    private String resPhoneNum;

    public rescuer(){}

    public rescuer(String resId, String resEmail, String resPswd, String resrepass, String resIcNum, String resAddress, String resPhoneNum) {
        this.resId = resId;
        this.resEmail = resEmail;
        this.resPswd = resPswd;
        this.resrepass = resrepass;
        this.resIcNum = resIcNum;
        this.resAddress = resAddress;
        this.resPhoneNum = resPhoneNum;
    }

    public rescuer(String resId, String resEmail, String resPswd, String resrepass) {
        this.resId = resId;
        this.resEmail = resEmail;
        this.resPswd = resPswd;
        this.resrepass = resrepass;
    }

    public String getResId() {return resId;}
    public void setResId(String resId) {this.resId = resId;}

    public String getResEmail() {return resEmail;}
    public void setResEmail(String resEmail) {this.resEmail = resEmail;}

    public String getResPswd() {return resPswd;}
    public void setResPswd(String resPswd) {this.resPswd = resPswd;}

    public String getResrepass() {return resrepass;}
    public void setResrepass(String resrepass) {this.resrepass = resrepass;}

    public String getResIcNum() {return resIcNum;}
    public void setResIcNum(String resIcNum) {this.resIcNum = resIcNum;}

    public String getResAddress() {return resAddress;}
    public void setResAddress(String resAddress) {this.resAddress = resAddress;}

    public String getResPhoneNum() {return resPhoneNum;}
    public void setResPhoneNum(String resPhoneNum) {this.resPhoneNum = resPhoneNum;}

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("Email", resEmail);

        return result;
    }

}
