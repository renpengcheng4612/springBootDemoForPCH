package com.chinaclear.sz.service;

import com.chinaclear.sz.model.aspect.SysLogBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysLogService {

    public boolean save(SysLogBo sysLogBo){
        log.info(sysLogBo.getParams());
        return true;
    }
}
