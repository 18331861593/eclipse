package com.test;

import java.net.InetSocketAddress;

import com.Service.HelloService;
import com.Service.Server;
import com.Service.ServiceCenter;
import com.Service.impl.HelloServiceImpl;
import com.client.RPCClient;

public class Test {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Server serviceServer = new ServiceCenter(442);
					serviceServer.register(HelloService.class, HelloServiceImpl.class);
					serviceServer.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		HelloService service = RPCClient.getRemoteProxyObj(HelloService.class,
				new InetSocketAddress("localhost", 442));
		System.out.println(service.sayHi("test"));
	}
}
