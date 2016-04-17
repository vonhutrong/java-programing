package th_ltm.th2.bai3.client.event;

public class PartnerNotFoundEvent {
	private String partnerName;
	public PartnerNotFoundEvent(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerName() {
		return partnerName;
	}
}
