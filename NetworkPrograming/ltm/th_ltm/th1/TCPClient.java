package th_ltm.th1;

import java.io.IOException;
import java.net.UnknownHostException;

public class TCPClient {
	
	private SocketHelper mSocket;
	
	public TCPClient(String host, int port) throws UnknownHostException, IOException {
		mSocket = new SocketHelper(host, port);
	}
	
	public void send(String s) throws IOException {
		mSocket.send(s);
	}
	
	public String receiveString() throws IOException {
		return mSocket.receiveString();
	}
	
	public void close() throws IOException {
		mSocket.close();
	}
	
	/*public static void main(String[] args) {
		
		TCPClient client;
		try {
			client = new TCPClient("localhost", 5000);
			System.out.println("connected to server");
			
			client.send("vo nhu trong 3");
			System.out.println("sent data");
			
			String s = client.receiveString();
			System.out.println("result: " + s);
			
			client.close();
			System.out.println("client stoped");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}*/
}
