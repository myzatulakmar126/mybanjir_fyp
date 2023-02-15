package com.example.mybanjir;

public class relief_shelter {
    private String relName;
    private String relAddress;
    private String relStatus;
    private String relNumPhone;
    private String dataImage;


    public relief_shelter(){};

   //constructor
    public relief_shelter(String relName, String relAddress, String relStatus, String relNumPhone, String dataImage) {
        this.relName = relName;
        this.relAddress = relAddress;
        this.relStatus = relStatus;
        this.relNumPhone = relNumPhone;
        this.dataImage = dataImage;
    }

    //getter
    public String getRelName() {
        return relName;
    }
    public String getRelAddress() {
        return relAddress;
    }
    public String getRelStatus() {
        return relStatus;
    }
    public String getRelNumPhone() {
        return relNumPhone;
    }
    public String getDataImage() {return dataImage;}

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public void setRelAddress(String relAddress) {
        this.relAddress = relAddress;
    }

    public void setRelStatus(String relStatus) {
        this.relStatus = relStatus;
    }

    public void setRelNumPhone(String relNumPhone) {
        this.relNumPhone = relNumPhone;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }
}
