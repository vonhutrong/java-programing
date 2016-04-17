package th_ltm.th2.bai2;

import java.net.SocketException;

import th_ltm.th2.UDPServer;

public class Server2 {

	public static void main(String[] args) {
		try {
			UDPServer server = new UDPServer(50000, 2048, ExpressionProcessing.getInstance());
			server.start();
			System.out.println("server started");
		} catch (SocketException se) {
			System.out.println("cannot start server\nerror:"+ se.getMessage());
		}
	}

}
