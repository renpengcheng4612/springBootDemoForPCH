package com.pc.sz.model.aspect;

import lombok.Data;

/*
*  系统日志bo
* */
@Data
public class SysLogBo {

    private String className;

    private String methodName;

    private String params;

    private Long exeuTime;

    private String remark;

    private String createDate;
}
