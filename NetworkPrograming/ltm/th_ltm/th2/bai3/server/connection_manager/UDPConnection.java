package th_ltm.th2.bai3.server.connection_manager;

import java.net.InetAddress;

public class UDPConnection {
	private InetAddress address;
	private int port;

	public UDPConnection(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof UDPConnection)) {
			return false;
		}
		UDPConnection conn = (UDPConnection) obj;
		return address.equals(conn.address) && port == conn.port;
	}

	public int getPort() {
		return port;
	}

	public InetAddress getAddress() {
		return address;
	}
}
