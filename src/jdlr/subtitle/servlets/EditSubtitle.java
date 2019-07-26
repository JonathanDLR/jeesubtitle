package jdlr.subtitle.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdlr.subtitle.beans.BDDTitle;
import jdlr.subtitle.dao.DAOException;
import jdlr.subtitle.dao.DAOFactory;
import jdlr.subtitle.dao.InfoSubDAO;
import jdlr.subtitle.dao.TitleSubDAO;
import jdlr.subtitle.forms.EditFileForm;
import jdlr.subtitle.utilities.ContextHandler;
import jdlr.subtitle.utilities.SubtitlesHandler;

@WebServlet("/EditSubtitle")
public class EditSubtitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TitleSubDAO titleSubDAO;
	private InfoSubDAO infoSubDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.titleSubDAO = daoFactory.getTitleSubDAO();
		this.infoSubDAO = daoFactory.getInfoSubDAO();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		scan(request);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/edit_subtitle.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Reading the choosed file and send info to the jsp
		EditFileForm form = new EditFileForm();
		form.getFile(request);
		
		SubtitlesHandler subtitles = new SubtitlesHandler(form.getFileToSub());
		
		request.setAttribute("nameFile", form.getFileSubName());
		request.setAttribute("subtitles", subtitles.getSubtitles());
		
		scan(request);
		
		// Check if file is present in the bdd
		try {
			List<BDDTitle> BDDTitles = titleSubDAO.getAllBDDTitle();
			List<String> BDDTitlesString = new ArrayList<String>();
			
			for (BDDTitle title: BDDTitles) {
				BDDTitlesString.add(title.getFileName());
			}
			
			if (BDDTitlesString.contains(form.getFileSubName())) {
				request.setAttribute("subs", infoSubDAO.getAllBDDInfo(form.getFileSubName()));
			}
		} catch (DAOException e) {
			request.setAttribute("error", e.getMessage());
		}
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/edit_subtitle.jsp").forward(request, response);
	}
	
	protected static final void scan(HttpServletRequest request) {
		EditFileForm form = new EditFileForm();
		ArrayList<String> fileList = form.scanPath("/home/jdlr/Documents/up_sub/");
		
		request.setAttribute("fileList", fileList);
	}
}

