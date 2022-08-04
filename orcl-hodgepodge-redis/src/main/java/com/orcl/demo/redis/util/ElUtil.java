package com.orcl.demo.redis.util;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: orcl
 * @since: 2022/4/25-17:05
 * @history: 2022/4/25 created by orcl
 */
public class ElUtil {

    private static final EvaluationContext              EVALUATION_CONTEXT = new StandardEvaluationContext();
    private static final SpelExpressionParser           EXPRESSION_PARSER  = new SpelExpressionParser();
    private static final DefaultParameterNameDiscoverer NAME_DISCOVERER    = new DefaultParameterNameDiscoverer();

    public static String analysisByMethod(Method method, Object[] args, String elExpression) {
        String[] paramsNames = NAME_DISCOVERER.getParameterNames(method);
        for (int i = 0; i < args.length; i++) {
            EVALUATION_CONTEXT.setVariable(paramsNames[i], args[i]);
        }
        return String.valueOf(EXPRESSION_PARSER.parseExpression(elExpression).getValue(EVALUATION_CONTEXT));
    }

}
