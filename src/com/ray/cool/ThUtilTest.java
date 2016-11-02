package com.ray.cool;

import org.junit.Test;

/**
 * Created by en on 2016/11/2.
 */
public class ThUtilTest {
    @Test
    public void getCRC() throws Exception {
            String str="45 46 CB 00 00 37 07 00 E0 07 05 08 09 36 32";
            str=str.replace("　","").replace("，","").replace(",","").replace(" ","");
            byte[]abc= ThUtil.HexString2Bytes(str);
            System.out.println(ThUtil.getCRC(abc));
    }

}