package jdlr.subtitle.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdlr.subtitle.forms.EditFileForm;
import jdlr.subtitle.utilities.SubtitlesHandler;

@WebServlet("/EditSubtitle")
public class EditSubtitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		scan(request);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/edit_subtitle.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EditFileForm form = new EditFileForm();
		form.getFile(request);
		
		SubtitlesHandler subtitles = new SubtitlesHandler(form.getFileToSub());
		
		request.setAttribute("nameFile", form.getFileSubName());
		request.setAttribute("subtitles", subtitles.getSubtitles());
		
		scan(request);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/edit_subtitle.jsp").forward(request, response);
	}
	
	protected static final void scan(HttpServletRequest request) {
		EditFileForm form = new EditFileForm();
		ArrayList<String> fileList = form.scanPath("/home/jdlr/Documents/up_sub");
		
		request.setAttribute("fileList", fileList);
	}
}

