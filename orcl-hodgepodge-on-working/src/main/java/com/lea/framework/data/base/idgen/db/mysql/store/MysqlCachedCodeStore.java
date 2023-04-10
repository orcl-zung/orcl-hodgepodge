package com.lea.framework.data.base.idgen.db.mysql.store;

import com.lea.framework.data.base.idgen.db.store.CodeStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lea
 * @description
 * @history 2023-04-10 17:44 created by lea
 * @since 2023-04-10 17:44
 */
public class MysqlCachedCodeStore implements CodeStore {

    public static final Logger logger = LoggerFactory.getLogger(MysqlCachedCodeStore.class);

    private DataSource dataSource = null;

    private int poolSize = 10;

    private Queue<String> cacheQueue = new ConcurrentLinkedQueue<>();


    public MysqlCachedCodeStore(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public synchronized String generateCode(String businessCode) {
        Connection conn = null;
        CallableStatement cstmt = null;
        String code = null;

        code = cacheQueue.poll();

        if (code != null) {
            return code;
        }

        try {
            conn = dataSource.getConnection();
            cstmt = conn.prepareCall("{call sp_get_serial_number_step(?, ?) }");
            cstmt.setString(1, businessCode);
            cstmt.setInt(2, poolSize);
            cstmt.execute();
            do {
                ResultSet rs = cstmt.getResultSet();
                while (rs.next()) {
                    code = rs.getString(1);
                    cacheQueue.offer(code);
                }
            } while (cstmt.getMoreResults());

            code = cacheQueue.poll();
            if (code == null) {
                throw new RuntimeException("获取生成序列号结果失败");
            }
            return code;
        } catch (Exception ex0) {
            logger.error("生成序列号失败", ex0);
            throw new RuntimeException("生成序列号失败: " + ex0.getMessage());
        } finally {
            this.closeResource(cstmt);
            this.closeResource(conn);
        }
    }

    private void closeResource(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("关闭数据库对象出错.", e);
        }
    }

    private void closeResource(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            logger.error("关闭数据库对象出错.", e);
        }
    }
}
