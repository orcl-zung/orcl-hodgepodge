package com.lea.framework.data.base.idgen.db.mysql.store;

import com.lea.framework.data.base.idgen.db.store.CodeStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @author lea
 * @description
 * @history 2023-04-10 18:12 created by lea
 * @since 2023-04-10 18:12
 */
public class MysqlSimpleCodeStore implements CodeStore {
    /** 数据源对象 */
    private DataSource dataSource = null;

    /** 日志对象 */
    private final static Logger loger = LoggerFactory.getLogger(MysqlSimpleCodeStore.class);

    public MysqlSimpleCodeStore(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 生成序列号（编号）
     *
     * @param businessCode
     */
    @Override
    public synchronized String generateCode(String businessCode)
    {
        Connection conn = null;
        CallableStatement cstmt = null;
        String code = null;
        try {
            conn = dataSource.getConnection();
//          cstmt = conn.prepareCall("{? = call sp_get_serial_number(?)}");
//          cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
//          cstmt.setString(2, businessCode);
//          cstmt.execute();
//          code = cstmt.getString(1);

            cstmt = conn.prepareCall("{call sp_get_serial_number(?) }");
            cstmt.setString(1, businessCode);
            cstmt.execute();
            //通过ResultSet获取返回值
            ResultSet rs = cstmt.getResultSet();
            if(rs.next()){
                return rs.getString(1);
            }else {
                throw new RuntimeException("获取生成序列号结果失败");
            }

        } catch (Exception ex) {
            loger.error("生成序列号失败", ex);
            throw new RuntimeException("生成序列号失败: "+ex.getMessage());
        } finally {
            this.closeResource(cstmt);
            this.closeResource(conn);
        }
    }


    /**
     * 检验并关闭相关的资源，这些资源包含了ResultSet,Connection,以及Statement.
     *
     * @param conn
     *            Connection 需要被关闭的数据库连接池。
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
     * @param stmt
     *            Statement 需要被关闭的PreparedStatement对象，
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

}
