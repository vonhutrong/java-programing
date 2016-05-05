package th_ltm.lib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author trongvn13
 *
 */
public class WriteTextFile {
	private String filePath;
	private BufferedWriter writer;
	
	public WriteTextFile(String filePath) throws IOException {
		this.filePath = filePath;
		writer = new BufferedWriter(new FileWriter(this.filePath, true));
	}
	
	public void append(String s) throws IOException {
		writer.append(s);
	}
	
	public void append(List<String> listStr) throws IOException {
		writer.newLine();
		for (String string : listStr) {
			writer.write(string);
			writer.newLine();
		}
	}
	
	public void close() {
		if (null == writer)
			return;
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
