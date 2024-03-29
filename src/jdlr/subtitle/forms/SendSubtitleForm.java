package jdlr.subtitle.forms;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import jdlr.subtitle.beans.BDDInfo;

/**
 * data processing of send servlet form
 * @author jdlr
 *
 */
public class SendSubtitleForm {
	private String fileName;
	private Enumeration<String> paramNames = null;
	private ArrayList<String> lineNumber = null;
	private ArrayList<BDDInfo> subs = new ArrayList<BDDInfo>();
	private int j = 1;
	private int k = 1;
	private int l;
	private int paramNumber = 0;
	private String paramMin = null;
	
	/**
	 * adding sub to ArrayList
	 * @param request
	 * @throws UnsupportedEncodingException 
	 */
	public void getSubtitle(HttpServletRequest request) throws UnsupportedEncodingException {	
		fileName = request.getParameter("fileName");
		paramNames = request.getParameterNames();
		lineNumber = Collections.list(paramNames);
		
		// Creation object BDDInfo
		for (String line : lineNumber) {
			if (line.contains("sub")) {
				BDDInfo bddinfo = new BDDInfo();
				subs.add(bddinfo);
			}
		}
		
		// Creation BDDInfo attribute
		for (String paramName : lineNumber) {			
			// String paramName = paramNames.nextElement();
			
			String[] paramValues = request.getParameterValues(paramName);
			
			for (int i = 0; i < paramValues.length; i ++) {
				String paramValue = paramValues[i];
				
				if (!paramValue.isEmpty() && paramValue != null && !paramName.equals("fileName")) {					
					if (paramName.equals("number" + k)) {
						paramNumber = Integer.parseInt(paramValue);
					}
					
					if (paramName.equals("min" + k)) {
						paramMin = paramValue;
						k = ++k;
					}
					
					if (paramName.equals("sub" + j)) {
						subs.get(j-1).setLine_number(paramNumber);
						subs.get(j-1).setFileName(fileName);
						subs.get(j-1).setLine_sub_number(Integer.parseInt(paramValue));
						subs.get(j-1).setLine_min(paramMin);
						l = j;
						j = ++j;
					}
									
					if (paramName.equals("line" + l)) {
						subs.get(l-1).setLine_text(paramValue);
					}
				}
			}
		}
	}
	
	// GETTERS SETTERS
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Enumeration<String> getParamNames() {
		return paramNames;
	}

	public void setParamNames(Enumeration<String> paramNames) {
		this.paramNames = paramNames;
	}

	public ArrayList<BDDInfo> getSubs() {
		return subs;
	}

	public void setSubs(ArrayList<BDDInfo> subs) {
		this.subs = subs;
	}
}
