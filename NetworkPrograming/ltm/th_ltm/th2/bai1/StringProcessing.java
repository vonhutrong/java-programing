package th_ltm.th2.bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import th_ltm.th2.ITransferMethod;

public class StringProcessing implements ITransferMethod {
	public static final String DELIM = ";";
	private static StringProcessing mInstance = new StringProcessing();
	
	private StringProcessing() {}
	
	public static int countWords(String s) {
		int wordCount = 0;
		boolean word = false;
		int endOfLine = s.length() - 1;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
				word = true;
			} else if (!Character.isLetter(s.charAt(i)) && word) {
				wordCount++;
				word = false;
			} else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
				wordCount++;
			}
		}
		return wordCount;
	}
	
	@Override
	public void transfer(DatagramPacket ReceivePacket, DatagramSocket ServerSocket) throws IOException {
		// receive data
		ServerSocket.receive(ReceivePacket);
		
		// get string
		String receiveString = new String(ReceivePacket.getData(), 0, ReceivePacket.getLength());
		System.out.println("server received:" + receiveString);
		
		// process string
		StringBuilder sb = new StringBuilder();
		sb.append(receiveString.toUpperCase());
		sb.append(StringProcessing.DELIM);
		sb.append(receiveString.toLowerCase());
		sb.append(StringProcessing.DELIM);
		sb.append(countWords(receiveString));
		
		// send result
		byte[] sendBuffer = sb.toString().getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ReceivePacket.getAddress(), ReceivePacket.getPort());
		ServerSocket.send(sendPacket);
		
	}

	public static ITransferMethod getInstance() {
		if (mInstance == null)
			mInstance = new StringProcessing();
		return mInstance;
	}
}
