package com.aladdin.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

@SuppressWarnings("deprecation")
public class AbstractMSSQLBaseDao extends SqlMapClientDaoSupport {
	
    private SqlExecutor sqlExecutor;

    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    public void setSqlExecutor(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    public void setEnableLimit(boolean enableLimit) {
        if (sqlExecutor instanceof LimitSqlExecutor) {
            ((LimitSqlExecutor) sqlExecutor).setEnableLimit(enableLimit);
        }
    }

    public void initialize() throws Exception {
        if (sqlExecutor != null) {
            SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
            if (sqlMapClient instanceof ExtendedSqlMapClient) {
            	setFieldValue(((ExtendedSqlMapClient) sqlMapClient).getDelegate(), "sqlExecutor", SqlExecutor.class, sqlExecutor);
            }
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void setFieldValue(Object target, String fname, Class ftype, Object fvalue) {
		if (target == null || fname == null || "".equals(fname) || (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
			return;
		}
		Class clazz = target.getClass();
		try {
			String themethod = "set" + Character.toUpperCase(fname.charAt(0)) + fname.substring(1);
			Method method = clazz.getDeclaredMethod(themethod, new Class[] { ftype });
			if (!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}
			method.invoke(target, new Object[] { fvalue });

		} catch (Exception me) {

			try {
				Field field = clazz.getDeclaredField(fname);
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}
				field.set(target, fvalue);
			} catch (Exception fe) {

			}
		}
	}

    public int getQueryCount(String sql) {
        int cc = 0;
        if (sqlExecutor instanceof LimitSqlExecutor) {
            cc = ((LimitSqlExecutor) sqlExecutor).getQueryCount(super.getDataSource(), sql);
        }
        return cc;
    }
}