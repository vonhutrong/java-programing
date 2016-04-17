package th_ltm.th1.bai3.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
	private String name;
	private Socket connection;
	private DataOutputStream outputStream;
	
	public Connection(Socket connection, String name) throws IOException {
		this.connection = connection;
		this.name = name;
		this.outputStream = new DataOutputStream(this.connection.getOutputStream());
	}

	public String getName() {
		return this.name;
	}
	
	public boolean sendString(String s) {
		try {
			this.outputStream.writeUTF(s);
			return true;
		} catch (IOException e) {
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		Connection conn = (Connection) obj;
		return name.equals(conn.getName());
	}
	
}
