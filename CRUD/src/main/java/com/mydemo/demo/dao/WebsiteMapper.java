package com.mydemo.demo.dao;

import com.mydemo.demo.model.Website;
import com.mydemo.demo.model.WebsiteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebsiteMapper {
    long countByExample(WebsiteExample example);

    int deleteByExample(WebsiteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Website record);

    int insertSelective(Website record);

    List<Website> selectByExample(WebsiteExample example);

    Website selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Website record, @Param("example") WebsiteExample example);

    int updateByExample(@Param("record") Website record, @Param("example") WebsiteExample example);

    int updateByPrimaryKeySelective(Website record);

    int updateByPrimaryKey(Website record);
    
    //重置序列
    int dropColumnID();
    
    int addColumnID();
}

