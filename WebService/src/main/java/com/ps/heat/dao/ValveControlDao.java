package com.ps.heat.dao;

import com.ps.heat.entity.ValveControl;

import java.time.LocalDateTime;

/**
 * Created by xbc on 2017/9/27.
 */
public interface ValveControlDao {
    /**
     *  更新阀门控制
     * @param valveControl    阀门控制命令
     * @return  更新成功返回true,失败返回false
     */
    public boolean updateValveControl(ValveControl valveControl);

    /**
     * 根据房间编号和控制时间查询控制结果
     * @param roomId      房间编号
     * @param ctrlTime    控制时间
     * @return  查询到的控制命令以及执行结果，查询不到返回null
     */
    public ValveControl getValveControl(int roomId, LocalDateTime ctrlTime);

}
