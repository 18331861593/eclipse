package com.three.dao;

import com.three.entity.CommAttachment;
import java.util.List;
import com.three.redis.GetCache;

public interface CommAttachmentMapper {

    //分页
    @GetCache(name = "commAttachmentPaging",value="page,rows")
    public List<CommAttachment> paging(int page, int rows);

    //添加
    public int insert(CommAttachment commAttachment);

    //删除
    public int delete(int id);
    
    public int update(CommAttachment commAttachment);

    public CommAttachment getById(int id);
    
    public int selectCount();
}
