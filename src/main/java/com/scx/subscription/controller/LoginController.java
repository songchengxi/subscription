package com.scx.subscription.controller;

import com.scx.subscription.model.SysUser;
import com.scx.subscription.repository.SysUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserRepository sysUserRepository;

    @PostMapping("/login")
    @ResponseBody
    public Map login(@RequestBody SysUser user, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        user = sysUserRepository.findByloginNoAndPwd(user.getLoginNo(), user.getPwd());
        if (user != null) {
            map.put("state", "OK");
            map.put("data", user);
            session.setAttribute("user", user);
        } else {
            map.put("state", "NO");
        }
        return map;
    }

    @PostMapping("/storage")
    @ResponseBody
    public Map<String, Object> storage(String openId, HttpSession session) {
        session.setAttribute("openId", openId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("openId", openId);
        return map;
    }

    @PostMapping("/quit")
    @ResponseBody
    public Map quit(@RequestBody SysUser user, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "OK");
        session.removeAttribute("user");
        return map;
    }

    @PostMapping("/prompt")
    @ResponseBody
    public String prompt() {
        return "login";
    }
}
