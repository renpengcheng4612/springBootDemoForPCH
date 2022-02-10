package com.pc.sz.service;

import com.pc.sz.model.aspect.SysLogBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysLogService {
    public boolean save(SysLogBo sysLogBo){
        log.info(sysLogBo.getParams());
        log.info(sysLogBo.getClassName());
        return true;
    }
}
