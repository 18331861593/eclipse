package com.${projectName}.dao;

import com.${projectName}.entity.${entityName};
import java.util.List;

public interface ${entityName}Mapper {

    //分页
    public List<${entityName}> paging(int page, int rows);

    //添加
    public int insert(${entityName} ${short_entityName});

    //删除
    public int delete(int id);
    
    public int update(${entityName} ${short_entityName});

    public ${entityName} getById(int id);

    public int selectCount();
}
