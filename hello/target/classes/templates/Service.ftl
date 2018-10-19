package com.${projectName}.service;


import com.${projectName}.dao.${entityName}Mapper;
import com.${projectName}.entity.${entityName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ${entityName}Service {

    @Autowired
    private ${entityName}Mapper mapper;

    //分页
    public List<${entityName}> paging(int page, int rows){
        return mapper.paging(page,rows);
    }

    //添加
    public int insert(${entityName} ${short_entityName}){
        return mapper.insert(${short_entityName});
    }

    //删除
    public int delete(int id){
        return mapper.delete(id);
    }
    
    public int update(${entityName} ${short_entityName}){
    	return mapper.update(${short_entityName});
    }

    public ${entityName} getById(int id) {
        return mapper.getById(id);
    }

    public int selectCount(){
        return mapper.selectCount();
    }
}
