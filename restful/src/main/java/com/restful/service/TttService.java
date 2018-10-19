package com.restful.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.restful.dao.TttMapper;
import com.restful.entity.Ttt;

@Repository
public class TttService {
	
	@Autowired
	private TttMapper mapper;
	
	public List<Map<String, Object>> getAll(){
		return mapper.getAll();
	}

	public void delete(int id){
		mapper.delete(id);
	}

	public void insert(Ttt t){
		mapper.insert(t);
	}

	public Map<String, Object> getOne(int id){
		return mapper.getOne(id);
	}

	public void update(Ttt t){
		mapper.update(t);
	}

}
