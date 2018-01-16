package com.ps.heat.entity;

import java.time.LocalDateTime;

/**
 * Created by xbc on 2016/4/28.
 */
public class HeatValveData {
    public static final int VALVE_OPENED = 0;
    public static final int VALVE_CLOSED = 1;
    public static final int VALVE_SCALE = 2;
    public static final int VALVE_ERROR = 3;
    private String valveAddr;       //阀门地址
    private String dataTime;        //数据时间
    private double setTemp;         //设置温度
    private double roomTemp;        //室内温度
    private double openHours;       //总累计开启时间
    private ValveStatus valveStatus;//阀门状态
    private int scale;              //开度值
    private double flowTemp;        //进水温度
    private double returnTemp;      //回水温度
    private double givenReturnTemp; //给定回水温度
    private String errList;         //故障列表

    public String getValveAddr() {
        return valveAddr;
    }

    public void setValveAddr(String valveAddr) {
        this.valveAddr = valveAddr;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public double getSetTemp() {
        return setTemp;
    }

    public void setSetTemp(double setTemp) {
        this.setTemp = setTemp;
    }

    public double getRoomTemp() {
        return roomTemp;
    }

    public void setRoomTemp(double roomTemp) {
        this.roomTemp = roomTemp;
    }

    public double getOpenHours() {
        return openHours;
    }

    public void setOpenHours(double openHours) {
        this.openHours = openHours;
    }

    public ValveStatus getValveStatus() {
        return valveStatus;
    }

    public void setValveStatus(ValveStatus valveStatus) {
        this.valveStatus = valveStatus;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public double getFlowTemp() {
        return flowTemp;
    }

    public void setFlowTemp(double flowTemp) {
        this.flowTemp = flowTemp;
    }

    public double getReturnTemp() {
        return returnTemp;
    }

    public void setReturnTemp(double returnTemp) {
        this.returnTemp = returnTemp;
    }

    public double getGivenReturnTemp() {
        return givenReturnTemp;
    }

    public void setGivenReturnTemp(double givenReturnTemp) {
        this.givenReturnTemp = givenReturnTemp;
    }

    public String getErrList() {
        return errList;
    }

    public void setErrList(String errList) {
        this.errList = errList;
    }

    @Override
    public String toString() {
        return "HeatValveData{" +
                "valveAddr='" + valveAddr + '\'' +
                ", dataTime=" + dataTime +
                ", setTemp=" + setTemp +
                ", roomTemp=" + roomTemp +
                ", openHours=" + openHours +
                ", valveStatus=" + valveStatus +
                ", scale=" + scale +
                ", flowTemp=" + flowTemp +
                ", returnTemp=" + returnTemp +
                ", givenReturnTemp=" + givenReturnTemp +
                ", errList='" + errList + '\'' +
                '}';
    }
}
