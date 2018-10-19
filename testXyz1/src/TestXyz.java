


import javax.xml.namespace.QName;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;   
import org.apache.axiom.om.OMAbstractFactory;  
import org.apache.axiom.om.OMFactory;  
import org.apache.axiom.om.OMNamespace;  
import org.apache.axis2.AxisFault;  
import org.apache.axis2.client.ServiceClient;


public class TestXyz {

	/**
	 * 方法一： 应用rpc的方式调用 这种方式就等于远程调用， 即通过url定位告诉远程服务器，告知方法名称，参数等， 调用远程服务，得到结果。 使用
	 * org.apache.axis2.rpc.client.RPCServiceClient类调用WebService
	 * 
	 * 【注】：
	 * 
	 * 如果被调用的WebService方法有返回值 应使用 invokeBlocking 方法 该方法有三个参数
	 * 第一个参数的类型是QName对象，表示要调用的方法名； 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；
	 * 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。
	 * 第三个参数表示WebService方法的 返回值类型的Class对象，参数类型为Class[]。
	 * 
	 * 
	 * 如果被调用的WebService方法没有返回值 应使用 invokeRobust 方法
	 * 该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同。
	 * 
	 * 在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名， 也就是
	 * <wsdl:definitions>元素的targetNamespace属性值。
	 * 
	 */

	public static void testXyz() {
		try {
			// 服务端地址
			String url = "http://localhost:443/axis2/services/xyz?wsdl";
			// 使用 RPC方式调用 webService
			RPCServiceClient client = new RPCServiceClient();
			// 调用webService 的url
			EndpointReference reference = new EndpointReference(url);
			// 确定目标服务器
			Options options = client.getOptions();
			options.setTo(reference);
			options.setAction("urn:sayHello");
			QName qname = new QName("http://xyz.com", "sayHello");

			// 指定sa'y'Hello的方法参数
			 String[] parameters = new String[] { "张三" }; 
			// 指定sayHello 参数
			 Class[] returnTyps = new Class[]{String.class};

			OMElement element = client.invokeBlocking(qname, parameters);
			// 值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。
			// 我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果
			String result = element.getFirstElement().getText();
			System.out.println(result);
		} catch (AxisFault e) {  
		      e.printStackTrace();  
	    }  
	}
	
	public static void main(String[] args) {
		testXyz();  
//		testDocument();
	}
	
	
	

	  /** 
	   * 方法二： 应用document方式调用 
	   * 用ducument方式应用现对繁琐而灵活。现在用的比较多。因为真正摆脱了我们不想要的耦合 
	   */ 
	public static void testDocument(){
		try {
			//指定url
			String url = "http://localhost:443/axis2/services/xyz?wsdl";
			/*Options options = new Options();
			EndpointReference targetEPR = new EndpointReference(url);
			options.setTo(targetEPR);
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OMFactory fac = OMAbstractFactory.getOMFactory();  
			String tns = "http://xyz.com";  
			OMNamespace omne = fac.createOMNamespace(tns, "");
			OMElement method = fac.createOMElement("sayHello",omne);
			OMElement name = fac.createOMElement("name", omne);  
			 // symbol.setText("1");  
			name.addChild(fac.createOMText(name, "张三"));  
			method.addChild(name);  
			method.build();  
		        
			OMElement result = sender.sendReceive(method);  
		  
			System.out.println(result);  */
					
			   Options options = new Options();  
			      // 指定调用WebService的URL  
			      EndpointReference targetEPR = new EndpointReference(url);  
			      options.setTo(targetEPR);  
			      // options.setAction("urn:getPrice");  
			  
			      ServiceClient sender = new ServiceClient();  
			      sender.setOptions(options);  
			        
			        
			      OMFactory fac = OMAbstractFactory.getOMFactory();  
			      String tns = "http://xyz.com";  
			      // 命名空间，有时命名空间不增加没事，不过最好加上，因为有时有事，你懂的  
			      OMNamespace omNs = fac.createOMNamespace(tns, "");  
			  
			      OMElement method = fac.createOMElement("sayHello", omNs);  
			      OMElement symbol = fac.createOMElement("name", omNs);  
			      // symbol.setText("1");  
			      symbol.addChild(fac.createOMText(symbol, "张三"));  
			      method.addChild(symbol);  
			      method.build();  
			        
			      OMElement result = sender.sendReceive(method);  
			  
			      System.out.println(result);  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
