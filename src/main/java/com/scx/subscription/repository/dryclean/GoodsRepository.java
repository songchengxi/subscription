package com.scx.subscription.repository.dryclean;

import com.scx.subscription.model.dryclean.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, String>, JpaSpecificationExecutor<Goods> {

    List<Goods> findByIsValid(String s);
}