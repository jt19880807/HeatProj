package com.ps.heat.dao.impl;

import com.ps.heat.dao.RoomInfoDao;
import com.ps.heat.entity.InnerRoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xbc on 2017/9/27.
 */
@Component("roomInfoDao")
public class RoomInfoDaoImpl implements RoomInfoDao {
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;
    @Override
    public InnerRoomInfo getRoomInfoByValve(String valveAddr) {
        InnerRoomInfo room =null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            System.out.println("getConnection连接数据库");
            conn = dataSource.getConnection();
            String sql = "SELECT DISTINCT id,b_num,u_name,r_name,user_code," +
                    "area,room_area,heat_valve_addr,heat_met_addr,is_supply,collector_id," +
                    "net_id,vcollector_id,v_net_id,meter_protocol,valve_protocol " +
                    " FROM node_list_view " +
                    "WHERE heat_valve_addr=?" ;
            ps =conn.prepareStatement(sql);
            ps.setString(1, valveAddr);
            rs =ps.executeQuery();
            if(rs.next())
            {
                room = new InnerRoomInfo();
                room.setRoomId(rs.getInt("id"));
                room.setRoomCode(rs.getString("user_code"));
                room.setValveAddr(rs.getString("heat_valve_addr"));
                room.setMeterAddr(rs.getString("heat_met_addr"));
                room.setHeatSupplied(rs.getInt("is_supply") > 0);
                room.setBuildNum(rs.getInt("b_num"));
                room.setUnitNum(rs.getInt("u_name"));
                room.setRoomNum(rs.getInt("r_name"));
                room.setHeatArea(rs.getDouble("area"));
                room.setRoomArea(rs.getDouble("room_area"));
                room.setMeterAddr(rs.getString("heat_met_addr"));
                room.setMeterCollNum(rs.getInt("meter_protocol"));
                room.setMeterNetNum(rs.getInt("net_id"));
                room.setMeterCollNum(rs.getInt("collector_id"));
                room.setValveAddr(rs.getString("heat_valve_addr"));
                room.setValveCollNum(rs.getInt("valve_protocol"));
                room.setValveNetNum(rs.getInt("v_net_id"));
                room.setValveCollNum(rs.getInt("vcollector_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            return room;
        }
    }

    @Override
    public InnerRoomInfo getRoomInfoByMeter(String meterAddr) {
        InnerRoomInfo room =null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            System.out.println("getConnection连接数据库");
            conn = dataSource.getConnection();
            String sql = "SELECT DISTINCT id,b_num,u_name,r_name,user_code," +
                    "area,room_area,heat_valve_addr,heat_met_addr,is_supply,collector_id," +
                    "net_id,vcollector_id,v_net_id,meter_protocol,valve_protocol " +
                    " FROM node_list_view " +
                    "WHERE heat_met_addr=?" ;
            ps =conn.prepareStatement(sql);
            ps.setString(1, meterAddr);
            rs =ps.executeQuery();
            if(rs.next())
            {
                room = new InnerRoomInfo();
                room.setRoomId(rs.getInt("id"));
                room.setRoomCode(rs.getString("user_code"));
                room.setValveAddr(rs.getString("heat_valve_addr"));
                room.setMeterAddr(rs.getString("heat_met_addr"));
                room.setHeatSupplied(rs.getInt("is_supply") > 0);
                room.setBuildNum(rs.getInt("b_num"));
                room.setUnitNum(rs.getInt("u_name"));
                room.setRoomNum(rs.getInt("r_name"));
                room.setHeatArea(rs.getDouble("area"));
                room.setRoomArea(rs.getDouble("room_area"));
                room.setMeterAddr(rs.getString("heat_met_addr"));
                room.setMeterCollNum(rs.getInt("meter_protocol"));
                room.setMeterNetNum(rs.getInt("net_id"));
                room.setMeterCollNum(rs.getInt("collector_id"));
                room.setValveAddr(rs.getString("heat_valve_addr"));
                room.setValveCollNum(rs.getInt("valve_protocol"));
                room.setValveNetNum(rs.getInt("v_net_id"));
                room.setValveCollNum(rs.getInt("vcollector_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            return room;
        }
    }
}
