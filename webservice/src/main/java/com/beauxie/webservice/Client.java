package com.beauxie.webservice;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Client {
	public static void main(String[] args) throws Exception {

		/*URL url = new URL("http://localhost:9001/demo?wsdl");

		QName qname = new QName("http://webservice.beauxie.com/", "SpeakerImplService");

		Service service = Service.create(url, qname);
		Speaker speaker = service.getPort(Speaker.class);

		// 4.调用服务的方法
		String str = speaker.sayhello("Beauxie");
		System.out.println(str);*/
		Map<String, Object> map = new HashMap<String,Object>();
		List<String> list = new ArrayList<String>();
		
		System.out.println(map.toString());

	}
}
