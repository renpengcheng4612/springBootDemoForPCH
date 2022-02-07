package com.chinaclear.sz.model.aspect;

import lombok.Data;

@Data
public class SysLogBo {

    private String className;

    private String methodName;

    private String params;

    private Long exeuTime;

    private String remark;

    private String createDate;
}
