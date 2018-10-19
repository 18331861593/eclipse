package com.AutoGenerate.dao;

import com.AutoGenerate.entity.Menu;
import java.util.List;

public interface MenuMapper {

    //分页
    public List<Menu> paging(int page, int rows);

    //添加
    public int insert(Menu menu);

    //删除
    public int delete(int id);
    
    public int update(Menu menu);

    public Menu getById(int id);

    public int selectCount();
}
