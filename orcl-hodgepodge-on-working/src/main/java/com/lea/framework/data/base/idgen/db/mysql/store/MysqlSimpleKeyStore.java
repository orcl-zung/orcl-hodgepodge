package com.lea.framework.data.base.idgen.db.mysql.store;

import com.lea.framework.data.base.idgen.db.store.KeyStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lea
 * @description
 * @history 2023-04-10 18:08 created by lea
 * @since 2023-04-10 18:08
 */
@SuppressWarnings("serial")
public final class MysqlSimpleKeyStore implements KeyStore {
    /**
     * 日志对象
     */
    private final Logger loger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * 数据源对象
     */
    private DataSource dataSource = null;


//    /** 产生序列键缓冲个数 */
//    private static final int POOL_SIZE = 1;

    public MysqlSimpleKeyStore(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 从数据库中查询主键值并设置
     *
     * @param key 主键名称
     */
    synchronized private int retrieve(String key) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = dataSource.getConnection();
//            cstmt = conn.prepareCall("{? = call sp_pub_id_store_update(?,?) }");
//            cstmt.registerOutParameter(1, Types.INTEGER);
//            cstmt.setString(2, key);
//            cstmt.setInt(3, POOL_SIZE);
//            int iCount = cstmt.getInt(1);

            cstmt = conn.prepareCall("{call sp_pub_id_store_update(?) }");
            cstmt.setString(1, key);
            cstmt.execute();
            //通过ResultSet获取返回值
            ResultSet rs = cstmt.getResultSet();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("获取主键id结果失败");
            }
        } catch (Exception ex) {
            loger.error("获取主键id失败", ex);
            throw new RuntimeException("获取主键id失败: " + ex.getMessage());
        } finally {
            this.closeResource(cstmt);
            this.closeResource(conn);
        }

    }

    /**
     * 得到指定主键名称的主键值。
     *
     * @param key 主键名称
     * @return 主键值
     */
    @Override
    public long getNextKey(String key) {
        return retrieve(key);
    }

    /**
     * 检验并关闭相关的资源，这些资源包含了ResultSet,Connection,以及Statement.
     *
     * @param conn Connection  需要被关闭的数据库连接池。
     */
    private void closeResource(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            loger.error("关闭数据库对象出错.", ex);
        }
    }

    /**
     * 检验并关闭相关的资源，这些资源包含了Statement.
     *
     * @param stmt Statement  需要被关闭的PreparedStatement对象，
     */
    private void closeResource(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            loger.error("关闭数据库对象出错.", ex);
        }
    }

    /**
     * 获取一批keyId
     *
     * @param key
     * @param number
     * @return
     */
    @Override
    public synchronized List<Long> getBatchKey(String key, Integer number) {
        List<Long> result = new ArrayList<>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = dataSource.getConnection();
            cstmt = conn.prepareCall("{call sp_pub_id_store_update_step(?,?) }");
            cstmt.setString(1, key);
            cstmt.setInt(2, number);
            cstmt.execute();
            //通过ResultSet获取返回值
            ResultSet rs = cstmt.getResultSet();
            if (rs.next()) {
                int newId = rs.getInt(1);
                int size = rs.getInt(2);
                for (int i = 0; i < size; i++) {
                    result.add((long) newId);
                    newId++;
                }
            } else {
                throw new RuntimeException("获取主键id结果失败");
            }
        } catch (Exception ex) {
            loger.error("获取主键id失败", ex);
            throw new RuntimeException("获取主键id失败: " + ex.getMessage());
        } finally {
            this.closeResource(cstmt);
            this.closeResource(conn);
        }
        return result;
    }
}
