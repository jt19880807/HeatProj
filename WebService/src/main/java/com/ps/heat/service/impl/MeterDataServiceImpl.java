package com.ps.heat.service.impl;

import com.ps.heat.dao.BranchInfoDao;
import com.ps.heat.dao.MeterDataDao;
import com.ps.heat.dao.RoomInfoDao;
import com.ps.heat.entity.HeatMeterData;
import com.ps.heat.entity.InnerRoomInfo;
import com.ps.heat.service.MeterDataService;
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
 * Created by xbc on 2018/1/9.
 */
@RestController
public class MeterDataServiceImpl implements MeterDataService {

    @Autowired
    @Qualifier("meterDataDao")
    MeterDataDao meterDataDao;
    @Autowired
    @Qualifier("roomInfoDao")
    RoomInfoDao roomInfoDao;
    @Autowired
    @Qualifier("branchInfoDao")
    BranchInfoDao branchInfoDao;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static final Logger logger = LogManager.getLogger(MeterDataServiceImpl.class.getName());

    @Override
    @RequestMapping("/queryMeter")
    public HeatMeterData getHeatMeterData(@RequestParam(value="meter", defaultValue="00")String meterAddr) {
        HeatMeterData res = null;
        try {
            if(meterAddr.length()!= 14){
                res = new HeatMeterData();
                res.setErrList(new String(
                                "地址必须为14位，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The meter address is not 14 bit length:"+meterAddr);
                return res;
            }
            InnerRoomInfo roomInfo = roomInfoDao.getRoomInfoByMeter(meterAddr);
            if(roomInfo == null) {
                res = new HeatMeterData();
                res.setErrList(new String(
                                "系统内未查询到该地址，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The room information is not found:"+meterAddr);
                return res;
            }
            res = meterDataDao.getLastMeterData(roomInfo);
            if(res == null){
                res = new HeatMeterData();
                res.setErrList(new String(
                                "系统内未查询到该表具的最新数据，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The heat meter data is not found:"+meterAddr);
                return res;
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            return res;
        }
    }

    @Override
    @RequestMapping("/queryMeterH")
    public List<HeatMeterData> getHistMeterData(@RequestParam(value="meter", defaultValue="00")String meterAddr,
                                                @RequestParam(value="start", defaultValue="2017-01-01T05:00:00")String start,
                                                @RequestParam(value="end", defaultValue="2017-01-01T05:00:00")String end) {
        List<HeatMeterData> resList = null;
        try {
            if(meterAddr.length()!= 14){
                resList = new ArrayList<HeatMeterData>();
                HeatMeterData res = new HeatMeterData();
                res.setErrList(new String(
                                "地址必须为14位，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The meter address is not 14 bit length:"+meterAddr);
                resList.add(res);
                return resList;
            }
            InnerRoomInfo roomInfo = roomInfoDao.getRoomInfoByMeter(meterAddr);
            if(roomInfo == null) {
                resList = new ArrayList<HeatMeterData>();
                HeatMeterData res = new HeatMeterData();
                res.setErrList(new String(
                                "系统内未查询到该地址，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The room information is not found:"+meterAddr);
                resList.add(res);
                return resList;
            }
            LocalDateTime startTime = LocalDateTime.parse(start,dtf);
            LocalDateTime endTime = LocalDateTime.parse(end,dtf);
            if(startTime == null || endTime== null){
                resList = new ArrayList<HeatMeterData>();
                HeatMeterData res = new HeatMeterData();
                res.setErrList(new String(
                                "请检查起始时间和截止时间格式是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The room information is not found:"+meterAddr);
                resList.add(res);
                return resList;
            }
            resList = meterDataDao.getHistMeterData(roomInfo,startTime,endTime);
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            return resList;
        }
    }

    @Override
    @RequestMapping("/queryMeterB")
    public HeatMeterData getBranchMeterData(@RequestParam(value="meter", defaultValue="00")String meterAddr) {
        HeatMeterData res = null;
        try {
            if(meterAddr.length()!= 14){
                res = new HeatMeterData();
                res.setErrList(new String(
                                "地址必须为14位，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The meter address is not 14 bit length:"+meterAddr);
                return res;
            }
            int branchId = branchInfoDao.getBranchIdByMeter(meterAddr);
            if(branchId<=0) {
                res = new HeatMeterData();
                res.setErrList(new String(
                                "系统内未查询到该地址，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The room information is not found:"+meterAddr);
                return res;
            }
            res = meterDataDao.getLastBranchMeterData(branchId);
            if(res == null){
                res = new HeatMeterData();
                res.setErrList(new String(
                                "系统内未查询到该表具的最新数据，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The heat meter data is not found:"+meterAddr);
                return res;
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            return res;
        }
    }

    @Override
    @RequestMapping("/queryMeterBH")
    public List<HeatMeterData> getBranchHistMeterData(@RequestParam(value="meter", defaultValue="00")String meterAddr,
                                                      @RequestParam(value="start", defaultValue="2017-01-01T05:00:00")String start,
                                                      @RequestParam(value="end", defaultValue="2017-01-01T05:00:00")String end) {
        List<HeatMeterData> resList = null;
        try {
            if(meterAddr.length()!= 14){
                resList = new ArrayList<HeatMeterData>();
                HeatMeterData res = new HeatMeterData();
                res.setErrList(new String(
                                "地址必须为14位，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The meter address is not 14 bit length:"+meterAddr);
                resList.add(res);
                return resList;
            }
            int branchId = branchInfoDao.getBranchIdByMeter(meterAddr);
            if(branchId<=0) {
                resList = new ArrayList<HeatMeterData>();
                HeatMeterData res = new HeatMeterData();
                res.setErrList(new String(
                                "系统内未查询到该地址，请检查地址是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The room information is not found:"+meterAddr);
                resList.add(res);
                return resList;
            }
            LocalDateTime startTime = LocalDateTime.parse(start,dtf);
            LocalDateTime endTime = LocalDateTime.parse(end,dtf);
            if(startTime == null || endTime== null){
                resList = new ArrayList<HeatMeterData>();
                HeatMeterData res = new HeatMeterData();
                res.setErrList(new String(
                                "请检查起始时间和截止时间格式是否准确".getBytes("UTF-8"),
                                "UTF-8")
                );
                logger.info("The room information is not found:"+meterAddr);
                resList.add(res);
                return resList;
            }
            resList = meterDataDao.getHistBranchMeterData(branchId, startTime, endTime);
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            return resList;
        }
    }
}
