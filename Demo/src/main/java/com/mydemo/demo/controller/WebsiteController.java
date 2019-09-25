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
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/operation")
    public ModelAndView queryAll(ModelAndView mv) {
        logger.info("=========>未登录");
        List<Website> websites = websiteService.getAllWebsites();
        mv.setViewName("operation");
        mv.addObject("allwebsites", websites);
        return mv;
    }

    @RequestMapping(value = "/tableData", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Website> tableData() {
        List<Website> websites = websiteService.getAllWebsites();
        return websites;
    }
//    @RequestMapping(value = "/tableData", produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public Map<String, Object> tableData() {
//        List<Website> websites = websiteService.getAllWebsites();
//        Map<String, Object> result = new HashMap<>();
//        result.put("total", websites.size());
//        result.put("rows", websites);
//        return result;
//    }

    @RequestMapping(value = "/updateORinsert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Website updateORinsert(@RequestBody Website website) {
        if (website.getId() == 0) {
            System.out.println(website);
//            if (website.getName() != null || website.getRemark() != null || website.getRemark() != null) {
            websiteService.insertWebsite(website);
//            } else {
//                return null;
//            }
        } else {
            websiteService.updateWebsite(website);
        }
        return website;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/xml;charset=UTF-8")
    @ResponseBody
    public Boolean delete(@RequestBody String[] ids) {
        if (ids.length == 0) {
            return false;
        } else {
            for (String id : ids) {
                System.out.println(Integer.parseInt(id));
                websiteService.deleteWebsiteById(Integer.parseInt(id));
            }
        }
        websiteService.resetID();
        return true;
    }

    @RequestMapping(value = "/tableView", method = RequestMethod.POST)
    public ModelAndView tableView(@ModelAttribute("username") String username,
            @ModelAttribute("password") String password) throws IOException {
        logger.info("Log4j2=========>用户名" + username + "密码" + password);

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                subject.login(token);
                System.out.println("登陆成功");
            } catch (AuthenticationException e) {
                System.out.println("登陆失败：" + e.getMessage());
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("bootstraptable");
        return mv;
    }

    @RequestMapping(value = "/insertWebsite", method = RequestMethod.POST)
    public ModelAndView insertWebsite(ModelAndView mv, @ModelAttribute("insertWeb") Website website) {
        websiteService.insertWebsite(website);
        mv.setViewName("redirect:/operation");
        return mv;
    }

    @RequestMapping(value = "/updateEdit/{id}")
    public ModelAndView updateEdit(ModelAndView mv, @PathVariable("id") Integer id) {
        Website editWeb = websiteService.getWebsiteById(id);
        mv.addObject("editWeb", editWeb);
        mv.setViewName("updateEdit");
        return mv;
    }

    @RequestMapping(value = "/updateWebsite", method = RequestMethod.PUT)
    public ModelAndView updateWebsite(@ModelAttribute("updateWeb") Website website) {
        ModelAndView mv = new ModelAndView();
        websiteService.updateWebsite(website);
        mv.setViewName("redirect:/operation");
        return mv;
    }

    @RequestMapping(value = "/deleteWebsite/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteWebsite(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        websiteService.deleteWebsiteById(id);
        mv.setViewName("redirect:/operation");
        return mv;
    }

}






