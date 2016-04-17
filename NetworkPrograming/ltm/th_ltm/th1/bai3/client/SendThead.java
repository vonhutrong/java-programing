package th_ltm.th1.bai3.client;

import java.util.Scanner;

public class SendThead extends Thread {
	private ClientChat client;
	private Scanner sc;
	private Thread thread;

	public SendThead(ClientChat client, Scanner sc) {
		this.client = client;
		this.sc = sc;
	}
	
	@Override
	public void run() {
		super.run();
		while (true) {
			String s = sc.nextLine();
			s = "msg;" + s;
			this.client.sendString(s);
		}
	}
	
	@Override
	public synchronized void start() {
		if (thread == null) {
			thread = new Thread(this, "SendThread");
			thread.start();
		}
	}
}
