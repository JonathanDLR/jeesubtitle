package jdlr.subtitle.forms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import jdlr.subtitle.utilities.ContextHandler;

/**
 * data processing of the edit servlet form
 * @author jdlr
 *
 */
public class EditFileForm {
	private static final String FILE_NAME = "/home/jdlr/Documents/up_sub/";
	private String fileToSub;
	private String fileSubName;
	
	/**
	 * find all the srt file of the path
	 * @param path
	 * @return list of srt file
	 */
	public ArrayList<String> scanPath(String path) {
		File pathFile = new File(path);
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
		fileSubName = request.getParameter("choose");
		fileToSub = FILE_NAME.concat(fileSubName);
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
