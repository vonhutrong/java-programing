package th_ltm.th2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public interface ITransferMethod {
	void transfer(DatagramPacket ReceivePacket, DatagramSocket ServerSocket) throws IOException;
}
