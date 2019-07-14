package jdlr.subtitle.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reading file for the view
 * @author jdlr
 *
 */
public class SubtitlesHandler {
	private ArrayList<String> originalSubtitles = null;
	private ArrayList<String> translatedSubtitles = null;

	/**
	 * reading the file
	 * @param fileName name of the file
	 */
	public SubtitlesHandler(String fileName) {
		originalSubtitles = new ArrayList<String>();
		translatedSubtitles = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				// if (!line.contains("-->") && !line.matches("[0-9]+")) {
					originalSubtitles.add(line);
				//}			
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// GETTERS
	
	public ArrayList<String> getSubtitles() {
		return originalSubtitles;
	}
}
