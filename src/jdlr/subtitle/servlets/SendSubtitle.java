package jdlr.subtitle.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdlr.subtitle.beans.BDDInfo;
import jdlr.subtitle.beans.BDDTitle;
import jdlr.subtitle.beans.BeanException;
import jdlr.subtitle.dao.DAOException;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendSubtitle() {
        super();
        // TODO Auto-generated constructor stub
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
		
		try {
			titleSubDAO.addTitle(bddtitle);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		for (BDDInfo info : form.getSubs()) {
			try {
				infoSubDAO.addInfo(info);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/send.jsp").forward(request, response);
	}

}
