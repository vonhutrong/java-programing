package th_ltm.th1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketHelper extends Socket{
	private Socket mSocket = null;
	private DataOutputStream mOutputStream = null;
	private DataInputStream mInputStream = null;
	
	public SocketHelper(Socket socket) throws IOException {
		mSocket = socket;
		initStreams();
	}
	
	public SocketHelper(String host, int port) throws UnknownHostException, IOException {
		mSocket = new Socket(host, port);
		initStreams();
	}
	
	private void initStreams() throws IOException {
		mOutputStream = new DataOutputStream(mSocket.getOutputStream());
		mInputStream = new DataInputStream(mSocket.getInputStream());
	}
	
	private void closeStreams() throws IOException {
		mOutputStream.close();
		mInputStream.close();
	}
	
	public void close() throws IOException {
		super.close();
		closeStreams();
		if (mSocket != null)
			mSocket.close();
	}
	
	public void send(String string) throws IOException {
		mOutputStream.writeUTF(string);
		/*try {
			mOutputStream.writeUTF(string);
			return true;
		} catch (NullPointerException nulle) {
		}
		return false;*/
	}
	
	public String receiveString() throws IOException {
		try {
			return mInputStream.readUTF();
		} catch (SocketException se) {
		}
		return null;
	}
}