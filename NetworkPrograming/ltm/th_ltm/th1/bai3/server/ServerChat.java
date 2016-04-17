package th_ltm.th1.bai3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.Vector;

public class ServerChat {
	private int port;
	private ServerSocket serverSocket;
	private ServerThread serverThread;
	private Vector<Connection> listConnections;
	
	public ServerChat(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(this.port);
		this.listConnections = new Vector<Connection>();
	}
	
	public void start() {
		this.serverThread = new ServerThread(this, this.serverSocket);
		this.serverThread.start();
	}
	
	public Vector<Connection> getAllConnections() {
		return this.listConnections.size() > 0 ? this.listConnections : null;
	}
	
	public void addConnection(Connection connection) {
		this.listConnections.add(connection);
	}
	
	public Connection searchConnection(String name) {
		for (Connection conn : this.listConnections) {
			if (name.equals(conn.getName())) {
				return conn;
			}
		}
		return null;
	}

	public void removeConnection(String name) {
		int index = this.listConnections.indexOf(name);
		if (index != -1) {
			this.listConnections.removeElementAt(index);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("server port:");
			String stringPort = sc.nextLine();
			try {
				int port = Integer.parseInt(stringPort);
				ServerChat server = new ServerChat(port);
				server.start();
				System.out.println("server started");
				break;
			} catch (NumberFormatException ne) {
				System.out.println("server port is invalid");
			} catch (IOException e) {
				System.out.println("cannot start sever");
			}
		}
		sc.close();
	}
}
