package th_ltm.th2.bai3.client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;

import th_ltm.th2.DataChangeEvent;
import th_ltm.th2.IDataListener;
import th_ltm.th2.UDPClient;
import th_ltm.th2.bai3.client.event.IClientChatListener;
import th_ltm.th2.bai3.client.event.MessageEvent;
import th_ltm.th2.bai3.client.event.PartnerNotFoundEvent;
import th_ltm.th2.bai3.server.ChatCommand;
import th_ltm.th2.bai3.server.chat_event.IChatListener;

public class ClientChat extends UDPClient {
	private Vector<IClientChatListener> chatListeners;

	public ClientChat(int serverPort, int bufferSize) throws SocketException,
			UnknownHostException {
		super(serverPort, bufferSize);
	}
		
	@Override
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while (true) {
						String receiveStr = receiveString();
						
						String[] parts = receiveStr.split(ChatCommand.DELIM);
						
						if (parts.length == 1 && ChatCommand.JOIN_OK.equals(parts[0])) {
							fireOnJoinOkEvent();
						} else if (parts.length == 1 && ChatCommand.JOIN_ERROR.equals(parts[0])) {
							fireOnJoinErrorEvent();
						} else if (parts.length == 1 && ChatCommand.OUT_OK.equals(parts[0])) {
							fireOnOutOkEvent();
						} else if (parts.length == 3 && ChatCommand.MESSAGE.equals(parts[0])) {
							fireOnMessageEvent(new MessageEvent(parts[1], parts[2]));
						} else if (parts.length == 2 && ChatCommand.PARTNER_NOT_FOUND.equals(parts[0])) {
							fireOnPartnerNotFound(new PartnerNotFoundEvent(parts[1]));
						} else if (parts.length == 1 && ChatCommand.SYNTAX_ERROR.equals(parts[0])) {
							fireOnSyntaxError();
						}
					}
				} catch (IOException e) {
				}
			}
		}).start();
	}

	@Override
	protected void initializeListListeners() {
		chatListeners = new Vector<IClientChatListener>();
	}
	public void addChatListener(IClientChatListener listener) {
		chatListeners.add(listener);
	}
	public void removeChatListener(IClientChatListener listener) {
		chatListeners.remove(listener);
	}
	private boolean haveChatListener() {
		return chatListeners != null && chatListeners.size() > 0;
	}
	private void fireOnJoinOkEvent() {
		if (!haveChatListener())
			return;
		for (IClientChatListener listener : chatListeners)
			listener.onJoinOk();
	}
	private void fireOnJoinErrorEvent() {
		if (!haveChatListener())
			return;
		for (IClientChatListener listener : chatListeners)
			listener.onJoinError();
	}
	private void fireOnOutOkEvent() {
		if (!haveChatListener())
			return;
		for (IClientChatListener listener : chatListeners)
			listener.onOutOk();
	}
	private void fireOnMessageEvent(MessageEvent messageEvent) {
		if (!haveChatListener())
			return;
		for (IClientChatListener listener : chatListeners)
			listener.onMessage(messageEvent);
	}
	private void fireOnPartnerNotFound(PartnerNotFoundEvent e) {
		if (!haveChatListener())
			return;
		for (IClientChatListener listener : chatListeners)
			listener.onPartnerNotFound(e);
	}
	private void fireOnSyntaxError() {
		if (!haveChatListener())
			return;
		for (IClientChatListener listener : chatListeners)
			listener.onSyntaxError();
	}

}
