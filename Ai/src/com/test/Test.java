package com.test;

import com.baidu.aip.speech.AipSpeech;

public class Test {
	public static void main(String[] args) {
//		AipSpeech aipSpeech1 = new AipSpeech(appId, apiKey, secretKey);
		AipSpeech aipSpeech = new AipSpeech("11286508","D7kErSm7CpVTXEkosifmvm68", "fffd9f9d7206f10e4890424f76494cc6");
		System.out.println(aipSpeech);
	}
}
