package th_ltm.th2.bai3.client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.InetAddress;

import th_ltm.th2.bai3.client.event.IClientChatListener;
import th_ltm.th2.bai3.client.event.MessageEvent;
import th_ltm.th2.bai3.client.event.PartnerNotFoundEvent;
import th_ltm.th2.bai3.server.ChatCommand;

public class ClientChatDemo implements IClientChatListener{
	private ClientChat clientChat;
	private String name;
	private boolean joinOk = false;
	
	public ClientChatDemo(InetAddress serverAddress, int serverPort) throws SocketException, UnknownHostException {
		clientChat = new ClientChat(serverAddress, serverPort, 2048);
		clientChat.addChatListener(this);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void start() {
		clientChat.start();
	}
	
	public void sendString(String string) {
		try {
			if (!joinOk) {
				name = string;
				string = ChatCommand.JOIN + ChatCommand.DELIM + string;
			} else if (joinOk && "out".equals(string)) {
				string = ChatCommand.OUT + ChatCommand.DELIM + name;
			} else if (joinOk) {
				string = ChatCommand.MESSAGE + ChatCommand.DELIM + string;
			}
			clientChat.sendString(string);
		} catch (IOException e) { }
	}
	
	public void printResponse(String s) {
		System.out.println("\n>>" + s + "\n");
	}
	
	@Override
	public void onJoinOk() {
		joinOk = true;
		printResponse("you joined to server");
	}
	
	@Override
	public void onJoinError() {
		joinOk = false;
		printResponse("the name you already type is exist, please type another name");
	}
	
	@Override
	public void onOutOk() {
		joinOk = false;
		printResponse("now you no longer server chat");
	}
	
	@Override
	public void onMessage(MessageEvent e) {
		printResponse(e.getSender() + ":" + e.getMessage());
	}
	
	@Override
	public void onPartnerNotFound(PartnerNotFoundEvent e) {
		printResponse(e.getPartnerName() + " not found");
	}
	
	@Override
	public void onSyntaxError() {
		printResponse("syntax error");
	}
	
	public void close() {
		sendString(ChatCommand.OUT);
		clientChat.close();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ClientChatDemo client = null;
		
		// input server address and server port
		while (true) {
			try {
				System.out.print("server address:");
				InetAddress serverAddress = InetAddress.getByName(sc.nextLine());
				System.out.print("server port:");
				int serverPort = Integer.parseInt(sc.nextLine());
				client = new ClientChatDemo(serverAddress, serverPort);
				System.out.println("create client successfully");
				break;
			} catch (UnknownHostException ex) {
				System.out.println("server address invalid");
			} catch (NumberFormatException ex2) {
				System.out.println("server port invalid");
			} catch (SocketException e) {
				System.out.println("cannot create client");
			}
		}
		
		client.start();
		System.out.println("type you name to login to server\n"
				+ "after that, you can type 'out' to logout from server");
		
		while (true) {
			String inputString = sc.nextLine();
			inputString = inputString.trim();
			client.sendString(inputString);
		}
		
	}
}
