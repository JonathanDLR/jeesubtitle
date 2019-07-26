package jdlr.subtitle.forms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
	public static final String FILE_PATH = "/home/jdlr/Documents/exp_sub/";
	private List<String> BDDinfosString = new ArrayList<String>();
	
	/**
	 * Exporting in srt with BDD infos
	 * @param request
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void exportFile(HttpServletRequest request, List<BDDInfo> BDDInfos) throws IOException {
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
		
		File path = new File(FILE_PATH, title);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			
			for(String line : BDDinfosString) {
				if (line != null) {
					bw.write(line);
					bw.newLine();
				} else {
					bw.write("null");
					bw.newLine();
				}
			}
			String message = "Export OK";
			request.setAttribute("message", message);
			bw.close();
		} catch (IOException e) {
			request.setAttribute("message", e.getMessage());
		}
	}
}
