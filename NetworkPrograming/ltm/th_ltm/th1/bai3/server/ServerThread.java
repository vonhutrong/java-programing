package th_ltm.th1.bai3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
	private ServerSocket serverSocket;
	private ServerChat serverChat;

	public ServerThread(ServerChat serverChat, ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		this.serverChat = serverChat;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket connection = this.serverSocket.accept();
				System.out.println("server have new connection");
				ConnectionThread connectionThread = new ConnectionThread(this.serverChat, connection);
				connectionThread.start();
			} catch (IOException ex) {

			}
		}
	}

}
