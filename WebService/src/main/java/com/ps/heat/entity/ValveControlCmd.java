package com.ps.heat.entity;

/**
 * Created by xbc on 2016/8/25.
 */
public enum ValveControlCmd {
    ON("普通开阀",3),
    OFF("普通关阀",19),
    FORCE_ON("强制开阀",18),
    FORCE_OFF("强制关阀",4);

    private String stateDsp;
    private int stateNum;
    ValveControlCmd(String dsp, int num) {
        this.stateDsp = dsp;
        this.stateNum = num;
    }

    public String getStateDsp() {
        return this.stateDsp;
    }

    public int getStateNum() {
        return this.stateNum;
    }

    static public ValveControlCmd getValveControlCmd(int num) {
        switch (num) {
            case 3:
                return ON;//
            case 4:
                return FORCE_OFF;//
            case 18:
                return FORCE_ON;
            case 19:
                return OFF;
            default:
                return null;//
        }
    }
}