package com.scx.subscription.repository;

import com.scx.subscription.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserRepository extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {

    SysUser findByloginNoAndPwd(String loginNo, String pwd);
}