package com.res.bean;

import java.util.Map;

public class LatLngForestResponse {
    private int gid;
    private String division;
    private String range;
    private String circle;
    private int range_id;
    private String sub_divisi;
    private double latitude;
    private double longitude;
    private Map<String, Object> remark;  // Use Map for empty or nested objects

    // Getters and Setters
    public int getGid() { return gid; }
    public void setGid(int gid) { this.gid = gid; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public String getRange() { return range; }
    public void setRange(String range) { this.range = range; }

    public String getCircle() { return circle; }
    public void setCircle(String circle) { this.circle = circle; }

    public int getRange_id() { return range_id; }
    public void setRange_id(int range_id) { this.range_id = range_id; }

    public String getSub_divisi() { return sub_divisi; }
    public void setSub_divisi(String sub_divisi) { this.sub_divisi = sub_divisi; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public Map<String, Object> getRemark() { return remark; }
    public void setRemark(Map<String, Object> remark) { this.remark = remark; }
}
