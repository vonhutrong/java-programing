package th_ltm.th2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

public class UDPClient {
	protected DatagramSocket mSocket;
	protected InetAddress mAddress;
	private byte[] mReceiveBuffer;
	private int mBufferSize;
	protected int mServerPort;
	protected DatagramPacket mReceivePacket;
	protected Vector<IDataListener> mListListeners;
	
	public UDPClient(int ServerPort, int BufferSize) throws SocketException, UnknownHostException {
		mServerPort = ServerPort;
		mAddress = InetAddress.getByName("localhost");
		mBufferSize = BufferSize;
		mSocket = new DatagramSocket();
		mReceiveBuffer = new byte[mBufferSize];
		mReceivePacket = new DatagramPacket(mReceiveBuffer, mBufferSize);
		initializeListListeners();
	}
	
	protected void initializeListListeners() {
		mListListeners = new Vector<IDataListener>();
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
		mSocket.receive(mReceivePacket);
		return new String(mReceivePacket.getData(), 0, mReceivePacket.getLength());
	}
	
	public void sendString(String string) throws IOException {
		byte[] buf = string.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, mAddress, mServerPort);
		mSocket.send(packet);
	}
	
	public void addDataListener(IDataListener Listener) {
		mListListeners.add(Listener);
	}
	
	public void removeDataListener(IDataListener Listener) {
		mListListeners.remove(Listener);
	}
	
	protected boolean haveDataListener() {
		return mListListeners != null && mListListeners.size() > 0;
	}
	
	private void fireDataChangeEvent(DataChangeEvent e) {
		if (!haveDataListener()) {
			return;
		}
		for (IDataListener listener : mListListeners) {
			listener.onDataChange(e);
		}
	}

	public void close() {
		if (mSocket != null)
			mSocket.close();
	}
	
}
