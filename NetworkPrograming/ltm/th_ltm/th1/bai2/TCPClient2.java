package th_ltm.th1.bai2;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import th_ltm.th1.TCPClient;

public class TCPClient2 {
	public static void main(String[] args) {
		TCPClient client;
		String host;
		int port;
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("type host: ");
			host = sc.nextLine();
			System.out.print("type port: ");
			port = sc.nextInt();
			sc.nextLine();
			try {
				client = new TCPClient(host, port);
				System.out.println("connected to server");
				break;
			} catch (UnknownHostException e) {
			} catch (IOException e) {
			}
			System.out.println("can not connect to server");
		}
		
		String outputString;
		while (true) {
			System.out.print("type expression: ");
			outputString = sc.nextLine();
			
			if (outputString == null || outputString.length() <= 0)
				break;
			
			try {
				client.send(outputString);
				
				String inputString = client.receiveString();
				System.out.println("result: " + inputString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		sc.close();
		try {
			client.close();
			System.out.println("client stoped");
		} catch (IOException e) {
			System.out.println("can not stop client");
		}
	}
}
