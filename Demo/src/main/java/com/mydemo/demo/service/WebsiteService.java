package com.mydemo.demo.service;

import com.mydemo.demo.bean.Website;
import com.mydemo.demo.dao.WebsiteMapper;
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
    private WebsiteMapper websiteMapper;
    
    public List<Website> getAllWebsites(){
        return websiteMapper.getAllWebsites();
    };
    
    public Website getWebsiteById(Integer id){
        return websiteMapper.getWebsiteById(id);
    };
    
    public void insertWebsite(Website website){
        websiteMapper.insetWebsite(website);
    }
    
    public void updateWebsite(Website website){
        websiteMapper.updateWebsite(website);
    }
    
    public void deleteWebsiteById(Integer id){
        websiteMapper.deleteWebsiteById(id);
    }
    
}
