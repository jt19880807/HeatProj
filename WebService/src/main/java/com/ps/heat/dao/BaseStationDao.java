package com.ps.heat.dao;

import com.ps.heat.entity.BaseStation;

/**
 * Created by xbc on 2017/9/27.
 */
public interface BaseStationDao {
    /**
     * 查询基站连线信息
      * @param netNum
     * @return
     */
    public BaseStation getBaseStation(int netNum);
}
