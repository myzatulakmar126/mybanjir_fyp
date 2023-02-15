package com.example.mybanjir;

public class sos {
    private String sosId;
  private String fvName;
  private String typeSOS;
  private String sosAddress;
  private String sosStatus;
  private String victimRemark;
  private String rescueRemark;

  public sos(){}

    public sos(String sosId, String fvName, String typeSOS, String sosAddress, String sosStatus, String victimRemark, String rescueRemark) {
      this.sosId = sosId;
      this.fvName = fvName;
        this.typeSOS = typeSOS;
        this.sosAddress = sosAddress;
        this.sosStatus = sosStatus;
        this.victimRemark = victimRemark;
        this.rescueRemark = rescueRemark;
    }

    public String getSosId() {return sosId;}
    public void setSosId(String sosId){this.sosId = sosId;}

    public String getFvName() {return fvName;}

    public void setFvName(String fvName) {
        this.fvName = fvName;
    }

    public String getTypeSOS() {
        return typeSOS;
    }

    public void setTypeSOS(String typeSOS) {
        this.typeSOS = typeSOS;
    }

    public String getSosAddress() {
        return sosAddress;
    }

    public void setSosAddress(String sosAddress) {
        this.sosAddress = sosAddress;
    }

    public String getSosStatus() {
        return sosStatus;
    }

    public void setSosStatus(String sosStatus) {
        this.sosStatus = sosStatus;
    }

    public String getVictimRemark() {
        return victimRemark;
    }

    public void setVictimRemark(String victimRemark) {
        this.victimRemark = victimRemark;
    }

    public String getRescueRemark() {
        return rescueRemark;
    }

    public void setRescueRemark(String rescueRemark) {
        this.rescueRemark = rescueRemark;
    }
}
