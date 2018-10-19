package com.three.dao;


import com.three.dataSource.DataSource;
import com.three.entity.CommAttachment;

import java.util.List;


@DataSource(value = "dataSource1")
public interface CommAttachmentMapper {

    //分页
    public List<CommAttachment> paging(int page, int rows);

    //添加
    public int insert(CommAttachment commAttachment);

    //删除
    public int delete(int id);
    
    public int update(CommAttachment commAttachment);

    public CommAttachment getById(int id);

    public int selectCount();
}
