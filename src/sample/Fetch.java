package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(getOneJobTest(count));
		out.flush();
		out.close();
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("----------------fetch--------" + request.getHeader(InterfaceParameter.COUNT) + "------------------->");
		System.out.println("----------------content--------------------------"+getBody(request));
		int count=1;
		if(request.getHeader(InterfaceParameter.COUNT)!=null){
			count=Integer.valueOf(request.getHeader(InterfaceParameter.COUNT));
		}
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
				metaObjA.put("username", "baiyanwei");
				metaObjA.put("password", "SELECTFROM");
				metaObjA.put("host_ip","localhost");
				metaObjA.put("port", "22");	
				metaObjA.put("shell_command", "ls");
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
	private String getSNMPest(int count) {
		try {
			JSONArray messageObj = new JSONArray();
			//
			for (int i = 1; i <= count; i++) {
				String tDate = new Date().toLocaleString();
				JSONObject taskObjA = new JSONObject();
				taskObjA.put(MonitoringTask.TASK_MONITOR_ID_PROPERTY_NAME, "TASK-HB-TEST-000" + i);
				taskObjA.put(MonitoringTask.TASK_TIMESTAMP_PROPERTY_NAME, tDate);
				taskObjA.put(MonitoringTask.TASK_CREATED_AD_PROPERTY_NAME, tDate);
				taskObjA.put(MonitoringTask.TASK_OPERATION_PROPERTY_NAME, "snmp");
				taskObjA.put(MonitoringTask.TASK_TARGET_IP_PROPERTY_NAME, "localhost");
				//
				JSONObject metaObjA = new JSONObject();
				metaObjA.put("snmp_version", "3");
				metaObjA.put("host_ip", "10.0.0.1");
				metaObjA.put("port","22");
				metaObjA.put("community", "22");	
				metaObjA.put("mibs", "1,2,3");
				metaObjA.put("username", "ls");
				metaObjA.put("authPass", "ls");
				metaObjA.put("priv", "ls");
				metaObjA.put("privPass", "ls");
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
	public String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}
}
