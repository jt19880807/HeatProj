package com.ps.heat.service;

import com.ps.heat.entity.HeatMeterData;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by xbc on 2018/1/9.
 */
public interface MeterDataService {
    /**
     * 通过热量表地址获取热量表数据
     * @param meterAddr
     * @return
     */
    public HeatMeterData getHeatMeterData(String meterAddr);

    /**
     * 通过热量表地址获取热量表一段时间内的历史数据
     * @param meterAddr
     * @param start
     * @param end
     * @return
     */
    public List<HeatMeterData> getHistMeterData(String meterAddr,
                                                String start,
                                                String end);

    /**
     * 通过热量表地址获取分支表数据
     * @param meterAddr
     * @return
     */
    public HeatMeterData getBranchMeterData(String meterAddr);

    /**
     * 通过热量表地址获取分支表历史数据
     * @param meterAddr
     * @param start
     * @param end
     * @return
     */
    public List<HeatMeterData> getBranchHistMeterData(String meterAddr,
                                                      String start,
                                                      String end);
}
