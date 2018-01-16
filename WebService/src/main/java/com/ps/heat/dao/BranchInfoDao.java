package com.ps.heat.dao;

/**
 * Created by xbc on 2018/1/10.
 */
public interface BranchInfoDao {
    /**
     * 通过热量表地址获取分支编号
     * @param meterAddr    分支热量表表地址
     * @return  分支编号
     */
    public int getBranchIdByMeter(String meterAddr);
}
