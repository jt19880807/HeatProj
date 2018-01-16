package com.ps.heat.dao;

import com.ps.heat.entity.HeatValveData;
import com.ps.heat.entity.InnerRoomInfo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by xbc on 2018/1/10.
 */
public interface ValveDataDao {
    /**
     * 通过房间信息查询房间最新的阀门数据
     * @param roomInfo    房间信息
     * @return  查询到的最新阀门数据，若查询不到，返回null
     */
    public HeatValveData getLastValveData(InnerRoomInfo roomInfo);

    /**
     * 通过房间信息查询一段时间内的阀门数据
     * @param roomInfo    房间信息
     * @param start       起始时间
     * @param end         结束时间
     * @return  查询到的阀门数据列表，若查询不到，返回空的列表，不返回null
     */
    public List<HeatValveData> getHistValveData(InnerRoomInfo roomInfo,
                                                LocalDateTime start,
                                                LocalDateTime end);
}
