package jdlr.subtitle.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class UploadFileForm {
	public static final int TMP_SIZE = 10240;
	public static final String FILE_PATH = "/home/jdlr/Documents/up_sub/";
	private String fileName;
	private String fileData;
	
	/**
	 * Upload file to sub
	 * @param request
	 * @throws IOException
	 * @throws ServletException
	 */
	public void uploadFile(HttpServletRequest request) throws IOException, ServletException {
		fileName = request.getParameter("urfilename");
		request.setAttribute("urfilename", fileName);
		
		Part part = request.getPart("urfile");
		
		fileData = getName(part);
		
		if (fileData != null && !fileData.isEmpty()) {
			String inputName = part.getName();
			
			fileData = fileData.substring(fileData.lastIndexOf('/') + 1).substring(fileData.lastIndexOf('\\') + 1);
			
			writeFile(part, fileData, FILE_PATH);
			
			request.setAttribute(inputName, fileData);
		}
	}
	
	/**
	 * Find the name of the file uploaded
	 * @param part
	 * @return
	 */
	private static String getName(Part part) {
		for (String contentDisposition: part.getHeader("content-disposition").split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		
		return null;
	}
	
	/**
	 * write the uploaded file in path
	 * @param part
	 * @param fileName
	 * @param path
	 * @throws IOException
	 */
	private void writeFile(Part part, String fileName, String path) throws IOException {
		BufferedInputStream inputStream = null;
		BufferedOutputStream outputStream = null;
		
		try {
			inputStream = new BufferedInputStream(part.getInputStream(), TMP_SIZE);
			outputStream = new BufferedOutputStream(new FileOutputStream(new File(path + fileName)), TMP_SIZE);
			
			byte[] tmp = new byte[TMP_SIZE];
			int lengthTmp;
			
			while ((lengthTmp = inputStream.read(tmp)) > 0) {
				outputStream.write(tmp, 0, lengthTmp);
			}
		} finally {
			try {
				outputStream.close();
			} catch (IOException ignore) {
				
			}
			try {
				inputStream.close();
			} catch (IOException ignore) {
				
			}
		}
		
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileData() {
		return fileData;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	

}
