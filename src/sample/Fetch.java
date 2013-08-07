package sample;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class Fetch extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8475431859442440844L;

	/**
	 * Constructor of the object.
	 */
	public Fetch() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----------------fetch--------" + request.getHeader(InterfaceParameter.COUNT) + "------------------->");
		int count=1;
		if(request.getHeader(InterfaceParameter.COUNT)!=null){
			count=Integer.valueOf(request.getHeader(InterfaceParameter.COUNT));
		}
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(getOneJobTest(count));
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	/**
	 * @param count
	 * @return
	 */
	private String getOneJobTest(int count) {
		try {
			JSONArray messageObj = new JSONArray();
			//
			for (int i = 1; i <= count; i++) {
				String tDate = new Date().toLocaleString();
				JSONObject taskObjA = new JSONObject();
				taskObjA.put(MonitoringTask.TASK_MONITOR_ID_PROPERTY_NAME, "TASK-HB-TEST-000" + i);
				taskObjA.put(MonitoringTask.TASK_TIMESTAMP_PROPERTY_NAME, tDate);
				taskObjA.put(MonitoringTask.TASK_CREATED_AD_PROPERTY_NAME, tDate);
				taskObjA.put(MonitoringTask.TASK_OPERATION_PROPERTY_NAME, "ssh");
				taskObjA.put(MonitoringTask.TASK_TARGET_IP_PROPERTY_NAME, "localhost");
				//
				JSONObject metaObjA = new JSONObject();
				metaObjA.put("user", "root");
				metaObjA.put("password", "123");
				metaObjA.put("shell", "ls");
				taskObjA.put(MonitoringTask.TASK_META_DATA_NAME, metaObjA);
				messageObj.put(taskObjA);
			}
			//
			return messageObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}

}
