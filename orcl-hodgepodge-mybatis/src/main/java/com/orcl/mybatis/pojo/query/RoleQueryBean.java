package com.orcl.mybatis.pojo.query;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lea
 * @description
 * @history 2023-05-02 20:50 created by lea
 * @since 2023-05-02 20:50
 */
@Data
@NoArgsConstructor
public
class RoleQueryBean {

    /**
     * contains name pattern.
     */
    private String name;

    /**
     * contains desc pattern.
     */
    private String description;

    private String roleKey;

}
