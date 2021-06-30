package com.again.cloud.web.aop;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import java.util.Map;

@Aspect // 声明此类是一个切面类：会包含切入点(pointcut)和通知(advice)
@Component //声明组件，进入工厂
@Slf4j
public class LoginAspect {
    @Pointcut("execution(* com.again.cloud.web.controller.SysUserController.getUsername(..))")
    public void pc() {
    }

    @Around("pc()") // 环绕通知
    public Object myInterceptor(ProceedingJoinPoint p) throws Throwable {
        Object[] objs = p.getArgs();
        String[] argNames = ((MethodSignature) p.getSignature()).getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < objs.length; i++) {
            if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
                paramMap.put(argNames[i], objs[i]);
            }
        }
        if (paramMap.size() > 0) {
            log.info("方法:{},参数:{}", p.getSignature(), JSONObject.toJSONString(paramMap));
        }
        Object ret = p.proceed();
        System.out.println("interceptor2~~~~"+ret);
        return ret;
    }
}
