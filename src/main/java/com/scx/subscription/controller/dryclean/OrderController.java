package com.scx.subscription.controller.dryclean;

import com.scx.subscription.model.dryclean.Order;
import com.scx.subscription.repository.dryclean.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/findByUserId")
    @ResponseBody
    public Map<String, Object> findByUserId(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderRepository.findByUserId(userId);
        map.put("orders", orders);
        return map;
    }
}
