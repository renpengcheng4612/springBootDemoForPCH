package com.chinaclear.sz.aspect;

import com.chinaclear.sz.model.aspect.SysLogBo;
import com.chinaclear.sz.service.SysLogService;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Aspect     // @Aspect  注解声明一个切面
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    /*
     *  这里我们使用注解的形式
     *  当然，我们也可以通过切点表达式直接指定我们需要拦截的package,需要拦截的class,以及method
     *  切点表达式: execution{...}
     * */
    @Pointcut("@annotation(com.chinaclear.sz.aspect.SysLog)")
    public void logPointCut() {}


    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        saveLog(point, time);
        return result;
    }


    private void saveLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SysLogBo sysLogBo = new SysLogBo();
        sysLogBo.setExeuTime(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sysLogBo.setCreateDate(simpleDateFormat.format(new Date()));
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            // 注解上的描述
            sysLogBo.setRemark(sysLog.value());
        }
        // 请求的类名，方法
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogBo.setClassName(className);
        sysLogBo.setMethodName(methodName);

        // 请求时的参数
        Object[] args = point.getArgs();
        try {
            List<String> list = new ArrayList<String>();
            for (Object o : args) {
                list.add(new Gson().toJson(o));
            }
            sysLogBo.setParams(list.toString());
        } catch (Exception e) {
        }
        sysLogService.save(sysLogBo);
    }
}
