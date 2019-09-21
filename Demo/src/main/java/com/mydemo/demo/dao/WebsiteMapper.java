package com.mydemo.demo.dao;

import com.mydemo.demo.bean.Website;
import java.util.List;

/**
 *
 * @author ME
 */
public interface WebsiteMapper {
    
    public Website getWebsiteById(Integer id);
    
    public List<Website> getAllWebsites();
    
    public void insetWebsite(Website website);
    
    public void updateWebsite(Website website);
    
    public void deleteWebsiteById(Integer id);
    
    public void dropColumnID();
    
    public void addColumnID();
    
}


