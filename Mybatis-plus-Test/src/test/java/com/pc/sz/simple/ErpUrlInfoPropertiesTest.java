package com.pc.sz.simple;

import com.pc.sz.config.ApiInfo;
import com.pc.sz.config.ErpUrlInfoProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class ErpUrlInfoPropertiesTest {
    @Autowired
    private ErpUrlInfoProperties erpUrlInfoProperties;

    @Test
    public void testSelect() {
        String erpUrl = erpUrlInfoProperties.getErpUrl();
        log.info("-------------[{}]", erpUrl);
        ApiInfo singleOrganization = erpUrlInfoProperties.getSingleOrganization();
        log.info("-------------[{}]", singleOrganization);
        log.info("-------------[{}]", singleOrganization.getTokenMap());

    }
}
