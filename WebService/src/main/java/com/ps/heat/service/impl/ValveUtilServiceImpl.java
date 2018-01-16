package com.ps.heat.service.impl;

import com.ps.heat.dao.RoomInfoDao;
import com.ps.heat.dao.ValveControlDao;
import com.ps.heat.entity.InnerRoomInfo;
import com.ps.heat.entity.ValveControl;
import com.ps.heat.entity.ValveControlCmd;
import com.ps.heat.service.ValveUtilService;
import com.ps.heat.util.CommonUtil;
import com.ps.heat.util.MD5Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by xbc on 2017/9/27.
 */
@RestController
public class ValveUtilServiceImpl implements ValveUtilService {
    @Autowired
    @Qualifier("roomInfoDao")
    private RoomInfoDao roomInfoDao;
    @Autowired
    @Qualifier("valveControlDao")
    private ValveControlDao valveCtrlDao;

    @Value("#{propertyConfigurer1['clientIP']}")
    private String clientIP;//客户Ip

//    public void setClientIP(String val) {
//        this.clientIP = val;
//    }
    private static final String[] ctrlCmds = { "ON", "OFF", "FON", "FOFF" };
    private static final DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static final Logger logger = LogManager.getLogger(ValveUtilServiceImpl.class.getName());
    @Override
    @RequestMapping("/valveCtrl")//, produces="text/html;charset=UTF-8"
    public ValveControl forceCloseValve(@RequestParam(value="valve", defaultValue="00")String valveAddr,
                                        @RequestParam(value="cmd", defaultValue="OO")String ctrlCmd,
                                        @RequestParam String opt,
                                        @RequestParam String checkCode) {
        logger.info("Receive new valve control command to control valve :"+valveAddr
                +" with command:"+ctrlCmd);
        ValveControl valveCtrl = new ValveControl();

        try {
            //校验码不能为空
            if (checkCode==null||checkCode.equals("")) {
                valveCtrl.setExecResult(new String(
                        "校验码不能为空".getBytes("UTF-8"),
                        "UTF-8")
                );
                logger.info("The check code is null or empty");
                return valveCtrl;
            }
            String message=valveAddr+ctrlCmd+opt+clientIP+ CommonUtil.formantCurrentDate("yyyyMMdd")+"Min0l";
            String md51=MD5Util.getMD5(message);
            String md52=MD5Util.md5Password(message);
            String md53=MD5Util.MD5(message);
            logger.info("MD51:"+md51);
            logger.info("MD52:"+md52);
            logger.info("MD53:"+md53);
            if (!MD5Util.check(message,checkCode)){
                valveCtrl.setExecResult(new String(
                        "校验码不正确".getBytes("UTF-8"),
                        "UTF-8")
                );
                logger.info("The check code is not right:"+checkCode);
                return valveCtrl;
            }
            if(valveAddr.length()!= 14){
                valveCtrl.setExecResult(new String(
                                "地址必须为14位，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The valve address is not 14 bit length:"+valveAddr);
                return valveCtrl;
            }
            String valveCmd = ctrlCmd.toUpperCase();
            if(!isCtrlCmd(valveCmd)){
                valveCtrl.setExecResult(new String(
                                "控制命令错误，请检查控制命令是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The valve control command is not right:"+valveAddr);
                return valveCtrl;
            }
            InnerRoomInfo room = roomInfoDao.getRoomInfoByValve(valveAddr);
            if (room == null) {
                valveCtrl.setExecResult(new String(
                                "系统内未查询到该地址，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The room information is not found:"+valveAddr);
                return valveCtrl;
            }
            valveCtrl.setRoomId(room.getRoomId());
            valveCtrl.setCtrlTime(LocalDateTime.now().format(dtf));
            valveCtrl.setBuildNum(room.getBuildNum());
            valveCtrl.setUnitNum(room.getUnitNum());
            valveCtrl.setRoomNum(room.getRoomNum());
            valveCtrl.setNetNum(room.getValveNetNum());
            valveCtrl.setCollNum(room.getValveCollNum());
            valveCtrl.setValveProt(room.getValveProtNum());
            valveCtrl.setValveAddr(valveAddr);
            if(valveCmd.equals("ON")){
                valveCtrl.setCtrlCmd(ValveControlCmd.ON);
            }else if(valveCmd.equals("OFF")){
                valveCtrl.setCtrlCmd(ValveControlCmd.OFF);
            }else if(valveCmd.equals("FON")){
                valveCtrl.setCtrlCmd(ValveControlCmd.FORCE_ON);
            }else if(valveCmd.equals("FOFF")){
                valveCtrl.setCtrlCmd(ValveControlCmd.FORCE_OFF);
            }
            valveCtrl.setCtrlOver(false);
            boolean res = valveCtrlDao.updateValveControl(valveCtrl);
            if(res){
                String result = "控制指令已接收，等待处理";
                valveCtrl.setExecResult(new String(result.getBytes("UTF-8"), "UTF-8"));
                logger.info("The valve control command was processed success:"+valveAddr);
            }else {
                String result = "控制指令接收失败，请联系服务提供方";
                valveCtrl.setExecResult(new String(result.getBytes("UTF-8"), "UTF-8"));
                valveCtrl.setExecTime(LocalDateTime.now().format(dtf));
                logger.info("The valve control command was processed failed:"+valveAddr);
            }
            System.out.println(valveCtrl.toString());
            return valveCtrl;
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            return valveCtrl;
        }
    }

    @Override
    @RequestMapping("/queryCtrl")
    public ValveControl queryValveControl(@RequestParam(value="valve", defaultValue="00")String valveAddr,
                                          @RequestParam(value="time", defaultValue="2017-01-01T05:00:00")String ctrTimeStr) {
        logger.info("Receive new valve control command to query valve control record :"+valveAddr);
        ValveControl valveCtrl = null;
        try {
            if(valveAddr.length()!= 14){
                logger.info("The valve address is not 14 bit length:"+valveAddr);
                return valveCtrl;
            }
            InnerRoomInfo room = roomInfoDao.getRoomInfoByValve(valveAddr);
            if (room == null) {
                logger.info("The room information is not found:"+valveAddr);
                return valveCtrl;
            }
            valveCtrl = valveCtrlDao.getValveControl(room.getRoomId(),LocalDateTime.parse(ctrTimeStr,dtf));
            if(valveCtrl == null){
                logger.info("The valve control record was not found:"+valveAddr+" at the time of "+ctrTimeStr);
            }else {
                logger.info("The valve control record :" + valveCtrl.toString());
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            return valveCtrl;
        }
    }

    private boolean isCtrlCmd(String value) {
        for (String cmd : ctrlCmds) {
            if (value.equals(cmd))
                return true;
        }
        return false;
    }
}
