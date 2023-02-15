package com.example.mybanjir;

import java.util.HashMap;
import java.util.Map;

public class user {
    private String key;
    private String userEmail;
    private String userPswd;
    private String repass;
    private String userRole;

    public user(){}

    public user(String key, String userEmail, String userPswd, String repass, String userRole){
        this.key = key;
        this.userEmail = userEmail;
        this.userPswd = userPswd;
        this.repass = repass;
        this.userRole = userRole;
    }
    public user(String userEmail, String userPswd, String repass, String userRole){
        this.userEmail = userEmail;
        this.userPswd = userPswd;
        this.repass = repass;
        this.userRole = userRole;
    }

    public String getKey() {return key;}
    public String getUserName() {return userEmail;}

    public String getUserPswd() {return userPswd;}

    public String getRepass() {return repass;}

    public String getUserRole() {return userRole;}

    public void setKey(String key){
        this.key = key;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Map<String, Object>toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("Email", userEmail);

        return result;
    }

}
