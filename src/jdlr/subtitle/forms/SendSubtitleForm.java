package jdlr.subtitle.forms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import jdlr.subtitle.beans.BDDInfo;

public class SendSubtitleForm {
	private String fileName;
	private Enumeration<String> paramNames = null;
	private ArrayList<String> lineNumber = null;
	private ArrayList<BDDInfo> subs = new ArrayList<BDDInfo>();
	private int j = 1;
	private int k;
	
	/**
	 * adding sub to ArrayList
	 * @param request
	 */
	public void getSubtitle(HttpServletRequest request) {
		fileName = request.getParameter(fileName);
		paramNames = request.getParameterNames();
		lineNumber = Collections.list(paramNames);
		
		
		
		// Creation object BDDInfo
		for (String line : lineNumber) {
			if (line.contains("number")) {
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
					if (paramName.equals("number"+j)) {
						subs.get(j-1).setLine_number(Integer.parseInt(paramValue));
						k = j;
						j = ++j;
					}
					if (paramName.equals("min"+k)) {
						subs.get(k-1).setLine_min(paramValue);
					}
					if (paramName.equals("line"+k)) {
						subs.get(k-1).setLine_text(paramValue);
					}
				}
			}
		}
	}
	
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
