package com.scx.subscription.repository;

import com.scx.subscription.model.WXUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<WXUser, String>, JpaSpecificationExecutor<WXUser> {

}