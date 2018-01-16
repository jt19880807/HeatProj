package com.ps.heat.dao;

import com.ps.heat.entity.InnerRoomInfo;

/**
 * Created by xbc on 2017/9/27.
 */
public interface RoomInfoDao {
    /**
     * 通过阀门地址查询房间信息，要求系统内阀门地址不能重复
     * @param valveAddr    阀门地址
     * @return  查询到的房间信息，若查询不到，返回null
     */
    public InnerRoomInfo getRoomInfoByValve(String valveAddr);

    /**
     * 通过热量表地址查询房间信息，要求系统内热量表具地址不能重复
     * @param meterAddr    热量表地址
     * @return  查询到的房间信息，若查询不到，返回null
     */
    public InnerRoomInfo getRoomInfoByMeter(String meterAddr);
}
