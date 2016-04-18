package th_ltm.th2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
	private ITransferMethod transferMethod;
	
	protected int port;
	protected DatagramSocket socket;
	protected DatagramPacket receivePacket;
	
	public UDPServer(int port, int bufferSize, ITransferMethod transferMethod) throws SocketException {
		this.port = port;
		this.transferMethod = transferMethod;
		socket = new DatagramSocket(this.port);
		receivePacket = new DatagramPacket( new byte[bufferSize], bufferSize);
	}
	
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while (true) {
						transferMethod.transfer(receivePacket, socket);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void close() {
		if (socket != null)
			socket.close();
	}
	
}
