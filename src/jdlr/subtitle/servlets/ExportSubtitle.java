package jdlr.subtitle.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdlr.subtitle.beans.BDDInfo;
import jdlr.subtitle.beans.BDDTitle;
import jdlr.subtitle.dao.DAOException;
import jdlr.subtitle.dao.DAOFactory;
import jdlr.subtitle.dao.InfoSubDAO;
import jdlr.subtitle.dao.TitleSubDAO;
import jdlr.subtitle.forms.EditFileForm;
import jdlr.subtitle.forms.ExportFileForm;

/**
 * Servlet implementation class ExportSubtitle
 */
@WebServlet(name = "ExportSubtitle", urlPatterns = { "/ExportSubtitle" })
public class ExportSubtitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static TitleSubDAO titleSubDAO;
	private InfoSubDAO infoSubDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.titleSubDAO = daoFactory.getTitleSubDAO();
		this.infoSubDAO = daoFactory.getInfoSubDAO();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportSubtitle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		scan(request);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/export.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BDDInfo> BDDInfos = new ArrayList<BDDInfo>();
		try {
			BDDInfos = infoSubDAO.getAllBDDInfo(request.getParameter("choose"));
		} catch (DAOException e) {
			request.setAttribute("error", e.getMessage());
		}
		
		ExportFileForm form = new ExportFileForm();
		
		form.exportFile(request, response, BDDInfos);
		
		scan(request);
		
		// this.getServletContext().getRequestDispatcher("/WEB-INF/export.jsp").forward(request, response);
	}

	protected static void scan(HttpServletRequest request) {
		try {
			List<BDDTitle> BDDTitles = titleSubDAO.getAllBDDTitle();
			request.setAttribute("files", BDDTitles);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
