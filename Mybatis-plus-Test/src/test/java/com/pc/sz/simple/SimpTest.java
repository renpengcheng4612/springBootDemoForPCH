package com.pc.sz.simple;

import com.pc.sz.model.po.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SimpTest {
    @Test
    public void testSelect() {
        String email = "pcren@chinaclear.com";
        int i = email.indexOf('@');
        String substring = email.substring(0, i);
        System.out.println(substring);
    }
}
