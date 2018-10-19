package com.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Test {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		list.add("123");
		list.add("123");
		list.add("1232");
		list.add("1232");
		
		
		Set<String> set = new HashSet<>(list);
		System.out.println(set.size());
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			String s = iterator.next();
			System.out.println(s);
		}
		
		
	}
	
}
