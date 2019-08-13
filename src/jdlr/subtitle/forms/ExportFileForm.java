package jdlr.subtitle.forms;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
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
	private List<String> BDDinfosString;
	private final String NEW_LINE;
	private PrintWriter writer;
	
	public ExportFileForm()  {
		BDDinfosString = new ArrayList<String>();
		NEW_LINE = System.getProperty("line.separator");
	}
	
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
		
	
		// Exporting the file
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment; filename="+title+"");
		
		writer = response.getWriter();
		
		try {		
			for(String line : BDDinfosString) {
				if (line != null) {
					writer.write(line);
					writer.write(NEW_LINE);
				} else {
					writer.write("null");
					writer.write(NEW_LINE);
				}
			}	
			writer.flush();
		} finally {
			if (writer != null) {
				// writer.close();
			}	
		}
	}
	
	public void finalize() {
		writer.close();
	}
}
