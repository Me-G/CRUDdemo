package com.mydemo.demo.service;

import com.mydemo.demo.dao.WebsiteMapper;
import com.mydemo.demo.model.Website;
import com.mydemo.demo.model.WebsiteExample;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ME
 */
@Service
public class WebsiteService {

    @Autowired
    WebsiteMapper websiteMapper;

    public List<Website> getAllWebsites() {
        //通过criteria构造查询条件
        WebsiteExample websiteExample = new WebsiteExample();
        websiteExample.setOrderByClause("id asc"); //asc升序,desc降序排列
        websiteExample.setDistinct(false); //去除重复,true是选择不重复记录,false反之
        websiteExample.or().getAllCriteria();
        //自定义查询条件可能返回多条记录,使用List接收
        List<Website> websites = websiteMapper.selectByExample(websiteExample);
        return websites;
    }

    public Website getWebsiteById(Integer id) {
        Website website = websiteMapper.selectByPrimaryKey(id);
        return website;
    }

    ;
    
    public int insertWebsite(Website website) {
        int result = websiteMapper.insertSelective(website);
        return result;
    }

    public int updateWebsite(Website website) {
        int result = websiteMapper.updateByPrimaryKeySelective(website);
        return result;
    }

    public int deleteWebsiteById(Integer id) {
        int result = websiteMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int dropColumnID() {
        int result = websiteMapper.dropColumnID();
        return result;
    }

    public int addColumnID() {
        int result = websiteMapper.addColumnID();
        return result;
    }

    public int resetID() {
        int resultDrop = websiteMapper.dropColumnID();
        int resultAdd = websiteMapper.addColumnID();
        int result = resultDrop & resultAdd;
        return result;
    }
}





