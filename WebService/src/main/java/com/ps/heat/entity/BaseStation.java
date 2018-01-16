package com.ps.heat.entity;

import java.time.LocalDateTime;

/**
 * Created by xbc on 2017/9/27.
 */
public class BaseStation {
    private int netNum;         //网络号
    private String clientIp;    //客户端IP
    private int clientPort;     //客户端端口
    private LocalDateTime heartTime;    //最后一次心跳包时间

    public int getNetNum() {
        return netNum;
    }

    public void setNetNum(int netNum) {
        this.netNum = netNum;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public LocalDateTime getHeartTime() {
        return heartTime;
    }

    public void setHeartTime(LocalDateTime heartTime) {
        this.heartTime = heartTime;
    }

    @Override
    public String toString() {
        return "BaseStation{" +
                "netNum=" + netNum +
                ", clientIp='" + clientIp + '\'' +
                ", clientPort=" + clientPort +
                ", heartTime=" + heartTime +
                '}';
    }
}
