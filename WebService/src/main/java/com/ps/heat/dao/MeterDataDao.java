package com.ps.heat.dao;

import com.ps.heat.entity.HeatMeterData;
import com.ps.heat.entity.InnerRoomInfo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by xbc on 2018/1/10.
 */
public interface MeterDataDao {
    /**
     * 通过房间信息获取热量表最新数据
     * @param room    房间信息
     * @return  查询到的热量表最新数据，若查询不到返回null
     */
    public HeatMeterData getLastMeterData(InnerRoomInfo room);

    /**
     * 通过分支编号查询最新的分支热量表最新数据
     * @param branchId    分支编号
     * @return  查询到的最新分支热量表数据，若查询不到返回null
     */
    public HeatMeterData getLastBranchMeterData(int branchId);

    /**
     * 通过房间信息查询一段时间之内的热量表数据
     * @param room     房间信息
     * @param start    查询数据的起始时间
     * @param end      查询数据的截止时间
     * @return  查询到的热量表数据列表，若查询不到返回空的数据列表，不返回null
     */
    public List<HeatMeterData> getHistMeterData(InnerRoomInfo room,
                                                LocalDateTime start,
                                                LocalDateTime end);

    /**
     * 通过分支编号查询一段时间之内的热量表数据
     * @param branchId    分支编号
     * @param start       查询数据的起始时间
     * @param end         查询数据的截止时间
     * @return  查询到的热量表数据列表，若查询不到返回空的数据列表，不返回null
     */
    public List<HeatMeterData> getHistBranchMeterData(int branchId,
                                                LocalDateTime start,
                                                LocalDateTime end);
}
