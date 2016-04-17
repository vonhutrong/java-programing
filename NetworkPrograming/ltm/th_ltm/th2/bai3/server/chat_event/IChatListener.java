package th_ltm.th2.bai3.server.chat_event;

public interface IChatListener {
	void onJoin(JoinEvent e);
	void onOut(OutEvent e);
	void onChat(ChatEvent e);
	void onError(ErrorEvent e);
	void onPartnerNotFound(PartnerNotFoundEvent e);
}
