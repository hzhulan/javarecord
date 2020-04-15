package com.hz.base;

import org.junit.Test;

/**
 * @authod: pp_lan on 2020/4/15.
 */
public class TenaryOperator {

    @Test
    /**
     * 测试类型不一致问题： 三目运算符转型的时候报空指针异常
     */
    public void test01() {
        boolean flag = false;
        Integer primary = null;
        Integer newInteger = flag ? 1 : primary;
        System.out.println(newInteger);
    }


}
