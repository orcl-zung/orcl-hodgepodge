package com.orcl.mybatis.mapper;

import com.orcl.mybatis.pojo.Role;
import com.orcl.mybatis.pojo.query.RoleQueryBean;

import java.util.List;

/**
 * @author lea
 * @description
 * @history 2023-05-02 19:24 created by lea
 * @since 2023-05-02 19:24
 */
public interface RoleMapper {

    List<Role> findList(RoleQueryBean roleQueryBean);

}
