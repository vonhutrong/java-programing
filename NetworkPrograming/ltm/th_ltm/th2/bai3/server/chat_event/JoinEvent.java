package th_ltm.th2.bai3.server.chat_event;

import java.net.InetAddress;

public class JoinEvent {
	private InetAddress address;
	private int port;
	private String name;
	
	public JoinEvent(InetAddress address, int port, String name) {
		this.address = address;
		this.port = port;
		this.name = name;
	}

	public InetAddress getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public String getName() {
		return name;
	}
	
}
