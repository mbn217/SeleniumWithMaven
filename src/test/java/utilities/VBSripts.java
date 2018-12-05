package utilities;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

public class VBSripts {
	private static Logger log = Logger.getLogger(VBSripts.class);
	
	/**
	 * @Author mbn217
	 * @param vbFilePath : This is the path of the vbs file
	 * you want to run 
	 * @Purpose This method will run the VBScript file 
	 * @Date 04/17/2018
	 */
	public static void runVBSript(String vbFilePath) {
		log.info("Running a Vbscript file");
		try {
	        Runtime.getRuntime().exec(new String[] {
	        "wscript.exe", vbFilePath
	        });        
	    } catch (FileNotFoundException ex) {
	    	log.error("The VBS File was not FOUND --> " + ex.getMessage());
	    
	    } catch (Exception ex) {
	    	log.error("Exception occured --> " + ex.getMessage());
	    }
	}
	
	
}
