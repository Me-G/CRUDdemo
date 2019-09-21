package com.mydemo.demo.service;

import com.mydemo.demo.bean.Website;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ME
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class WebsiteServiceTest {

    @Autowired
    private WebsiteService websiteService;
    /**
     * Test of getAllWebsites method, of class WebsiteService.
     */
    @Test
    public void testGetAllWebsites() {
        System.out.println("getAllWebsites");
        List<Website> result = websiteService.getAllWebsites();
        System.out.println(result);
    }

    /**
     * Test of getWebsiteById method, of class WebsiteService.
     */
    @Test
    public void testGetWebsiteById() {
        System.out.println("getWebsiteById");
        Website result = websiteService.getWebsiteById(1);
        System.out.println(result);
    }
    
    /**
     * Test of deleteWebsiteById method, of class WebsiteService.
     */
    @Test
    @Transactional
    public void testDeleteWebsiteById() {
        System.out.println("deleteWebsiteById");
        websiteService.deleteWebsiteById(6);
        List<Website> result = websiteService.getAllWebsites();
        System.out.println(result);
    }

    /**
     * Test of insertWebsite method, of class WebsiteService.
     */
    @Test
    @Transactional
    public void testInsertWebsite() {
        System.out.println("insertWebsite");
        Website website=new Website();
        website.setName("test");
        website.setValue("testInsert");
        website.setRemark("testInsert");
        websiteService.insertWebsite(website);
        List<Website> result = websiteService.getAllWebsites();
        System.out.println(result);
    }

    /**
     * Test of updateWebsite method, of class WebsiteService.
     */
    @Test
    @Transactional
    public void testUpdateWebsite() {
        System.out.println("updateWebsite");
        Website website = websiteService.getWebsiteById(6);
        website.setRemark("remark");
        websiteService.updateWebsite(website);
        System.out.println(website);
    }

    /**
     * Test of resetID method, of class WebsiteService.
     */
    @Test
    public void testResetID() {
        System.out.println("resetID");
        websiteService.resetID();
        List<Website> result = websiteService.getAllWebsites();
        System.out.println(result);
    }
    
}
