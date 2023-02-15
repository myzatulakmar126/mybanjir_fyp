package com.example.mybanjir;

public class fv {

    private String fvEmail;
    private String fvPswd;
    private String fvpass;
    private String fvIcNum;
    private String fvAddress;

    public fv(){}

    public fv(String fvEmail, String fvPswd, String fvpass, String fvIcNum, String fvAddress) {
        this.fvEmail = fvEmail;
        this.fvPswd = fvPswd;
        this.fvpass = fvpass;
        this.fvIcNum = fvIcNum;
        this.fvAddress = fvAddress;
    }

    public String getFvEmail() {return fvEmail;}

    public String getFvPswd() {return fvPswd;}

    public String getFvpass() {return fvpass;}

    public String getFvIcNum() {return fvIcNum;}

    public String getFvAddress() {return fvAddress;}


    public void setFvEmail(String fvEmail) {this.fvEmail = fvEmail;}

    public void setFvPswd(String fvPswd) {this.fvPswd = fvPswd;}

    public void setFvpass(String fvpass) {this.fvpass = fvpass;}

    public void setFvIcNum(String fvIcNum) {this.fvIcNum = fvIcNum;}

    public void setFvAddress(String fvAddress) {this.fvAddress = fvAddress;}
}
