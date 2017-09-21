package com.scx.subscription.controller;

import com.scx.subscription.model.WXUser;
import com.scx.subscription.repository.WXUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wxUser")
public class WXUserController {

    private final static Logger log = LoggerFactory.getLogger(WXUserController.class);

    @Autowired
    private WXUserRepository wxUserRepository;

    @PostMapping("/findById")
    @ResponseBody
    public Map<String, Object> findById(@RequestBody WXUser u) {
        Map<String, Object> map = new HashMap<String, Object>();
        u = wxUserRepository.findOne(u.getOpenid());
        map.put("user", u);
        return map;
    }
}
