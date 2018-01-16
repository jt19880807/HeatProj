package com.ps.heat.entity;

import java.time.LocalDateTime;

/**
 * Created by xbc on 2016/4/28.
 */
public class HeatMeterData {
    private String meterAddr;   //热量表地址
    private String dataTime;    //数据时间
    private double cuHeat;      //累计热量
    private double power;       //热功率
    private double volume;      //累计流量
    private double flow;        //瞬时流速
    private double flowTemp;    //进水温度
    private double returnTemp;  //回水温度
    private int hours;          //工作时间
    private boolean underVoltage;   //是否欠压
    private String errList;     //故障列表，以|分割

    public String getMeterAddr() {
        return meterAddr;
    }

    public void setMeterAddr(String meterAddr) {
        this.meterAddr = meterAddr;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public double getCuHeat() {
        return cuHeat;
    }

    public void setCuHeat(double cuHeat) {
        this.cuHeat = cuHeat;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public boolean isUnderVoltage() {
        return underVoltage;
    }

    public void setUnderVoltage(boolean underVoltage) {
        this.underVoltage = underVoltage;
    }

    public String getErrList() {
        return errList;
    }

    public void setErrList(String errList) {
        this.errList = errList;
    }

    @Override
    public String toString() {
        return "HeatMeterData{" +
                "meterAddr='" + meterAddr + '\'' +
                ", dataTime=" + dataTime +
                ", cuHeat=" + cuHeat +
                ", power=" + power +
                ", volume=" + volume +
                ", flow=" + flow +
                ", flowTemp=" + flowTemp +
                ", returnTemp=" + returnTemp +
                ", hours=" + hours +
                ", underVoltage=" + underVoltage +
                ", errList='" + errList + '\'' +
                '}';
    }
}
