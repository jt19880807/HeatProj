package com.ps.heat.dao.impl;

import com.ps.heat.dao.MeterDataDao;
import com.ps.heat.entity.HeatMeterData;
import com.ps.heat.entity.InnerRoomInfo;
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
@Component("meterDataDao")
public class MeterDataDaoImpl implements MeterDataDao {
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final Logger logger = LogManager.getLogger(MeterDataDaoImpl.class.getName());
    @Override
    public HeatMeterData getLastMeterData(InnerRoomInfo room) {
        HeatMeterData res = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT room_id, add_time, cu_heat, heat_power, cu_flow, instant_flowrate, " +
                    "entrance_temp, exit_temp, hours, battery_status " +
                    " FROM node_data_last " +
                    "WHERE room_id=? ";
            ps =conn.prepareStatement(sql);
            ps.setInt(1,room.getRoomId());
            rs =ps.executeQuery();
            if(rs.next())
            {
                res = new HeatMeterData();
                res.setMeterAddr(room.getMeterAddr());
                res.setDataTime(rs.getTimestamp("add_time").toLocalDateTime().format(df));
                res.setCuHeat(rs.getDouble("cu_heat"));
                res.setPower(rs.getDouble("heat_power"));
                res.setVolume(rs.getDouble("cu_flow"));
                res.setFlow(rs.getDouble("instant_flowrate"));
                res.setFlowTemp(rs.getDouble("entrance_temp"));
                res.setReturnTemp(rs.getDouble("exit_temp"));
                res.setHours(rs.getInt("hours"));
                res.setUnderVoltage(rs.getInt("battery_status")>0);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        finally {
            try {
                if(ps!=null) {
                    ps.close();
                }
                if(rs!=null) {
                    rs.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    @Override
    public HeatMeterData getLastBranchMeterData(int branchId) {
        HeatMeterData res = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT meter_id, meter_datatime, cu_heat, heat_power, cu_flow, flow_rate, " +
                    "entrance_temp, exit_temp, hours, battery_status " +
                    " FROM meter_data_last " +
                    "WHERE meter_id=? ";
            ps =conn.prepareStatement(sql);
            ps.setInt(1,branchId);
            rs =ps.executeQuery();
            if(rs.next())
            {
                res = new HeatMeterData();
//                res.setMeterAddr(room.getMeterAddr());
                res.setDataTime(rs.getTimestamp("meter_datatime").toLocalDateTime().format(df));
                res.setCuHeat(rs.getDouble("cu_heat"));
                res.setPower(rs.getDouble("heat_power"));
                res.setVolume(rs.getDouble("cu_flow"));
                res.setFlow(rs.getDouble("flow_rate"));
                res.setFlowTemp(rs.getDouble("entrance_temp"));
                res.setReturnTemp(rs.getDouble("exit_temp"));
                res.setHours(rs.getInt("hours"));
                res.setUnderVoltage(rs.getInt("battery_status")>0);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        finally {
            try {
                if(ps!=null) {
                    ps.close();
                }
                if(rs!=null) {
                    rs.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    @Override
    public List<HeatMeterData> getHistMeterData(InnerRoomInfo room, LocalDateTime start, LocalDateTime end) {
        List<HeatMeterData> resList = new ArrayList<HeatMeterData>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT room_id, add_time, cu_heat, heat_power, cu_flow, instant_flowrate, " +
                    "entrance_temp, exit_temp, hours, battery_status " +
                    " FROM node_data " +
                    "WHERE room_id=? " +
                    "AND add_time BETWEEN ? AND ? " +
                    "ORDER BY add_time ASC ";
            ps =conn.prepareStatement(sql);
            ps.setInt(1,room.getRoomId());
            ps.setString(2,dtf.format(start));
            ps.setString(3,dtf.format(end));
            rs =ps.executeQuery();
            while (rs.next())
            {
                HeatMeterData res = new HeatMeterData();
                res.setMeterAddr(room.getMeterAddr());
                res.setDataTime(rs.getTimestamp("add_time").toLocalDateTime().format(df));
                res.setCuHeat(rs.getDouble("cu_heat"));
                res.setPower(rs.getDouble("heat_power"));
                res.setVolume(rs.getDouble("cu_flow"));
                res.setFlow(rs.getDouble("instant_flowrate"));
                res.setFlowTemp(rs.getDouble("entrance_temp"));
                res.setReturnTemp(rs.getDouble("exit_temp"));
                res.setHours(rs.getInt("hours"));
                res.setUnderVoltage(rs.getInt("battery_status")>0);
                resList.add(res);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        finally {
            try {
                if(ps!=null) {
                    ps.close();
                }
                if(rs!=null) {
                    rs.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resList;
        }
    }

    @Override
    public List<HeatMeterData> getHistBranchMeterData(int branchId, LocalDateTime start, LocalDateTime end) {
        List<HeatMeterData> resList = new ArrayList<HeatMeterData>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT meter_id, meter_datatime, cu_heat, heat_power, cu_flow, flow_rate, " +
                    "entrance_temp, exit_temp, hours, battery_status " +
                    "FROM meter_data " +
                    "WHERE meter_id=? " +
                    "AND add_time BETWEEN ? AND ? " +
                    "ORDER BY meter_datatime ASC ";
            ps =conn.prepareStatement(sql);
            ps.setInt(1,branchId);
            ps.setString(2,dtf.format(start));
            ps.setString(3,dtf.format(end));
            rs =ps.executeQuery();
            while(rs.next())
            {
                HeatMeterData res = new HeatMeterData();
//                res.setMeterAddr(room.getMeterAddr());
                res.setDataTime(rs.getTimestamp("meter_datatime").toLocalDateTime().format(df));
                res.setCuHeat(rs.getDouble("cu_heat"));
                res.setPower(rs.getDouble("heat_power"));
                res.setVolume(rs.getDouble("cu_flow"));
                res.setFlow(rs.getDouble("flow_rate"));
                res.setFlowTemp(rs.getDouble("entrance_temp"));
                res.setReturnTemp(rs.getDouble("exit_temp"));
                res.setHours(rs.getInt("hours"));
                res.setUnderVoltage(rs.getInt("battery_status")>0);
                resList.add(res);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        finally {
            try {
                if(ps!=null) {
                    ps.close();
                }
                if(rs!=null) {
                    rs.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resList;
        }
    }
}
