package th_ltm.th2.bai3.client.event;

public class MessageEvent {
	private String sender;
	private String message;
	public MessageEvent(String sender, String message) {
		this.sender = sender;
		this.message = message;
	}
	public String getSender() {
		return sender;
	}
	public String getMessage() {
		return message;
	}
	
}
