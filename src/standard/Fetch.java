package standard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import sample.MonitoringTask;

public class Fetch extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5101612542876552024L;

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

		this.doPost(request, response);
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
		System.out.println("----------------fetch SYSLOG metric standard rules--------------------------->");
		int count = 1;
		response.setContentType("text/html");
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

	private String getOneJobTest(int count) {
		try {
			JSONArray messageObj = new JSONArray();
			//
			for (int i = 1; i <= 5; i++) {
				String tDate = new Date().toLocaleString();
				JSONObject taskObjA = new JSONObject();
				taskObjA.put("ip", "10.0.0." + i);
				taskObjA.put(MonitoringTask.TASK_CREATED_AD_PROPERTY_NAME, tDate);
				taskObjA.put("num", "1");
				taskObjA.put("dateFormat", "yyyymmddhhMMss");
				//
				JSONObject regexs = new JSONObject();
				regexs.put("ruleA", "ruleAValue");
				regexs.put("ruleB", "ruleBValue");
				
				JSONObject protoFormat = new JSONObject();
				protoFormat.put("protoFormat1", "protoFormat1");
				protoFormat.put("protoFormat2", "protoFormat2");
				//
				taskObjA.put("regexs", regexs);
				taskObjA.put("protoFormat", protoFormat);
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
