package th_ltm.th1.bai3.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionThread extends Thread {
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private ServerChat serverChat;
	private String name;
	private Thread thread;
	
	public ConnectionThread(ServerChat serverChat, Socket socket) throws IOException {
		this.serverChat = serverChat;
		this.socket = socket;
		this.inputStream = new DataInputStream(this.socket.getInputStream());
		this.outputStream = new DataOutputStream(this.socket.getOutputStream());
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				String inputString = this.inputStream.readUTF();
				String command = inputString.substring(0, inputString.indexOf(';'));
				inputString = inputString.substring(inputString.indexOf(';') + 1);
				if ("join".equals(command)) {
					String name = inputString;
					this.name = name;
					this.serverChat.addConnection(new Connection(this.socket, name));
					this.outputStream.writeUTF("joined;");
				} else if ("out".equals(command)) {
					this.serverChat.removeConnection(this.name);
				} else if ("msg".equals(command)) {
					String partner = inputString.substring(0, inputString.indexOf(';'));
					inputString = inputString.substring(inputString.indexOf(';') + 1);
					Connection connectionOfPartner = this.serverChat.searchConnection(partner);
					if (connectionOfPartner != null) {
						// send msg to partner
						connectionOfPartner.sendString("msg;" + this.name + ";" + inputString + ";");
					} else {
						this.outputStream.writeUTF("notfound;" + partner + ";");
					}
				} else {
					this.outputStream.writeUTF("error");
				}
			} catch (IOException ex) {
				
			}
		}
	}
	
	@Override
	public synchronized void start() {
		if (thread == null) {
			thread = new Thread(this, "SendThread");
			thread.start();
		}
	}
	
}
