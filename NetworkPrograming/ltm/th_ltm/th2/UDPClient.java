package th_ltm.th2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

public class UDPClient {
	protected DatagramSocket socket;
	protected InetAddress serverAddress;
	protected int serverPort;
	protected DatagramPacket receivePacket;
	protected Vector<IDataListener> listeners;
	
	public UDPClient(InetAddress serverAddress, int serverPort, int bufferSize)
			throws SocketException, UnknownHostException {
		this.serverPort = serverPort;
		this.serverAddress = serverAddress;
		this.socket = new DatagramSocket();
		receivePacket = new DatagramPacket(new byte[bufferSize], bufferSize);
		initializeListListeners();
	}
	
	protected void initializeListListeners() {
		listeners = new Vector<IDataListener>();
	}
	
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while (true) {
						String receiveString = receiveString();
						fireDataChangeEvent(new DataChangeEvent(receiveString));
					}
				} catch (IOException e) {
				}
			}
		}).start();;
	}

	protected String receiveString() throws IOException {
		socket.receive(receivePacket);
		return new String(receivePacket.getData(), 0, receivePacket.getLength());
	}
	
	public void sendString(String string) throws IOException {
		byte[] buf = string.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddress, serverPort);
		socket.send(packet);
	}
	
	public void addDataListener(IDataListener Listener) {
		listeners.add(Listener);
	}
	
	public void removeDataListener(IDataListener Listener) {
		listeners.remove(Listener);
	}
	
	protected boolean haveDataListener() {
		return listeners != null && listeners.size() > 0;
	}
	
	private void fireDataChangeEvent(DataChangeEvent e) {
		if (!haveDataListener()) {
			return;
		}
		for (IDataListener listener : listeners) {
			listener.onDataChange(e);
		}
	}

	public void close() {
		if (socket != null)
			socket.close();
	}
	
}
