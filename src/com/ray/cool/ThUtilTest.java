package com.ray.cool;

import org.junit.Test;

/**
 * Created by en on 2016/11/2.
 */
public class ThUtilTest {
    @Test
    public void getCRC() throws Exception {
           // String str="45 46 CB 00 00 37 07 00 E0 07 05 08 09 36 32";
           // String str="";
           String str="45 46 CB 06 00 31 18 00 A1 00 00 00 01 00 00 00 00 00 00 00 64 00 00 00 01 00 00 00 10 00 00 00";
            //String str="45 46 CB 05 00 36 00 00";
            //String str="45 46 CB 01 00 34 13 00 A1 00 00 00 64 00 00 00 02 00 00 00 01 00 00 00 31 32 33 34 30 31 00";
            str=str.replace("　","").replace("，","").replace(",","").replace(" ","");
            byte[]abc= ThUtil.HexString2Bytes(str);
            System.out.println(ThUtil.getCRC(abc));
    }

}