package com.orcl.jpa.repository;

import com.orcl.jpa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-21 17:03
 * @history: 2022-07-21 17:03 created by orcl
 */
public interface JpaUserRepository extends JpaRepository<JpaUser, Long> {


}
