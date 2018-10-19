package com.${projectName}.dao;

import com.${projectName}.entity.${entityName};
import java.util.List;
import com.${projectName}.redis.GetCache;

public interface ${entityName}Mapper {

    //分页
    @GetCache(name = "${short_entityName}Paging",value="page,rows")
    public List<${entityName}> paging(int page, int rows);

    //添加
    public int insert(${entityName} ${short_entityName});

    //删除
    public int delete(int id);
    
    public int update(${entityName} ${short_entityName});

    public ${entityName} getById(int id);

    public int selectCount();
}
