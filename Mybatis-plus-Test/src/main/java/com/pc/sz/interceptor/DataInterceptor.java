package com.pc.sz.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.*.annotation.EncryptField;
import com.*.annotation.EncryptMethod;
import com.*.util.CryptPojoUtils;
import com.*.util.JsonUtil;
import com.pc.sz.utils.CryptPojoUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class DataInterceptor implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(DataInterceptor.class);
    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;
    static int ROWBOUNDS_INDEX = 2;
    static int RESULT_HANDLER_INDEX = 3;
    static String ENCRYPTFIELD = "1";
    static String DECRYPTFIELD = "2";
    private static boolean ENCRYPT_SWTICH = true;

    /**
     * 是否进行加密查询
     *
     * @return 1 true 代表加密 0 false 不加密
     */
    private boolean getFuncSwitch() {
        return ENCRYPT_SWTICH;
    }

    /**
     * 校验执行器方法 是否在白名单中
     *
     * @param statementid
     * @return true 包含 false 不包含
     */
    private boolean isWhiteList(String statementid) {
        boolean result = false;
//        String whiteStatementid = "com.*.dao.UserDao.save";
        String whiteStatementid = "";
        if (whiteStatementid.indexOf(statementid) != -1) {
            result = true;
        }
        return result;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("EncryptDaoInterceptor.intercept开始执行==> ");
        MappedStatement statement = (MappedStatement) invocation.getArgs()[MAPPED_STATEMENT_INDEX];
        Object parameter = invocation.getArgs()[PARAMETER_INDEX];
        logger.info(statement.getId() + "未加密参数串:" + JsonUtil.DEFAULT.toJson(parameter));
        /*
         * 判断是否拦截白名单 或 加密开关是否配置，
         * 如果不在白名单中，并且本地加密开关 已打开 执行参数加密
         */
        if (!isWhiteList(statement.getId()) && getFuncSwitch()) {
            parameter = encryptParam(parameter, invocation);
            logger.info(statement.getId() + "加密后参数:" + JsonUtil.DEFAULT.toJson(parameter));
        }
        invocation.getArgs()[PARAMETER_INDEX] = parameter;
        Object returnValue = invocation.proceed();
        logger.info(statement.getId() + "未解密结果集:" + JsonUtil.DEFAULT.toJson(returnValue));
        returnValue = decryptReslut(returnValue, invocation);
        logger.info(statement.getId() + "解密后结果集:" + JsonUtil.DEFAULT.toJson(returnValue));
        logger.info("EncryptDaoInterceptor.intercept执行结束==> ");
        return returnValue;
    }

    /**
     * 解密结果集
     *
     * @param @param  returnValue
     * @param @param  invocation
     * @param @return
     * @return Object
     * @throws
     */
    public Object decryptReslut(Object returnValue, Invocation invocation) {
        MappedStatement statement = (MappedStatement) invocation.getArgs()[MAPPED_STATEMENT_INDEX];
        if (returnValue != null) {
            if (returnValue instanceof ArrayList<?>) {
                List<?> list = (ArrayList<?>) returnValue;
                List<Object> newList = new ArrayList<Object>();
                if (1 <= list.size()) {
                    for (Object object : list) {
                        Object obj = CryptPojoUtils.decrypt(object);
                        newList.add(obj);
                    }
                    returnValue = newList;
                }
            } else if (returnValue instanceof Map) {
                String[] fields = getEncryFieldList(statement, DECRYPTFIELD);
                if (fields != null) {
                    returnValue = CryptPojoUtils.getDecryptMapValue(returnValue, fields);
                }
            } else {
                returnValue = CryptPojoUtils.decrypt(returnValue);
            }
        }
        return returnValue;
    }

    /***
     * 针对不同的参数类型进行加密
     * @param @param parameter
     * @param @param invocation
     * @param @return
     * @return Object
     * @throws
     *
     */
    public Object encryptParam(Object parameter, Invocation invocation) {
        MappedStatement statement = (MappedStatement) invocation.getArgs()[MAPPED_STATEMENT_INDEX];
        try {
            if (parameter instanceof String) {
                if (isEncryptStr(statement)) {
                    parameter = CryptPojoUtils.encryptStr(parameter);
                }
            } else if (parameter instanceof Map) {
                String[] fields = getEncryFieldList(statement, ENCRYPTFIELD);
                if (fields != null) {
                    parameter = CryptPojoUtils.getEncryptMapValue(parameter, fields);
                }
            } else {
                parameter = CryptPojoUtils.encrypt(parameter);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.info("EncryptDaoInterceptor.encryptParam方法异常==> " + e.getMessage());
        }
        return parameter;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 获取参数map中需要加密字段
     *
     * @param statement
     * @param type
     * @return List<String>
     * @throws
     */
    private String[] getEncryFieldList(MappedStatement statement, String type) {
        String[] strArry = null;
        Method method = getDaoTargetMethod(statement);
        Annotation annotation = method.getAnnotation(EncryptMethod.class);
        if (annotation != null) {
            if (type.equals(ENCRYPTFIELD)) {
                strArry = ((EncryptMethod) annotation).encrypt();
            } else if (type.equals(DECRYPTFIELD)) {
                strArry = ((EncryptMethod) annotation).decrypt();
            } else {
                strArry = null;
            }
        }
        return strArry;
    }

    /**
     * 获取Dao层接口方法
     *
     * @param @return
     * @return Method
     * @throws
     */
    private Method getDaoTargetMethod(MappedStatement mappedStatement) {
        Method method = null;
        try {
            String namespace = mappedStatement.getId();
            String className = namespace.substring(0, namespace.lastIndexOf("."));
            String methedName = namespace.substring(namespace.lastIndexOf(".") + 1, namespace.length());
            Method[] ms = Class.forName(className).getMethods();
            for (Method m : ms) {
                if (m.getName().equals(methedName)) {
                    method = m;
                    break;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            logger.info("EncryptDaoInterceptor.getDaoTargetMethod方法异常==> " + e.getMessage());
            return method;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.info("EncryptDaoInterceptor.getDaoTargetMethod方法异常==> " + e.getMessage());
            return method;
        }
        return method;
    }

    /**
     * 判断字符串是否需要加密
     *
     * @param @param  mappedStatement
     * @param @return
     * @return boolean
     * @throws
     */
    private boolean isEncryptStr(MappedStatement mappedStatement) throws ClassNotFoundException {
        boolean reslut = false;
        try {
            Method m = getDaoTargetMethod(mappedStatement);
            m.setAccessible(true);
            Annotation[][] parameterAnnotations = m.getParameterAnnotations();
            if (parameterAnnotations != null && parameterAnnotations.length > 0) {
                for (Annotation[] parameterAnnotation : parameterAnnotations) {
                    for (Annotation annotation : parameterAnnotation) {
                        if (annotation instanceof EncryptField) {
                            reslut = true;
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            logger.info("EncryptDaoInterceptor.isEncryptStr异常：==> " + e.getMessage());
            reslut = false;
        }
        return reslut;
    }

}

