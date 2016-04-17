package th_ltm.th2.bai3.server.chat_event;

public class PartnerNotFoundEvent {
	private String sender;
	private String receiver;
	public PartnerNotFoundEvent(String sender, String receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public String getReceiver() {
		return receiver;
	}
}
