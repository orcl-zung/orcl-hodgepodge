package com.lea.framework.data;

import com.lea.framework.data.crypt.util.JlcSpringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lea
 * @description
 * @history 2023-04-07 18:04 created by lea
 * @since 2023-04-07 18:04
 */
@Import(JlcSpringUtil.class)
@Configuration
public class DataBaseAutoConfiguration {
}
