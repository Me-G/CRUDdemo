package com.mydemo.demo.controller;

import com.mydemo.demo.model.Website;
import com.mydemo.demo.service.WebsiteService;
import java.io.IOException;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ME
 */
@Controller
public class WebsiteController {

    @Autowired
    WebsiteService websiteService;
    private static final Logger logger = LoggerFactory.getLogger(WebsiteController.class);

    //插入或更新数据
    @RequestMapping(value = "/updateORinsert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Website updateORinsert(@RequestBody Website website) {
        //通过id判断是插入还是更新,id=0插入，id！=0更新
        if (website.getId() == 0) {
            logger.info("Log4j2===>Insert:"+website);
            websiteService.insertWebsite(website);
        } else {
            logger.info("Log4j2===>Update:"+website);
            websiteService.updateWebsite(website);
        }
        return website;
    }
    //删除操作
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/xml;charset=UTF-8")
    @ResponseBody
    public Boolean delete(@RequestBody String[] ids) {
        if (ids.length == 0) {
            return false;
        } else {
            for (String id : ids) {
                logger.info("Log4j2===>Delete:"+id);
                websiteService.deleteWebsiteById(Integer.parseInt(id));
            }
        }
        websiteService.resetID();
        return true;
    }
    /**
     * 返回查询结果
     * @param message
     * message[0]:页面
     * message[1]:页面大小
     * message[2]:查询条件
     * @return 
     */
    @RequestMapping(value = "/getSearchPageData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSearchPageData(@RequestBody String[] message) {
        List<Website> websites = null;
        int total = 0;
        if (message.length == 3) {
            //通过条件查询数据
            String condition = message[2];
            websites = websiteService.getWebsiteByCondition(condition);
            total = websites.size();
//            System.out.println("total:" + total + ",websites:" + websites);
            if (message[0] != null && message[1] != null) {
                int number = Integer.parseInt(message[0]);
                int size = Integer.parseInt(message[1]);
                int start = (number - 1) * size;
                int end = total;
                if (start <= total) {
                    if (number * size - 1 <= total) {
                        end = number * size - 1;
                    } else {
                        end = total;
                    }
                }
                websites = websiteService.getWebsitePage(websites, start, end);
            } else {
                websites = websiteService.getWebsitePage(websites, 0, 9);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("rows", websites);
        return result;
    }
    //用户验证
    @RequestMapping(value = "/tableView", method = RequestMethod.POST)
    public ModelAndView tableView(@ModelAttribute("username") String username,
            @ModelAttribute("password") String password) throws IOException {
<<<<<<< HEAD
        logger.info("Log4j2===>用户名" + username);
        ModelAndView mv = new ModelAndView();
=======
        logger.info("Log4j2=========>用户名" + username + "密码" + password);

>>>>>>> parent of ec3be1d... SSM+Log4j2+Bootstrap+Ajax+Junit5+Shiro
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                subject.login(token);
<<<<<<< HEAD
                logger.info("Log4j2===>登陆成功");
                mv.setViewName("bootstraptable");
            } catch (AuthenticationException e) {
                mv.setViewName("unauthorized");
                logger.info("Log4j2===>登陆失败：" + e.getMessage());
=======
                System.out.println("登陆成功");
            } catch (AuthenticationException e) {
                System.out.println("登陆失败：" + e.getMessage());
>>>>>>> parent of ec3be1d... SSM+Log4j2+Bootstrap+Ajax+Junit5+Shiro
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("bootstraptable");
        return mv;
    }

<<<<<<< HEAD
    //退出
    @RequestMapping(value = "/logout")
    public ModelAndView logout(ModelAndView mv) {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
=======
    @RequestMapping(value = "/insertWebsite", method = RequestMethod.POST)
    public ModelAndView insertWebsite(ModelAndView mv,
            @ModelAttribute("insertWeb") Website website
    ) {
        websiteService.insertWebsite(website);
        mv.setViewName("redirect:/operation");
        return mv;
    }

    @RequestMapping(value = "/updateEdit/{id}")
    public ModelAndView updateEdit(ModelAndView mv,
            @PathVariable("id") Integer id
    ) {
        Website editWeb = websiteService.getWebsiteById(id);
        mv.addObject("editWeb", editWeb);
        mv.setViewName("updateEdit");
        return mv;
    }

    @RequestMapping(value = "/updateWebsite", method = RequestMethod.PUT)
    public ModelAndView updateWebsite(@ModelAttribute("updateWeb") Website website
    ) {
        ModelAndView mv = new ModelAndView();
        websiteService.updateWebsite(website);
        mv.setViewName("redirect:/operation");
        return mv;
    }

    @RequestMapping(value = "/deleteWebsite/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteWebsite(@PathVariable("id") Integer id
    ) {
        ModelAndView mv = new ModelAndView();
        websiteService.deleteWebsiteById(id);
        mv.setViewName("redirect:/operation");
>>>>>>> parent of ec3be1d... SSM+Log4j2+Bootstrap+Ajax+Junit5+Shiro
        return mv;
    }

}


