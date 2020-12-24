package com.mobile.model;

import java.io.Serializable;

/**
 * @ClassName FaceInformation
 * @Description TODO 接口信息
 * @Author admini
 * @Date 2019/7/23 11:09
 * @Version 1.0
 */
public class FaceInformation implements Serializable{
    String id;
    String fasename;
    String faceclass;
    String facetype;
    String fasestatus;
    double cost;
    String ctime;

    public FaceInformation() {
    }

    public FaceInformation(String id, String fasename, String faceclass, String facetype, String fasestatus, double cost, String ctime) {
        this.id = id;
        this.fasename = fasename;
        this.faceclass = faceclass;
        this.facetype = facetype;
        this.fasestatus = fasestatus;
        this.cost = cost;
        this.ctime = ctime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFasename() {
        return fasename;
    }

    public void setFasename(String fasename) {
        this.fasename = fasename;
    }

    public String getFaceclass() {
        return faceclass;
    }

    public void setFaceclass(String faceclass) {
        this.faceclass = faceclass;
    }

    public String getFacetype() {
        return facetype;
    }

    public void setFacetype(String facetype) {
        this.facetype = facetype;
    }

    public String getFasestatus() {
        return fasestatus;
    }

    public void setFasestatus(String fasestatus) {
        this.fasestatus = fasestatus;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
