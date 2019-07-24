package jdlr.subtitle.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdlr.subtitle.beans.BDDInfo;
import jdlr.subtitle.beans.BDDTitle;
import jdlr.subtitle.beans.BeanException;
import jdlr.subtitle.dao.DAOException;
import jdlr.subtitle.dao.DAOFactory;
import jdlr.subtitle.dao.TitleSubDAO;
import jdlr.subtitle.dao.InfoSubDAO;
import jdlr.subtitle.forms.SendSubtitleForm;

/**
 * Servlet implementation class SendSubtitle
 */
@WebServlet("/SendSubtitle")
public class SendSubtitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TitleSubDAO titleSubDAO;
	private InfoSubDAO infoSubDAO;
       
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.titleSubDAO = daoFactory.getTitleSubDAO();
		this.infoSubDAO = daoFactory.getInfoSubDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SendSubtitleForm form = new SendSubtitleForm();
		form.getSubtitle(request);
		
		BDDTitle bddtitle = new BDDTitle();
		
		bddtitle.setFileName(form.getFileName());
		
		// CHECK IF FILE IS IN THE BDD
		try {
			List<BDDTitle> BDDTitles = titleSubDAO.getAllBDDTitle();
			List<String> BDDTitlesString = new ArrayList<String>();
			
			for (BDDTitle title: BDDTitles) {
				BDDTitlesString.add(title.getFileName());
			}
			
			// IF FILE IS IN THE BDD WE JUST UPDATE INFO ELSE WE CREATE THE FILE AND THE INFO
			if (BDDTitlesString.contains(bddtitle.getFileName())) {
				for (BDDInfo info : form.getSubs()) {
					try {
						if (info.getLine_text() != null && !info.getLine_text().isEmpty()) {
							infoSubDAO.updInfo(info);
						}					
					} catch (DAOException e) {
						request.setAttribute("error", e.getMessage());
					}
				}
			} else { 
				try {
					titleSubDAO.addTitle(bddtitle);
					request.setAttribute("fileName", form.getFileName());
				} catch (DAOException e) {
					request.setAttribute("error", e.getMessage());
				}
				
				for (BDDInfo info : form.getSubs()) {
					try {
						infoSubDAO.addInfo(info);
					} catch (DAOException e) {
						request.setAttribute("error", e.getMessage());
					}
				}
			}
		} catch (DAOException e) {
			request.setAttribute("error", e.getMessage());
		}
		
		
		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/send.jsp").forward(request, response);
	}

}
