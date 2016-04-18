package th_ltm.th2.bai1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import th_ltm.th2.DataChangeEvent;
import th_ltm.th2.IDataListener;
import th_ltm.th2.UDPClient;
import th_ltm.th2.bai2.Client2;

public class Client1 implements IDataListener {
	private UDPClient mClient;
	
	public Client1(InetAddress serverAddress, int serverPort) throws SocketException, UnknownHostException {
		mClient = new UDPClient(serverAddress, serverPort, 2048);
		mClient.addDataListener(this);
		mClient.start();
	}
	
	private void sendString(String inputString) throws IOException {
		mClient.sendString(inputString);
	}
	
	@Override
	public void onDataChange(DataChangeEvent e) {
		System.out.println("server:" + e.getData()); 
	}
	
	public void close() {
		mClient.close();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Client1 client = null;
		try {
			while (true) {
				try {
					System.out.print("server address:");
					InetAddress serverAddress = InetAddress.getByName(sc.nextLine());
					System.out.print("server port:");
					int serverPort = Integer.parseInt(sc.nextLine());
					client = new Client1(serverAddress, serverPort);
					
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
				System.out.print("input:");
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
