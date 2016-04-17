package th_ltm.th1.bai3.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import th_ltm.th1.bai3.server.ConnectionThread;

public class ClientChat {
	private Socket socket;
	private InetAddress serverAddress;
	private int serverPort;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private String name;
	
	public ClientChat(InetAddress serverAddress, int serverPort, String name) throws IOException {
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		this.socket = new Socket(this.serverAddress, this.serverPort);
		this.inputStream = new DataInputStream(this.socket.getInputStream());
		this.outputStream = new DataOutputStream(this.socket.getOutputStream());
		this.name = name;
	}
	
	public boolean sendString(String s) {
		try {
			this.outputStream.writeUTF(s);
			return true;
		} catch (IOException e) {
		}
		return false;
	}
	
	public void join() {
		String s = "join;" + this.name;
		sendString(s);
	}
	
	public void sendMessage(String message, String partner) {
		String s = "msg;" + partner + ";" + message;
		sendString(s);
	}
	
	public String receiveString() {
		try {
			String receiveString = this.inputStream.readUTF();
			return receiveString;
		} catch (IOException e) {
		}
		return null;
	}
	
	public void close() {
		try {
			this.inputStream.close();
			this.outputStream.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// connect to server
		String name;
		System.out.print("your name: ");
		name = sc.nextLine();
		ClientChat client = null;
		while (true) {
			System.out.print("server address: ");
			String serverAddressString = sc.nextLine();
			System.out.print("server port: ");
			String serverPortString = sc.nextLine();
			
			try {
				InetAddress serverAddress = InetAddress.getByName(serverAddressString);
				int serverPort = Integer.parseInt(serverPortString);
				client = new ClientChat(serverAddress, serverPort, name);
				break;
			} catch (UnknownHostException e) {
				System.out.println("server address is invalid");
			} catch (IOException e) {
				System.out.println("cannot connect to server");
			}
		}
		
		// join to server
		client.join();
		
		// new thread to send message
		Thread sendThread = new SendThead(client, sc);
		sendThread.start();
		
		// this thread will show receive message
		while (true) {
			String receiveString = client.receiveString();
			String cmd = receiveString.substring(0, receiveString.indexOf(';'));
			receiveString = receiveString.substring(receiveString.indexOf(';') + 1);
			if ("msg".equals(cmd)) {
				String partner = receiveString.substring(0, receiveString.indexOf(';'));
				String msg = receiveString.substring(receiveString.indexOf(';') + 1);
				System.out.println(partner + ">>" + msg);
			} else if ("joined".equals(cmd)) {
				System.out.println("connected to server chat");
			} else if ("notfound".equals(cmd)) {
				String partner = receiveString.substring(0, receiveString.indexOf(';'));
				System.out.println("not found " + partner);
			}
		}
	}
	
}
