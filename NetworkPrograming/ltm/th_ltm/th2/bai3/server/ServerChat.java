package th_ltm.th2.bai3.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

import th_ltm.th2.UDPServer;
import th_ltm.th2.bai3.server.chat_event.ChatEvent;
import th_ltm.th2.bai3.server.chat_event.ChatProcessing;
import th_ltm.th2.bai3.server.chat_event.ErrorEvent;
import th_ltm.th2.bai3.server.chat_event.IChatListener;
import th_ltm.th2.bai3.server.chat_event.JoinEvent;
import th_ltm.th2.bai3.server.chat_event.OutEvent;
import th_ltm.th2.bai3.server.chat_event.PartnerNotFoundEvent;
import th_ltm.th2.bai3.server.connection_manager.UDPConnection;
import th_ltm.th2.bai3.server.connection_manager.UDPListConnections;

public class ServerChat extends UDPServer implements IChatListener {
	private UDPListConnections listConnections;
	private ChatProcessing chatProcessing;

	public ServerChat(int port, int bufferSize)
			throws SocketException {
		super(port, bufferSize, null);
		listConnections = new UDPListConnections();
		chatProcessing = new ChatProcessing(this);
		chatProcessing.addListener(this);
	}
	
	public DatagramPacket receiveData() throws IOException {
		this.socket.receive(this.receivePacket);
		return this.receivePacket;
	}
	
	public void sendString(String string, InetAddress address, int port) throws IOException {
		byte[] buf = string.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		socket.send(packet);
	}
	
	public UDPConnection getConnectionByName(String name) {
		return listConnections.getConnectionByName(name);
	}

	public String getNameByConnection(UDPConnection receiverConnection) {
		return listConnections.getNameByConnection(receiverConnection);
	}
	
	@Override
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while (true)
						chatProcessing.transfer();
				} catch (IOException e) {}
			}
		}).start();
	}

	@Override
	public void onJoin(JoinEvent e) {
		try {
			if (listConnections.addConnection(new UDPConnection(e.getAddress(), e.getPort()), e.getName()))
				sendString(ChatCommand.JOIN_OK, e.getAddress(), e.getPort());
			else
				sendString(ChatCommand.JOIN_ERROR, e.getAddress(), e.getPort());
		} catch (IOException e1) { }
	}

	@Override
	public void onOut(OutEvent e) {
		UDPConnection conn = getConnectionByName(e.getName());
		try {
			if (listConnections.removeConnection(e.getName()))
				sendString(ChatCommand.OUT_OK, conn.getAddress(), conn.getPort());
		} catch (IOException e1) { }
	}

	@Override
	public void onChat(ChatEvent e) {
		StringBuilder sb = new StringBuilder();
		sb.append(ChatCommand.MESSAGE);
		sb.append(ChatCommand.DELIM);
		sb.append(e.getSenderName());
		sb.append(ChatCommand.DELIM);
		sb.append(e.getMessage());
		UDPConnection receiverConn = getConnectionByName(e.getReceiverName());
		try {
			sendString(sb.toString(), receiverConn.getAddress(), receiverConn.getPort());
		} catch (IOException e1) { }
	}

	@Override
	public void onPartnerNotFound(PartnerNotFoundEvent e) {
		UDPConnection senderConn = getConnectionByName(e.getSender());
		StringBuilder sb = new StringBuilder();
		sb.append(ChatCommand.PARTNER_NOT_FOUND);
		sb.append(ChatCommand.DELIM);
		sb.append(e.getReceiver());
		try {
			sendString(sb.toString(), senderConn.getAddress(), senderConn.getPort());
		} catch (IOException e1) { }
	}

	@Override
	public void onError(ErrorEvent e) {
		UDPConnection conn = e.getConnection();
		try {
			sendString(e.getError(), conn.getAddress(), conn.getPort());
		} catch (IOException e1) {}
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("server port:");
			int serverPort = Integer.parseInt(sc.nextLine());
			ServerChat serverChat = new ServerChat(serverPort, 2048);
			serverChat.start();
			System.err.println("server started");
		} catch (Exception ex) {
			System.out.println("cannot start server");
		}
	}

}
