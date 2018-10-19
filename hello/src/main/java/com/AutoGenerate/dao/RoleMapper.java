package com.AutoGenerate.dao;

import com.AutoGenerate.entity.Role;
import java.util.List;

public interface RoleMapper {

    //分页
    public List<Role> paging(int page, int rows);

    //添加
    public int insert(Role role);

    //删除
    public int delete(int id);
    
    public int update(Role role);

    public Role getById(int id);

    public int selectCount();
}
