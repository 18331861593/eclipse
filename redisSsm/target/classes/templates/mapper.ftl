<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.${projectName}.dao.${entityName}Mapper">

    <resultMap id="${short_entityName}" type="com.${projectName}.entity.${entityName}">
   	 <#-- <result property="id" javaType="int" column="id"/> -->
    	<#list fields as f>
			<#if f.type != 'Object'>
		<result property="${f.name}" javaType="${f.type}" column="${f.name}" />
			</#if>
 		</#list>
    </resultMap>


    <select id="paging" resultMap="${short_entityName}">
    	${paging}
    </select>

    <insert id="insert" >
    	${insert}
    </insert>

    <delete id="delete">
    	${delete}
    </delete>
	
	<update id="update">
		${update}
	</update>

    <select id="getById" resultMap="${short_entityName}">
    	${getById}
    </select>

    <select id="selectCount" resultType="int">
        select count(id) from ${entityName}
    </select>
	
</mapper>