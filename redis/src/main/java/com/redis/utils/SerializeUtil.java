package com.redis.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化  反序列化 工具
 * @author Administrator
 *
 */
public class SerializeUtil {
	
	/**
	 * 序列化
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object obj) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] arr = baos.toByteArray();
			return arr;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 反序列化
	 * @param result
	 * @return
	 */
	public static Object unSerialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}
	

	
}
