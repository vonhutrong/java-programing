package th_ltm.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author trongvn13
 *
 */
public class ReadTextFile {
	private String filePath;
	private Scanner sc;
	
	public ReadTextFile(String filePath) throws FileNotFoundException {
		this.filePath = filePath;
		sc = new Scanner(new File(filePath));
	}

	public boolean hasNextInt() {
		return sc.hasNextInt();
	}
	
	public int nextInt() {
		return sc.nextInt();
	}
	
	public boolean hasNextFloat() {
		return sc.hasNextFloat();
	}
	
	public float nextFloat() {
		return sc.nextFloat();
	}
	
	public boolean hasNextDouble() {
		return sc.hasNextDouble();
	}
	
	public double nextDouble() {
		return sc.nextDouble();
	}
	
	public void close() {
		if (null == sc)
			return;
		sc.close();
	}
	/*
	public static void main(String[] args) {
		String filePath;
		Scanner sc = new Scanner(System.in);
		System.out.print("file name:");
		filePath = sc.nextLine();
		try {
			ReadTextFile file = new ReadTextFile(filePath);
			while (file.hasNextFloat()) {
				System.out.println(file.nextFloat() + "  ");
			}
			System.out.println("end of file");
		} catch (FileNotFoundException e) {
			System.err.println("cannot open file");
			e.printStackTrace();
		}
	}
*/
}
