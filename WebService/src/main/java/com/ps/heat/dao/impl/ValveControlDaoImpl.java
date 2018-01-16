package com.ps.heat.dao.impl;

import com.ps.heat.dao.ValveControlDao;
import com.ps.heat.entity.ValveControl;
import com.ps.heat.entity.ValveControlCmd;
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

/**
 * Created by xbc on 2017/9/27.
 */
@Component("valveControlDao")
public class ValveControlDaoImpl implements ValveControlDao {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final Logger logger = LogManager.getLogger(ValveControlDaoImpl.class.getName());
    @Override
    public boolean updateValveControl(ValveControl valveControl) {
        if(isValveCtrlExist(valveControl.getRoomId())){
            return updateValveCtrl(valveControl);
        }else{
            return addValveCtrl(valveControl);
        }
    }

    @Override
    public ValveControl getValveControl(int roomId, LocalDateTime ctrlTime) {
        ValveControl res = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT room_id,ctrl_time,net_num,coll_num,build_num,unit_num,room_num," +
                    "heat_valve_addr,valve_prot,ctrl_state,ctrl_is_over,ctrl_exec_time,ctrl_exec_result "+
                    "FROM room_valve_ctrl_hist " +
                    "WHERE room_id = ? AND ctrl_time=? " ;
            ps =conn.prepareStatement(sql);
            ps.setInt(1, roomId);
            ps.setString(2, dtf.format(ctrlTime));
            rs =ps.executeQuery();
            if(rs.next())
            {
                res = new ValveControl();
                res.setRoomId(roomId);
                res.setCtrlTime(rs.getTimestamp("ctrl_time").toLocalDateTime().format(df));
                res.setNetNum(rs.getInt("net_num"));
                res.setCollNum(rs.getInt("coll_num"));
                res.setBuildNum(rs.getInt("build_num"));
                res.setUnitNum(rs.getInt("unit_num"));
                res.setRoomNum(rs.getInt("room_num"));
                res.setValveAddr(rs.getString("heat_valve_addr"));
                res.setValveProt(rs.getInt("valve_prot"));
                res.setCtrlCmd(ValveControlCmd.getValveControlCmd(rs.getInt("net_num")));
                res.setCtrlOver(rs.getInt("ctrl_is_over")>0);
                res.setExecTime(rs.getTimestamp("ctrl_exec_time").toLocalDateTime().format(df));
                res.setExecResult(rs.getString("ctrl_exec_result"));
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

    private boolean addValveCtrl(ValveControl valveControl){
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO room_valve_ctrl " +
                    "(room_id,ctrl_time,net_num,coll_num,build_num,unit_num,room_num," +
                    "heat_valve_addr,valve_prot,ctrl_state,ctrl_is_over) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " ;
            ps =conn.prepareStatement(sql);
            ps.setInt(1,valveControl.getRoomId());
            ps.setString(2, LocalDateTime.parse(valveControl.getCtrlTime(),df).format(dtf));
            ps.setInt(3, valveControl.getNetNum());
            ps.setInt(4, valveControl.getCollNum());
            ps.setInt(5, valveControl.getBuildNum());
            ps.setInt(6, valveControl.getUnitNum());
            ps.setInt(7, valveControl.getRoomNum());
            ps.setString(8, valveControl.getValveAddr());
            ps.setInt(9, valveControl.getValveProt());
            ps.setInt(10, valveControl.getCtrlCmd().getStateNum());
            ps.setInt(11, valveControl.isCtrlOver()?1:0);
            int rows = ps.executeUpdate();
            if(rows > 0) {
                res = true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        finally {
            try {
                if(ps!=null) {
                    ps.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            return res;
        }
    }

    private boolean updateValveCtrl(ValveControl valveControl){
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            String sql = "UPDATE room_valve_ctrl SET " +
                    "ctrl_time=?,net_num=?,coll_num=?,build_num=?,unit_num=?,room_num=?," +
                    "heat_valve_addr=?,valve_prot=?,ctrl_state=?,ctrl_is_over=? " +
                    "WHERE room_id=? " ;
            ps =conn.prepareStatement(sql);
            ps.setString(1, LocalDateTime.parse(valveControl.getCtrlTime(),df).format(dtf));
            ps.setInt(2, valveControl.getNetNum());
            ps.setInt(3, valveControl.getCollNum());
            ps.setInt(4, valveControl.getBuildNum());
            ps.setInt(5, valveControl.getUnitNum());
            ps.setInt(6, valveControl.getRoomNum());
            ps.setString(7, valveControl.getValveAddr());
            ps.setInt(8, valveControl.getValveProt());
            ps.setInt(9, valveControl.getCtrlCmd().getStateNum());
            ps.setInt(10, valveControl.isCtrlOver()?1:0);
            ps.setInt(11,valveControl.getRoomId());
            int rows = ps.executeUpdate();
            if(rows > 0) {
                res = true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        finally {
            try {
                if(ps!=null) {
                    ps.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            return res;
        }
    }

    private boolean isValveCtrlExist(int roomId) {
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT room_id, ctrl_time " +
                    "FROM room_valve_ctrl " +
                    "WHERE room_id = ? ";
            ps =conn.prepareStatement(sql);
            ps.setInt( 1, roomId);
            rs =ps.executeQuery();
            if(rs.next())
            {
                res = true;
            }
        } catch (SQLException e) {
//            e.printStackTrace();
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

}
