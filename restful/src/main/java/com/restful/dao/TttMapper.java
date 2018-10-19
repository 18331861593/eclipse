package com.restful.dao;

import java.util.List;
import java.util.Map;

import com.restful.entity.Ttt;


public interface TttMapper {
	
	public List<Map<String,Object>> getAll();
	
	public void delete(int id);
	
	public void insert(Ttt t);
	
	public Map<String,Object> getOne(int id);
	
	public void update(Ttt t);

}
