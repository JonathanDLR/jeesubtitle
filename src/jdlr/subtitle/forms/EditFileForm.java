package jdlr.subtitle.forms;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import jdlr.subtitle.utilities.ContextHandler;

/**
 * data processing of the edit servlet form
 * @author jdlr
 *
 */
public class EditFileForm {
	private static final String FILE_NAME = "WEB-INF/srt/";
	private String fileToSub;
	private String fileSubName;
	
	/**
	 * find all the srt file of the path
	 * @param inputStream
	 * @return list of srt file
	 */
	public ArrayList<String> scanPath(String inputStream) {
		File pathFile = new File(inputStream);
		File[] listPath = pathFile.listFiles();
		ArrayList<String> fileList = new ArrayList<String>();
		String pattern = ".*\\.srt";
		
		for (File theFile: listPath) {
			if (theFile.getName().matches(pattern)) {
				fileList.add(theFile.getName());
			}
		}
		
		return fileList;
	}
	
	/**
	 * Get file choosed by user
	 * @param request
	 * @throws IOException
	 * @throws ServletException
	 */
	public void getFile(HttpServletRequest request) throws IOException, ServletException {
		String path = getRealPath();
		
		fileSubName = request.getParameter("choose");
		fileToSub = path.concat(fileSubName);
	}
	
	/**
	 * @return Real path string of /srt folder in application context
	 */
	private static String getRealPath() {
		return ContextHandler.getContext().getRealPath(FILE_NAME);
	}
	
	// GETTERS SETTERS

	public String getFileToSub() {
		return fileToSub;
	}

	public void setFileToSub(String fileToSub) {
		this.fileToSub = fileToSub;
	}

	public String getFileSubName() {
		return fileSubName;
	}

	public void setFileSubName(String fileSubName) {
		this.fileSubName = fileSubName;
	}
}
