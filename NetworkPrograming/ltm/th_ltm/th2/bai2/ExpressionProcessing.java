package th_ltm.th2.bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import th_ltm.th2.ITransferMethod;

public class ExpressionProcessing implements ITransferMethod {
	public static final String DELIM = ";";
	private static ExpressionProcessing mInstance = new ExpressionProcessing();
	private ScriptEngineManager mEngineMgr = null;
	private ScriptEngine mEngine = null;
	
	private ExpressionProcessing() {
		mEngineMgr = new ScriptEngineManager();
		mEngine = mEngineMgr.getEngineByName("JavaScript");
	}
	
	public static ExpressionProcessing getInstance() {
		if (mInstance == null)
			mInstance = new ExpressionProcessing();
		return mInstance;
	}
	
	public String process(String string) {
		String result = null;
		if (string != null && string.length() > 0){
			try {
				result = mEngine.eval(string).toString();
			} catch (ScriptException e) {
				result = "syntax error";
			}
		}
		return result;
	}
	
	@Override
	public void transfer(DatagramPacket ReceivePacket,
			DatagramSocket ServerSocket) throws IOException {
		// receive data
		ServerSocket.receive(ReceivePacket);

		// get string
		String receiveString = new String(ReceivePacket.getData(), 0,
				ReceivePacket.getLength());

		// process string
		String result = process(receiveString);

		// send result
		byte[] sendBuffer = result.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendBuffer,
				sendBuffer.length, ReceivePacket.getAddress(),
				ReceivePacket.getPort());
		ServerSocket.send(sendPacket);
	}

}
