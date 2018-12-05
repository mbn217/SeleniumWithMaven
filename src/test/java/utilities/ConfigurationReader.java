package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author mbn217
 * @Date 04/18/2018
 * @Purpose This class will read the configuration.properties and use the value
 *          as dynamic entries for the test
 * @Usage Methods in this class are static , so you can just use class name with
 *        method name concatenated by "." example:
 *        ConfigurationReader.getProperty("url")
 */

public class ConfigurationReader {
	private static Properties configFile;
	private static Logger log = Logger.getLogger(ConfigurationReader.class);
	
	/**
	 * @author mbn217
	 * @Date 06/26/2018
	 * @Purpose this method will load the properties file
	 * @param N/A
	 * @return N/A
	 * @throws FileNotFoundException if the properties file is not found
	 * @throws Exception if general error occur while loading the properties file
	 */
	static {
		log.info("Reading configuration.properties File");
		String path = "./src/main/resources/configuration.properties";

		try {
			FileInputStream input = new FileInputStream(path);

			configFile = new Properties();
			configFile.load(input);

			input.close();

		} catch (FileNotFoundException e) {
			log.error("The File is not FOUND --> " + e.getMessage());

		} catch (Exception e) {
			log.error("Something went Wrong --> " + e.getMessage());

		}
	}

	/**
	 * @author mbn217
	 * @Date 04/19/2018
	 * @Purpose this method will return the property value of the chosen string
	 * @param key
	 *            -> the key value you want to retrieve
	 * @return a String type for the value of the key in the config file
	 */
	public static String getProperty(String key) {
		return configFile.getProperty(key);
	}

	/**
	 * @author mbn217
	 * @Date 04/19/2018
	 * @Purpose this method will return the property value of the chosen string
	 * @param key
	 *            -> the key value you want to retrieve
	 * @return a boolean type for the value of the key in the config file
	 */
	public static boolean getBooleanProperty(String key) {
		boolean isHighlight = Boolean.parseBoolean(configFile.getProperty(key));
		return isHighlight;
	}

}
