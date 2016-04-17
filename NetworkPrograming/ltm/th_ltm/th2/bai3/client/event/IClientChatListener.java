package th_ltm.th2.bai3.client.event;

public interface IClientChatListener {
	void onJoinOk();
	void onJoinError();
	void onOutOk();
	void onMessage(MessageEvent messageEvent);
	void onPartnerNotFound(PartnerNotFoundEvent e);
	void onSyntaxError();
}
