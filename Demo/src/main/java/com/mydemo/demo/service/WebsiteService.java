package com.mydemo.demo.service;

import com.mydemo.demo.dao.WebsiteMapper;
import com.mydemo.demo.model.Website;
import com.mydemo.demo.model.WebsiteExample;
import java.util.ArrayList;
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

    public List<Website> getWebsiteByIdBetween(Integer start, Integer end) {
        //通过criteria构造查询条件
        WebsiteExample websiteExample = new WebsiteExample();
        websiteExample.setDistinct(false); //去除重复,true是选择不重复记录,false反之
        websiteExample.or().andIdBetween(start, end);
        //自定义查询条件可能返回多条记录,使用List接收
        List<Website> websites = websiteMapper.selectByExample(websiteExample);
        return websites;
    }

    public List<Website> getWebsiteByCondition(String condition) {
        //通过criteria构造查询条件
        WebsiteExample websiteExample = new WebsiteExample();
        websiteExample.setOrderByClause("id asc"); //asc升序,desc降序排列
        websiteExample.setDistinct(true); //去除重复,true是选择不重复记录,false反之
        if (condition == null) {
            websiteExample.or().getAllCriteria();
        } else {
            condition = "%" + condition + "%";
            websiteExample.or().andIdLike(condition);
            websiteExample.or().andNameLike(condition);
            websiteExample.or().andValueLike(condition);
            websiteExample.or().andRemarkLike(condition);
        }
        //自定义查询条件可能返回多条记录,使用List接收
        List<Website> websites = websiteMapper.selectByExample(websiteExample);
        return websites;
    }
    //返回页面数据
    public List<Website> getWebsitePage(List<Website> websites, Integer start, Integer end) {
        List<Website> result = new ArrayList<>();
        System.out.println(websites.size());
        for (int i = 0; i < websites.size(); i++) {
            if (i >= start && i <= end) {
                System.out.println(websites.get(i));
                result.add(websites.get(i));
            }
        }
        if (result.isEmpty()) {
            //通过criteria构造查询条件
            WebsiteExample websiteExample = new WebsiteExample();
            websiteExample.setDistinct(false); //去除重复,true是选择不重复记录,false反之
            websiteExample.or().andIdBetween(start, end);
            //自定义查询条件可能返回多条记录,使用List接收
            result = websiteMapper.selectByExample(websiteExample);
        }
        return result;
    }

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









