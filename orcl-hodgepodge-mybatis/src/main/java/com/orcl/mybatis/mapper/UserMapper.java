package com.orcl.mybatis.mapper;

import com.orcl.mybatis.pojo.User;
import com.orcl.mybatis.pojo.query.UserQueryBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lea
 * @description
 * @history 2023-05-02 19:24 created by lea
 * @since 2023-05-02 19:24
 */
public interface UserMapper {

    List<User> findList(UserQueryBean userQueryBean);

    User findById(Long id);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    int update(User user);

    int save(User user);

    int updatePassword(User user);

}
