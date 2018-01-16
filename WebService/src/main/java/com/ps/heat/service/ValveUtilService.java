package com.ps.heat.service;

import com.ps.heat.entity.ValveControl;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xbc on 2017/9/27.
 */
public interface ValveUtilService {

    /**
     * 控制阀门
     * @param valveAddr
     * @param ctrlCmd [ON,OFF,FON,FOFF]
     * @return
     */
    public ValveControl forceCloseValve(String valveAddr,String ctrlCmd, String opt, String checkCode);

    /**
     * 查询阀门控制结果
     * @param valveAddr
     * @param ctrTimeStr
     * @return
     */
    public ValveControl queryValveControl(String valveAddr, String ctrTimeStr);

}
