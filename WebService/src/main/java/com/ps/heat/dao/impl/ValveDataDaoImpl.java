package com.ps.heat.dao.impl;

import com.ps.heat.dao.ValveDataDao;
import com.ps.heat.entity.HeatValveData;
import com.ps.heat.entity.InnerRoomInfo;
import com.ps.heat.entity.ValveStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xbc on 2018/1/10.
 */
@Component("valveDataDao")
public class ValveDataDaoImpl implements ValveDataDao {
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final Logger logger = LogManager.getLogger(ValveDataDaoImpl.class.getName());
    @Override
    public HeatValveData getLastValveData(InnerRoomInfo roomInfo) {
        HeatValveData valveData = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql="SELECT room_id, add_time, cur_temp, set_temp, " +
                    "valve_state, entrance_temp, exit_temp, valve_time " +
                    "FROM valve_data_last " +
                    "WHERE room_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roomInfo.getRoomId());
            rs=ps.executeQuery();
            if(rs.next())
            {
                valveData=new HeatValveData();
                valveData.setValveAddr(roomInfo.getValveAddr());
                valveData.setDataTime(rs.getTimestamp("add_time").toLocalDateTime().format(df));
                valveData.setSetTemp(rs.getDouble("set_temp"));
                valveData.setRoomTemp(rs.getDouble("cur_temp"));
                valveData.setFlowTemp(rs.getDouble("entrance_temp"));
                valveData.setReturnTemp(rs.getDouble("exit_temp"));
                valveData.setGivenReturnTemp(0);
                valveData.setScale(0);
                int valveState = rs.getInt("valve_state");
                valveData.setValveStatus(ValveStatus.getValveStatus(valveState));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return valveData;
        }
    }

    @Override
    public List<HeatValveData> getHistValveData(InnerRoomInfo roomInfo, LocalDateTime start, LocalDateTime end) {
        List<HeatValveData> valveDataList = new ArrayList<HeatValveData>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql="SELECT room_id, add_time, cur_temp, set_temp, " +
                    "valve_state, entrance_temp, exit_temp, valve_time " +
                    "FROM valve_data_last " +
                    "WHERE room_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roomInfo.getRoomId());
            rs=ps.executeQuery();
            while(rs.next())
            {
                HeatValveData valveData=new HeatValveData();
                valveData.setValveAddr(roomInfo.getValveAddr());
                valveData.setDataTime(rs.getTimestamp("add_time").toLocalDateTime().format(df));
                valveData.setSetTemp(rs.getDouble("set_temp"));
                valveData.setRoomTemp(rs.getDouble("cur_temp"));
                valveData.setFlowTemp(rs.getDouble("entrance_temp"));
                valveData.setReturnTemp(rs.getDouble("exit_temp"));
                valveData.setGivenReturnTemp(0);
                valveData.setScale(0);
                int valveState = rs.getInt("valve_state");
                valveData.setValveStatus(ValveStatus.getValveStatus(valveState));
                valveDataList.add(valveData);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return valveDataList;
        }
    }
}
