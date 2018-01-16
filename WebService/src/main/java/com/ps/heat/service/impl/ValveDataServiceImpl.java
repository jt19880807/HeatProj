package com.ps.heat.service.impl;

import com.ps.heat.dao.RoomInfoDao;
import com.ps.heat.dao.ValveDataDao;
import com.ps.heat.entity.HeatMeterData;
import com.ps.heat.entity.HeatValveData;
import com.ps.heat.entity.InnerRoomInfo;
import com.ps.heat.service.ValveDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xbc on 2018/1/10.
 */
@RestController
public class ValveDataServiceImpl implements ValveDataService {
    @Autowired
    @Qualifier("valveDataDao")
    ValveDataDao valveDataDao;
    @Autowired
    @Qualifier("roomInfoDao")
    RoomInfoDao roomInfoDao;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static final Logger logger = LogManager.getLogger(MeterDataServiceImpl.class.getName());
    @Override
    @RequestMapping("/queryValve")
    public HeatValveData getHeatValveData(@RequestParam(value="valve", defaultValue="00")String valveAddr) {
        HeatValveData res = null;
        try {
            if(valveAddr.length()!= 14){
                res = new HeatValveData();
                res.setErrList(new String(
                                "地址必须为14位，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The meter address is not 14 bit length:"+valveAddr);
                return res;
            }
            InnerRoomInfo roomInfo = roomInfoDao.getRoomInfoByMeter(valveAddr);
            if(roomInfo == null) {
                res = new HeatValveData();
                res.setErrList(new String(
                                "系统内未查询到该地址，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The room information is not found:"+valveAddr);
                return res;
            }
            res = valveDataDao.getLastValveData(roomInfo);
            if(res == null){
                res = new HeatValveData();
                res.setErrList(new String(
                                "系统内未查询到该表具的最新数据，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The heat meter data is not found:"+valveAddr);
                return res;
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            return res;
        }
    }

    @Override
    @RequestMapping("/queryValveH")
    public List<HeatValveData> getHistHeatValveData(@RequestParam(value="valve", defaultValue="00")String valveAddr,
                                                    @RequestParam(value="start", defaultValue="2017-01-01T05:00:00")String start,
                                                    @RequestParam(value="end", defaultValue="2017-01-01T05:00:00")String end) {
        List<HeatValveData> resList = null;
        try {
            if(valveAddr.length()!= 14){
                resList = new ArrayList<HeatValveData>();
                HeatValveData res = new HeatValveData();
                res.setErrList(new String(
                                "地址必须为14位，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The meter address is not 14 bit length:"+valveAddr);
                resList.add(res);
                return resList;
            }
            InnerRoomInfo roomInfo = roomInfoDao.getRoomInfoByMeter(valveAddr);
            if(roomInfo == null) {
                resList = new ArrayList<HeatValveData>();
                HeatValveData res = new HeatValveData();
                res.setErrList(new String(
                                "系统内未查询到该地址，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The room information is not found:"+valveAddr);
                resList.add(res);
                return resList;
            }
            LocalDateTime startTime = LocalDateTime.parse(start,dtf);
            LocalDateTime endTime = LocalDateTime.parse(end,dtf);
            if(startTime == null || endTime== null){
                resList = new ArrayList<HeatValveData>();
                HeatValveData res = new HeatValveData();
                res.setErrList(new String(
                                "请检查起始时间和截止时间格式是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The room information is not found:"+valveAddr);
                resList.add(res);
                return resList;
            }
            resList = valveDataDao.getHistValveData(roomInfo, startTime, endTime);
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            return resList;
        }
    }
}
