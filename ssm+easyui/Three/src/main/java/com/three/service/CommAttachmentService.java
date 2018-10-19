package com.three.service;


import com.three.dao.CommAttachmentMapper;
import com.three.entity.CommAttachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommAttachmentService {

    @Autowired
    private CommAttachmentMapper mapper;

    //分页
    public List<CommAttachment> paging(int page, int rows){
        return mapper.paging(page,rows);
    }

    //添加
    public int insert(CommAttachment commAttachment){
        return mapper.insert(commAttachment);
    }

    //删除
    public int delete(int id){
        return mapper.delete(id);
    }
    
    public int update(CommAttachment commAttachment){
    	return mapper.update(commAttachment);
    }

    public CommAttachment getById(int id) {
        return mapper.getById(id);
    }

    public int selectCount(){
        return mapper.selectCount();
    }

}
