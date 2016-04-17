package th_ltm.th2.bai3.server.chat_event;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Vector;

import th_ltm.th2.bai3.server.ChatCommand;
import th_ltm.th2.bai3.server.ServerChat;
import th_ltm.th2.bai3.server.connection_manager.UDPConnection;

public class ChatProcessing {

	private ServerChat serverChat;
	private Vector<IChatListener> listeners;
	
	public ChatProcessing(ServerChat serverChat) {
		this.serverChat = serverChat;
		this.listeners = new Vector<>();
	}
	
	public void transfer() throws IOException {
		// receive data
		DatagramPacket receivePacket = serverChat.receiveData();
		InetAddress address = receivePacket.getAddress();
		int port = receivePacket.getPort();
		String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();

		String[] parts = receiveString.split(ChatCommand.DELIM);
		
		// if receive string is not command (first part is not command)
		// this is wrong syntax
		// send error message and finish
		String command = parts[0];
		if (ChatCommand.MESSAGE.equals(command) && parts.length == 3) {
			// MESSAGE + DELIM + RECEIVER + DELIM + MSG, ex: msg;receiver;hello
			String senderName = serverChat.getNameByConnection(new UDPConnection(address, port));
			if (serverChat.getConnectionByName(parts[1]) == null) {
				fireOnPartnerNotFound(new PartnerNotFoundEvent(senderName, parts[1]));
			} else {
				fireOnChatEvent(new ChatEvent(senderName, parts[1], parts[2]));
			}
		} else if (ChatCommand.JOIN.equals(command) && parts.length == 2) {
			// JOIN + DELIM + name, ex: join;trong
			fireOnJoinEvent(new JoinEvent(address, port, parts[1]));
		} else if (ChatCommand.OUT.equals(command) && parts.length == 2) {
			// OUT + DELIM + name, ex: out;trong
			fireOnOutEvent(new OutEvent(parts[1]));
		} else {
			fireOnError(new ErrorEvent(ChatCommand.SYNTAX_ERROR, new UDPConnection(address, port)));
		}
		
	}
	
	private boolean haveListeners(){
		return listeners != null && listeners.size() > 0;
	}
	public void addListener(IChatListener listener) {
		listeners.add(listener);
	}
	public void removeListenser(IChatListener listner) {
		listeners.remove(listner);
	}
	public void fireOnJoinEvent(JoinEvent e) {
		if (!haveListeners())
			return;
		for (IChatListener listener : listeners) {
			listener.onJoin(e);
		}
	}
	public void fireOnOutEvent(OutEvent e) {
		if (!haveListeners())
			return;
		for (IChatListener listener : listeners) {
			listener.onOut(e);
		}
	}
	public void fireOnChatEvent(ChatEvent e) {
		if (!haveListeners())
			return;
		for (IChatListener listener : listeners) {
			listener.onChat(e);
		}
	}	
	private void fireOnPartnerNotFound(PartnerNotFoundEvent e) {
		if (!haveListeners())
			return;
		for (IChatListener listener : listeners) {
			listener.onPartnerNotFound(e);
		}
	}
	public void fireOnError(ErrorEvent e) {
		if (!haveListeners())
			return;
		for (IChatListener listener : listeners) {
			listener.onError(e);
		}
	}
}
