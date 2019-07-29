package jdlr.subtitle.forms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdlr.subtitle.beans.BDDInfo;
import jdlr.subtitle.beans.BDDTitle;
import jdlr.subtitle.dao.DAOException;
import jdlr.subtitle.dao.DAOFactory;
import jdlr.subtitle.dao.InfoSubDAO;
import jdlr.subtitle.utilities.ContextHandler;

/**
 * Data processing for exporting the sub
 * @author jdlr
 *
 */
public class ExportFileForm {
	// public static final String FILE_PATH = "/WEB-INF/translate/";
	private List<String> BDDinfosString = new ArrayList<String>();
	
	/**
	 * Exporting in srt with BDD infos
	 * @param request
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void exportFile(HttpServletRequest request, HttpServletResponse response, List<BDDInfo> BDDInfos) throws IOException {
		String title = request.getParameter("choose");
	
		// Formatting array to write the file
		for (BDDInfo info : BDDInfos) {
			if (info.getFileName().contentEquals(title)) {
				if (!BDDinfosString.contains(Integer.toString(info.getLine_number()))) {
					BDDinfosString.add(Integer.toString(info.getLine_number()));
				}
				if (!BDDinfosString.contains(info.getLine_min())) {
					BDDinfosString.add(info.getLine_min());
				}
				BDDinfosString.add(info.getLine_text());
			}		
		}
		
		// String thePath = getRealPath();
		
		// File path = new File(title);
		try {
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename="+title+"");
			OutputStream outputStream = response.getOutputStream();
			for(String line : BDDinfosString) {
				if (line != null) {
					outputStream.write(line.getBytes());
					// outputStream.newLine();
				} else {
					outputStream.write("null".getBytes());
					// bw.newLine();
				}
			}
			String message = "Export OK";
			request.setAttribute("message", message);
			
			outputStream.flush();
			outputStream.close();
//			bw.write(response.getOutputStream());
//			bw.close();
		} catch (IOException e) {
			request.setAttribute("message", e.getMessage());
		}
	}
	
	/**
	 * @return Real path string of /srt folder in application context
	 */
//	private static String getRealPath() {
//		return ContextHandler.getContext().getRealPath(FILE_PATH);
//	}
}
