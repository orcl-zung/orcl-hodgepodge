package com.orcl.mybatis.pojo.query;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lea
 * @description
 * @history 2023-05-02 20:52 created by lea
 * @since 2023-05-02 20:52
 */
@Data
@NoArgsConstructor
public class UserQueryBean {

    /**
     * contains name pattern.
     */
    private String userName;

    /**
     * contains desc pattern.
     */
    private String description;

    private String phoneNumber;

    private String email;

}
