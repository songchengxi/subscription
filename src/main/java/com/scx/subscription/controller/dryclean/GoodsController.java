package com.scx.subscription.controller.dryclean;

import com.scx.subscription.model.dryclean.Goods;
import com.scx.subscription.repository.dryclean.GoodsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsRepository goodsRepository;

    @PostMapping("/findAll")
    @ResponseBody
    public Map<String, Object> findAll() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Goods> all = goodsRepository.findAll();
        map.put("goods", all);
        return map;
    }

    /**
     * 查找有效商品
     */
    @PostMapping("/findAllValid")
    @ResponseBody
    public Map<String, Object> findAllValid() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Goods> all = goodsRepository.findByIsValid("1");
        map.put("goods", all);
        return map;
    }

    @PostMapping("/findById")
    @ResponseBody
    public Map<String, Object> findById(@RequestBody Goods goods) {
        Map<String, Object> map = new HashMap<String, Object>();
        goods = goodsRepository.findOne(goods.getId());
        map.put("goods", goods);
        return map;
    }

    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> save(@RequestBody Goods goods) {
        Map<String, Object> map = new HashMap<String, Object>();
        Goods save = goodsRepository.save(goods);
        map.put("goods", goods);
        return map;
    }

    @PostMapping("/del")
    @ResponseBody
    public Map<String, Object> del(@RequestBody Goods goods) {
        Map<String, Object> map = new HashMap<String, Object>();
        goodsRepository.delete(goods);
        map.put("id", goods.getId());
        return map;
    }
}
