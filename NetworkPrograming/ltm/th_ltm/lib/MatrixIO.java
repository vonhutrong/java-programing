package th_ltm.lib;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Jama.LUDecomposition;
import Jama.Matrix;

/**
 * @author trongvn13
 *
 */
public class MatrixIO {
	public static double[][] getMatrixFromFile(ReadTextFile file) {
		int rows = file.nextInt();
		int cols = file.nextInt();
		double[][] matrix = new double[rows][cols];
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				matrix[i][j] = file.nextDouble();
			}
		}
		return matrix;
	}
	
	public static String matrixToString(double[][] matrix) {
		if (null == matrix)
			return null;
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		if (0 == rows && 0 == cols)
			return null;
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				sb.append(matrix[i][j] + "\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static List<String> matrixToListString(double[][] matrix) {
		if (null == matrix)
			return null;
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		if (0 == rows && 0 == cols)
			return null;
		
		List<String> list = new ArrayList<String>();
		StringBuilder sb;
		for (int i = 0; i < rows; ++i) {
			sb = new StringBuilder();
			for (int j = 0; j < cols; ++j) {
				sb.append(matrix[i][j] + "\t");
			}
			list.add(sb.toString());
		}
		return (list.size() == 0) ? null : list;
	}
	/*
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("filepath:");
		String filePath = sc.nextLine();
		try {
			ReadTextFile file = new ReadTextFile(filePath);
			double[][] matrix1 = MatrixIO.getMatrixFromFile(file);
			file.close();
			
			Matrix a = new Matrix(matrix1);
			a.print(10, 2);			
			Matrix b = a.inverse();
			String s = MatrixIO.matrixToString(b.getArray());
			System.out.println(s);
			
			try {
				WriteTextFile writer = new WriteTextFile(filePath);
				writer.append(MatrixIO.matrixToListString(b.getArray()));
				writer.close();
				System.out.println("wrote to file");
			} catch (IOException e) {
				System.out.println("cannot write to file");
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
	}
	*/
}
