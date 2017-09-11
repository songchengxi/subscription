package com.scx.subscription.repository;

import com.scx.subscription.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccessTokenRepository extends JpaRepository<AccessToken, String>, JpaSpecificationExecutor<AccessToken> {
}
