package com.ps.heat.dao.impl;

import com.ps.heat.dao.BaseStationDao;
import com.ps.heat.entity.BaseStation;
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

/**
 * Created by xbc on 2017/9/27.
 */
@Component("baseStationDao")
public class BaseStationDaoImpl implements BaseStationDao {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    private static final Logger logger = LogManager.getLogger(BaseStationDaoImpl.class.getName());
    @Override
    public BaseStation getBaseStation(int netNum) {
        BaseStation res = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT device_net,device_ip,device_port,heart_time "+
                    "FROM device " +
                    "WHERE device_net = ? " ;
            ps =conn.prepareStatement(sql);
            ps.setInt(1, netNum);
            rs =ps.executeQuery();
            if(rs.next())
            {
                res = new BaseStation();
                res.setNetNum(netNum);
                res.setClientIp(rs.getString("device_ip"));
                res.setClientPort(rs.getInt("device_port"));
                res.setHeartTime(rs.getTimestamp("heart_time").toLocalDateTime());
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
}
