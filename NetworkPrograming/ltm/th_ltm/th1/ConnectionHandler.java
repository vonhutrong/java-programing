package th_ltm.th1;

import java.io.IOException;
import java.net.ServerSocket;

public class ConnectionHandler extends Thread {
	
	private ServerSocket mServer;
	private SocketHelper mConnection;
	private IStringProcessing mStringProcessing;
	
	public ConnectionHandler(ServerSocket server, SocketHelper connection, IStringProcessing stringProcessing) {
		mServer = server;
		mConnection = connection;
		mStringProcessing = stringProcessing;
	}
	
	@Override
	public void run() {
		super.run();
		while (true) {
			try {
				String inputStr = mConnection.receiveString();
	
				mConnection.send(process(inputStr));
			} catch (IOException e) {
				try {
					mConnection.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	private String process(String s) {
		return mStringProcessing.process(s);
	}
}
