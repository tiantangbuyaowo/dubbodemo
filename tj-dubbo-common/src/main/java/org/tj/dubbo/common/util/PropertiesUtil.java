package org.tj.dubbo.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties systemConfig = null;

	public static Properties getSystemConfigProperties() {
		if (systemConfig == null) {
			try {
				systemConfig = getSystemProperties(
						PropertiesUtil.class.getResource("/").toURI().getPath() + File.separator + "config.properties");
				return systemConfig;
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return null;
			}
		}
		return systemConfig;
	}
	/**
	 * 获取某个属性
	 */
	public static String getProperty(String key) {
		// return "D:" + File.separator + "picture";
		return getSystemConfigProperties().getProperty(key);
	}
	public static Properties getSystemProperties(String path) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return props;
	}
}
