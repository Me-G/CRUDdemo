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
import org.apache.shiro.authz.annotation.RequiresRoles;
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
    public Map<String, Object> tableData() {
        List<Website> websites = websiteService.getAllWebsites();
        int total = websites.size();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("rows", websites);
        return result;
    }

    @RequestMapping(value = "/updateORinsert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @RequiresRoles({"admin"})
    @ResponseBody
    public Website updateORinsert(@RequestBody Website website) {
        if (website.getId() == 0) {
            System.out.println(website);
            websiteService.insertWebsite(website);
        } else {
            websiteService.updateWebsite(website);
        }
        return website;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/xml;charset=UTF-8")
    @RequiresRoles({"admin"})
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

    @RequestMapping(value = "/getSearchPageData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSearchPageData(@RequestBody String[] message) {
        System.out.println("message[0]:" + message[0] + ",message[1]:" + message[1] + ",message[2]:" + message[2]);
        List<Website> websites = null;
        int total = 0;
        if (message.length == 3) {
            String condition = message[2];
            websites = websiteService.getWebsiteByCondition(condition);
            total = websites.size();
            System.out.println("total:" + total + ",websites:" + websites);

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
                websites = websiteService.getWebsitePageByCondition(websites, start, end);
            } else {
                websites = websiteService.getWebsitePageByCondition(websites, 0, 10);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("rows", websites);
        return result;
    }

//    @RequestMapping(value = "/getPageData", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> getPageData(@RequestBody String[] message) {
//        List<Website> websites = websiteService.getAllWebsites();
//        int total = websites.size();
//        if (message.length == 2) {
//            if (message[0] != null && message[1] != null) {
//                int number = Integer.parseInt(message[0]);
//                int size = Integer.parseInt(message[1]);
//                int start = (number - 1) * size + 1;
//                int end = total;
//                if (start <= total) {
//                    if (number * size <= total) {
//                        end = number * size;
//                    } else {
//                        end = total;
//                    }
//                }
//                websites = websiteService.getWebsiteByIdBetween(start, end);
//            } else {
//                websites = websiteService.getWebsiteByIdBetween(1, 10);
//            }
//
//        }
//        Map<String, Object> result = new HashMap<>();
//        result.put("total", total);
//        result.put("rows", websites);
//        return result;
//    }
    @RequestMapping(value = "/tableView", method = RequestMethod.POST)
    public ModelAndView tableView(@ModelAttribute("username") String username,
            @ModelAttribute("password") String password) throws IOException {
        logger.info("Log4j2=========>用户名" + username + "密码" + password);
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                subject.login(token);
                System.out.println("登陆成功");
                mv.setViewName("bootstraptable");
            } catch (AuthenticationException e) {
                mv.setViewName("unauthorized");
                mv.addObject("error", e.getMessage());
                System.out.println("登陆失败：" + e.getMessage());
            }
        }
        return mv;
    }

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
        return mv;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(ModelAndView mv) {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return mv;
    }

}

