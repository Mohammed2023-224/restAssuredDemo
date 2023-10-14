package util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;

//reading from the configuration file
public class ConfigManager {
	public static Properties prop;

	public static FileReader fr;

	public static Properties configManager() throws IOException {
		prop = new Properties();

		FileReader fr = new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "config.properties");
		prop.load(fr);
		return prop;

	}
}
