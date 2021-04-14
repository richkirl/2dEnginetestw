  
package engine.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
	public static String loadAsString(String path) {
		StringBuilder result = new StringBuilder();
		String line;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null) {
				result.append(line).append("\n");
			}
		} catch (IOException e) {
			System.err.println("Couldn't find the file at " + path);
		}
		
		return result.toString();
	}
}
