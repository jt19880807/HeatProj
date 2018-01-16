package com.ps.heat.entity;

/**
 * Created by xbc on 2017/10/5.
 */
public enum ValveStatus {
    OPENED("开状态", 0),//开状态
    CLOSED("关状态", 1),//关状态
    SCALE("开度状态", 2),//
    ERROR("异常", 3); //异常

    private String stateDsp;
    private int stateNum;

    ValveStatus(String dsp, int num) {
        this.stateDsp = dsp;
        this.stateNum = num;
    }

    public String getStateDsp() {
        return this.stateDsp;
    }

    public int getStateNum() {
        return this.stateNum;
    }

    static public ValveStatus getValveStatus(int num) {
        switch (num) {
            case 0:
                return OPENED;//开状态
            case 1:
                return CLOSED;//关状态
            case 2:
                return SCALE;
            case 3:
                return ERROR;//异常
            default:
                return ERROR;//异常
        }
    }
}
