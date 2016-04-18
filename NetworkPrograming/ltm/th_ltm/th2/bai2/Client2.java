package th_ltm.th2.bai2;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.InetAddress;

import th_ltm.th2.DataChangeEvent;
import th_ltm.th2.IDataListener;
import th_ltm.th2.UDPClient;

public class Client2 implements IDataListener {
	private UDPClient mClient;
	
	public Client2(InetAddress serverAddress, int serverPort) throws SocketException, UnknownHostException {
		mClient = new UDPClient(serverAddress, serverPort, 2048);
		mClient.addDataListener(this);
		mClient.start();
	}
	
	private void sendString(String inputString) throws IOException {
		mClient.sendString(inputString);
	}
	
	@Override
	public void onDataChange(DataChangeEvent e) {
		System.out.println("result:" + e.getData()); 
	}
	
	public void close() {
		mClient.close();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Client2 client = null;
		try {
			while (true) {
				try {
					System.out.print("server address:");
					InetAddress serverAddress = InetAddress.getByName(sc.nextLine());
					System.out.print("server port:");
					int serverPort = Integer.parseInt(sc.nextLine());
					client = new Client2(serverAddress, serverPort);
					break;
				} catch (UnknownHostException hex) {
					System.out.println("server address is invalid");
				} catch (NumberFormatException nex) {
					System.out.println("server port is invalid");
				} catch (SocketException sex) {
					System.out.println("cannot create client");
				}
			}
			
			while (true) {
				String inputString = sc.nextLine();
				if (inputString.length() <= 0) {
					client.close();
					break;
				}
				client.sendString(inputString);
			}
		} catch (SocketException se) {
			System.out.println("cannot connect to server");
		} catch (IOException e) {
			System.out.println("cannot send string");
		}
		
		sc.close();
	}

}
