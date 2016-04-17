package th_ltm.th1;

import java.io.IOException;
import java.net.ServerSocket;

public class TCPServer {
	
	protected ServerSocket mSocket;
	protected IStringProcessing mStringProcessing;
	
	public TCPServer(int port, IStringProcessing stringProcessing) throws IOException {
		mSocket = new ServerSocket(port);
		mStringProcessing = stringProcessing;
	}
	
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						SocketHelper connection = new SocketHelper(mSocket.accept());
						
						new ConnectionHandler(mSocket, connection, mStringProcessing).start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
}
