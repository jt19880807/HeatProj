package com.ps.heat.entity;


/**
 * Created by xbc on 2017/9/26.
 */
public class ValveControl {
    int roomId;
    String ctrlTime;
    int netNum;
    int collNum;
    int buildNum;
    int unitNum;
    int roomNum;
    String valveAddr;
    int valveProt;
    ValveControlCmd ctrlCmd;
    boolean ctrlOver;
    String execTime;
    String execResult;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCtrlTime() {
        return ctrlTime;
    }

    public void setCtrlTime(String ctrlTime) {
        this.ctrlTime = ctrlTime;
    }

    public int getNetNum() {
        return netNum;
    }

    public void setNetNum(int netNum) {
        this.netNum = netNum;
    }

    public int getCollNum() {
        return collNum;
    }

    public void setCollNum(int collNum) {
        this.collNum = collNum;
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

    public String getValveAddr() {
        return valveAddr;
    }

    public void setValveAddr(String valveAddr) {
        this.valveAddr = valveAddr;
    }

    public int getValveProt() {
        return valveProt;
    }

    public void setValveProt(int valveProt) {
        this.valveProt = valveProt;
    }

    public ValveControlCmd getCtrlCmd() {
        return ctrlCmd;
    }

    public void setCtrlCmd(ValveControlCmd ctrlCmd) {
        this.ctrlCmd = ctrlCmd;
    }

    public boolean isCtrlOver() {
        return ctrlOver;
    }

    public void setCtrlOver(boolean ctrlOver) {
        this.ctrlOver = ctrlOver;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getExecResult() {
        return execResult;
    }

    public void setExecResult(String execResult) {
        this.execResult = execResult;
    }

    @Override
    public String toString() {
        return "ValveControl{" +
                "roomId=" + roomId +
                ", ctrlTime=" + ctrlTime +
                ", netNum=" + netNum +
                ", collNum=" + collNum +
                ", buildNum=" + buildNum +
                ", unitNum=" + unitNum +
                ", roomNum=" + roomNum +
                ", valveAddr='" + valveAddr + '\'' +
                ", valveProt=" + valveProt +
                ", ctrlCmd=" + ctrlCmd +
                ", ctrlOver=" + ctrlOver +
                ", execTime=" + execTime +
                ", execResult='" + execResult + '\'' +
                '}';
    }
}
