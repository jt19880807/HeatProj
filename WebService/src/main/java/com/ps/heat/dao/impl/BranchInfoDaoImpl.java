package com.ps.heat.dao.impl;

import com.ps.heat.dao.BranchInfoDao;
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
 * Created by xbc on 2018/1/10.
 */
@Component("branchInfoDao")
public class BranchInfoDaoImpl implements BranchInfoDao {
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;
    private static final Logger logger = LogManager.getLogger(BranchInfoDaoImpl.class.getName());

    @Override
    public int getBranchIdByMeter(String meterAddr) {
        int res = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            System.out.println("getConnection连接数据库");
            conn = dataSource.getConnection();
            String sql = "SELECT DISTINCT id,zone_id " +
                    " FROM meter_list " +
                    "WHERE meter_addr=?" ;
            ps =conn.prepareStatement(sql);
            ps.setString(1, meterAddr);
            rs =ps.executeQuery();
            if(rs.next())
            {
                res = rs.getInt("id");
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
            return res;
        }
    }
}
