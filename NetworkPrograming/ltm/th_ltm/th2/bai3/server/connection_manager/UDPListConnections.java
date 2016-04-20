package th_ltm.th2.bai3.server.connection_manager;

import java.util.HashMap;
import java.util.Map;

public class UDPListConnections {
	private Map<String, UDPConnection> listConnections;
	
	public UDPListConnections() {
		listConnections = new HashMap<String, UDPConnection>();
	}
	
	public boolean addConnection(UDPConnection conn, String name) {
		return listConnections.put(name, conn) == null ? true : false;
	}
	
	public boolean removeConnection(String name) {
		return listConnections.remove(name) != null ? true : false;
	}
	
	public UDPConnection getConnectionByName(String name) {
		return listConnections.get(name);
	}

	public String getNameByConnection(UDPConnection receiverConnection) {
		for (String key : listConnections.keySet())
			if (listConnections.get(key).equals(receiverConnection))
				return key;
		return null;
	}
}
