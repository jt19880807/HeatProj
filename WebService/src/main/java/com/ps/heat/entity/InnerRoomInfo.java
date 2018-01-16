package com.ps.heat.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xbc on 2016/4/28.
 */
public class InnerRoomInfo {
    private int roomId;         //������
    private String roomCode;    //�������
    private double roomArea;    //�������
    private double heatArea;    //��ů���
    private String meterAddr;   //�������ַ
    private String valveAddr;   //���ŵ�ַ
    private boolean heatSupplied;//�Ƿ�ů

    private int buildNum;       //¥��
    private int unitNum;        //��Ԫ��
    private int roomNum;        //����� 101
    private int valveNetNum;    //�����
    private int valveCollNum;   //��������
    private int meterNetNum;    //�����
    private int meterCollNum;   //��������
    private int meterProtNum;   //������Э���
    private int valveProtNum;   //����Э���

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public double getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(double roomArea) {
        this.roomArea = roomArea;
    }

    public double getHeatArea() {
        return heatArea;
    }

    public void setHeatArea(double heatArea) {
        this.heatArea = heatArea;
    }

    public String getMeterAddr() {
        return meterAddr;
    }

    public void setMeterAddr(String meterAddr) {
        this.meterAddr = meterAddr;
    }

    public String getValveAddr() {
        return valveAddr;
    }

    public void setValveAddr(String valveAddr) {
        this.valveAddr = valveAddr;
    }

    public boolean isHeatSupplied() {
        return heatSupplied;
    }

    public void setHeatSupplied(boolean heatSupplied) {
        this.heatSupplied = heatSupplied;
    }

    public int getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(int buildNum) {
        this.buildNum = buildNum;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public int getValveNetNum() {
        return valveNetNum;
    }

    public void setValveNetNum(int valveNetNum) {
        this.valveNetNum = valveNetNum;
    }

    public int getValveCollNum() {
        return valveCollNum;
    }

    public void setValveCollNum(int valveCollNum) {
        this.valveCollNum = valveCollNum;
    }

    public int getMeterNetNum() {
        return meterNetNum;
    }

    public void setMeterNetNum(int meterNetNum) {
        this.meterNetNum = meterNetNum;
    }

    public int getMeterCollNum() {
        return meterCollNum;
    }

    public void setMeterCollNum(int meterCollNum) {
        this.meterCollNum = meterCollNum;
    }

    public int getMeterProtNum() {
        return meterProtNum;
    }

    public void setMeterProtNum(int meterProtNum) {
        this.meterProtNum = meterProtNum;
    }

    public int getValveProtNum() {
        return valveProtNum;
    }

    public void setValveProtNum(int valveProtNum) {
        this.valveProtNum = valveProtNum;
    }

    @Override
    public String toString() {
        return "InnerRoomInfo{" +
                "roomId=" + roomId +
                ", roomCode='" + roomCode + '\'' +
                ", roomArea=" + roomArea +
                ", heatArea=" + heatArea +
                ", meterAddr='" + meterAddr + '\'' +
                ", valveAddr='" + valveAddr + '\'' +
                ", heatSupplied=" + heatSupplied +
                ", buildNum=" + buildNum +
                ", unitNum=" + unitNum +
                ", roomNum=" + roomNum +
                ", valveNetNum=" + valveNetNum +
                ", valveCollNum=" + valveCollNum +
                ", meterNetNum=" + meterNetNum +
                ", meterCollNum=" + meterCollNum +
                ", meterProtNum=" + meterProtNum +
                ", valveProtNum=" + valveProtNum +
                '}';
    }
}
