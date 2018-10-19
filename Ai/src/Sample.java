import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLDecoder;  

import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;

public class Sample {

	private static final String serverUrl = "http://vop.baidu.com/server_api";

	private static String token = "";

	private static final String fileName = "16k_test.pcm"; // 百度语音提供技术支持

	private static final String apiKey = "D7kErSm7CpVTXEkosifmvm68";
	private static final String secretKey = "fffd9f9d7206f10e4890424f76494cc6";
	private static final String cuid = "11286508";

	public static void main(String[] args) throws Exception {
		getToken();
		method1();
		method2();
	}

	public static void getToken() throws Exception {
		String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials" + "&client_id="
				+ apiKey + "&client_secret=" + secretKey;
		HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL).openConnection();
		token = new JSONObject(printResponse(conn)).getString("access_token");
	}

	public static void method1() throws Exception {
		File pcmFile = new File(fileName);
		HttpURLConnection conn = (HttpURLConnection) new URL(serverUrl).openConnection();
		JSONObject params = new JSONObject();
		params.put("format", "pcm");
		params.put("rate", 8000);
		params.put("channel", "1");
		params.put("token", token);
		params.put("lan", "zh");
		params.put("cuid", cuid);
		params.put("len", pcmFile.length());
		params.put("speech", DatatypeConverter.printBase64Binary(loadFile(pcmFile)));
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");  
		conn.setDoInput(true);  
        conn.setDoOutput(true);  
        // send request  
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());  
        wr.writeBytes(params.toString());  
        wr.flush();  
        wr.close();  
        printResponse(conn);
	}
	private static void method2() throws Exception {  
        File pcmFile = new File(fileName);  
        HttpURLConnection conn = (HttpURLConnection) new URL(serverUrl  
                + "?cuid=" + cuid + "&token=" + token).openConnection();  
  
        // add request header  
        conn.setRequestMethod("POST");  
        conn.setRequestProperty("Content-Type", "audio/pcm; rate=8000");  
  
        conn.setDoInput(true);  
        conn.setDoOutput(true);  
  
        // send request  
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());  
        wr.write(loadFile(pcmFile));  
        wr.flush();  
        wr.close();  
  
        System.out.println(getUtf8String(printResponse(conn)));  
    }  
	private static String printResponse(HttpURLConnection conn) throws Exception {
		if (conn.getResponseCode() != 200) {
			System.out.println("conn.getResponseCode() = " + conn.getResponseCode());
			return "";
		}
		InputStream is = conn.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuffer response = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\r');
		}
		rd.close();
		System.out.println(new JSONObject(response.toString()).toString(4));
		return response.toString();
	}

	private static byte[] loadFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		if (offset < bytes.length) {
			is.close();
			throw new IOException("Could not completely read file " + file.getName());
		}

		is.close();
		return bytes;
	}
	
	
	public static String getUtf8String(String s) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();  
        sb.append(s);  
        String xmlString = "";  
        String xmlUtf8 = "";  
        xmlString = new String(sb.toString().getBytes("GBK"));  
        xmlUtf8 = URLEncoder.encode(xmlString , "GBK");  
          
        return URLDecoder.decode(xmlUtf8, "UTF-8");  
	}
	

}
