package jdlr.subtitle.beans;

/**
 * File's each sub info
 * @author jdlr
 *
 */
public class BDDInfo {
	private int line_number;
	private int line_sub_number;
	private String line_min;
	private String line_text;
	private String fileName;
	
	public int getLine_number() {
		return line_number;
	}
	
	public void setLine_number(int line_number) {
		this.line_number = line_number;
	}
	
	public int getLine_sub_number() {
		return line_sub_number;
	}

	public void setLine_sub_number(int line_sub_number) {
		this.line_sub_number = line_sub_number;
	}

	public String getLine_min() {
		return line_min;
	}
	
	public void setLine_min(String line_min) {
		this.line_min = line_min;
	}

	public String getLine_text() {
		return line_text;
	}
	
	public void setLine_text(String line_text) {
		this.line_text = line_text;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
