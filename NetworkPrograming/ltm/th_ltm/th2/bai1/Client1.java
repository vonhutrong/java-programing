package th_ltm.th2.bai1;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import th_ltm.th2.DataChangeEvent;
import th_ltm.th2.IDataListener;
import th_ltm.th2.UDPClient;

public class Client1 implements IDataListener {
	private UDPClient mClient;
	
	public Client1() throws SocketException, UnknownHostException {
		mClient = new UDPClient(50000, 2048);
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
		try {
			Client1 client = new Client1();
			System.out.println("connected to server");
			
			String inputString;
			while (true) {
				inputString = sc.nextLine();
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
