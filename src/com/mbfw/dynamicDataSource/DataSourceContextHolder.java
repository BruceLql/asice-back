package com.mbfw.dynamicDataSource;

/**
 * 动态数据源切换使用类
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();      
    
    /**
     * 设置使用的数据源。<br>
     * 该参数值必须是ApplicationContext.xml文件中id为dataSource的bean中的key的值。
     * @param dbType 数据源标识
     */
    public static void setDBType(String dbType) {      
        contextHolder.set(dbType);      
    }      
  
    /**
     * 获取设置的数据源
     * @return 
     */
    public static String getDBType() {      
        return ((String) contextHolder.get());      
    }      
  
    /**
     * 清除设置的数据源。此后系统将使用默认的数据源。
     */
    public static void clearDBType() {      
        contextHolder.remove();      
    }
}
