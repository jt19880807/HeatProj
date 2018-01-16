package com.ps.heat.service;

import com.ps.heat.entity.HeatValveData;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by xbc on 2018/1/10.
 */
public interface ValveDataService {
    /**
     * 通过阀门地址获取阀门最新数据
     * @param valveAddr
     * @return
     */
    public HeatValveData getHeatValveData(String valveAddr);

    /**
     * 通过阀门地址获取阀门一段时间内的历史数据
     * @param valveAddr
     * @param start
     * @param end
     * @return
     */
    public List<HeatValveData> getHistHeatValveData(String valveAddr,
                                                    String start,
                                                    String end);
}
