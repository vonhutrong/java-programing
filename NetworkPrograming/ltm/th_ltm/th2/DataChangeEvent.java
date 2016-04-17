package th_ltm.th2;

public class DataChangeEvent {
	private String mData;
	
	public DataChangeEvent(String string) {
		mData = string;
	}
	
	public String getData() {
		return mData;
	}
}
