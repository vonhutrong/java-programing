package th_ltm.th2.bai2;

import java.net.SocketException;
import java.util.Scanner;

import th_ltm.th2.UDPServer;

public class Server2 {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("server port:");
			int serverPort = Integer.parseInt(sc.nextLine());
			UDPServer server = new UDPServer(serverPort, 2048, ExpressionProcessing.getInstance());
			server.start();
			System.out.println("server started");
		} catch (SocketException se) {
			System.out.println("cannot start server\nerror:"+ se.getMessage());
		}
	}

}
