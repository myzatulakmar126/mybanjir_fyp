package com.example.mybanjir;

import java.util.HashMap;
import java.util.Map;

public class Admin {
    private String adminId;
    private String adEmail;
    private String adPswd;
    private String adrepass;

    public Admin() {}

    public Admin(String adminId,String adEmail, String adPswd, String adrepass) {
        this.adminId = adminId;
        this.adEmail = adEmail;
        this.adPswd = adPswd;
        this.adrepass = adrepass;
    }

    public String getAdminId() {return adminId;}
    public void setAdminId(String adminId) {this.adminId = adminId;}

    public String getAdEmail() {return adEmail;}
    public void setAdEmail(String adEmail) {this.adEmail = adEmail;}

    public String getAdPswd() {return adPswd;}
    public void setAdPswd(String adPswd) {this.adPswd = adPswd;}

    public String getAdrepass() {return adrepass;}
    public void setAdrepass(String adrepass) {this.adrepass = adrepass;}

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("Email", adEmail);

        return result;
    }

}


