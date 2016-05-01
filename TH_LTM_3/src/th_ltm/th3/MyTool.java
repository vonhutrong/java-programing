package th_ltm.th3;

import javax.servlet.http.HttpServletResponse;

public class MyTool {
	@SuppressWarnings("static-access")
	public static void sendRedirect(HttpServletResponse response, String site) {
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}
}
