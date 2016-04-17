package th_ltm.th1.bai1;

import java.io.IOException;
import java.util.Scanner;

import th_ltm.th1.TCPServer;

public class TCPSever1 {
	public static void main(String[] args) {
		TCPServer server;
		int port;
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("type port of server: ");
			port = sc.nextInt();
			sc.nextLine();
			try {
				server = new TCPServer(port, StringProcessing.getInstance());
				break;
			} catch (IOException e) {
				System.out.println("can not starting server with that port");
			}
		}
		
		server.start();
		System.out.println("server started");
		
		sc.close();
	}
}
