package com.lincomb.web.app.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class PropertiesUtil {
	/**
	 * Logger for this class
	 */
	private static final String PROPERTIES_FILE = "config.properties";
	
		
	/**
	 * 获取项目属性配置文件
	 * 
	 * @param propName
	 * @return
	 */
	public static Properties getProperties() {
		/*if (logger.isDebugEnabled()) {
			logger.debug("getProperties() - start"); //$NON-NLS-1$
		}*/
		Properties prop = new Properties();
		try {
			ClassPathResource cpr = new ClassPathResource(PROPERTIES_FILE);
			prop.load(cpr.getInputStream());
		} catch (IOException e) {
			//logger.error("getProperties()", e); //$NON-NLS-1$

			e.printStackTrace();
		}

		/*if (logger.isDebugEnabled()) {
			logger.debug("getProperties() - end"); //$NON-NLS-1$
		}*/
		return prop;
	}
	
	
   /**
     * 获取属性配置文件中指定参数
     * 
     * @param propName
     * @return
     */
    public static String getValue(String key) {
        String value = "";
        Properties prop = new Properties();
        try {
            ClassPathResource cpr = new ClassPathResource(PROPERTIES_FILE);
            prop.load(cpr.getInputStream());
            value = prop.getProperty(key);
        } catch (IOException e) {
            //logger.error("getValue()", e); //$NON-NLS-1$
            e.printStackTrace();
        }

        return value;
    }

}
