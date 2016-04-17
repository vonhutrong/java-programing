package th_ltm.th2.bai3.server.chat_event;

public class ChatEvent {
	private String senderName;
	private String receiverName;
	private String message;
	
	public ChatEvent(String senderName, String receiverName, String message) {
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.message = message;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public String getMessage() {
		return message;
	}
	
	
}
