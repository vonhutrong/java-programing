package th_ltm.th2.bai1;

import java.net.SocketException;
import java.util.Scanner;

import th_ltm.th2.UDPServer;

public class Server1 {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("server port:");
			int serverPort = Integer.parseInt(sc.nextLine());
			UDPServer server = new UDPServer(serverPort, 2048, StringProcessing.getInstance());
			server.start();
			System.out.println("server started");
			while (true);
		} catch (SocketException se) {
			System.out.println("cannot start server\nerror:"+ se.getMessage());
		}
	}

}
