package th_ltm.th2.bai3.server.chat_event;

import th_ltm.th2.bai3.server.connection_manager.UDPConnection;

public class ErrorEvent {
	private String error;
	private UDPConnection connection;
	public ErrorEvent(String error, UDPConnection connection) {
		this.error = error;
		this.connection = connection;
	}
	public String getError() {
		return error;
	}
	public UDPConnection getConnection() {
		return connection;
	}
}
