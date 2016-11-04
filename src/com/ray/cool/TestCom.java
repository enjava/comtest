package com.ray.cool;

import org.junit.Test;

/**
 * Created by en on 2016/11/2.
 */
public class TestCom {
    @Test
    public void test() throws Exception {
        String strA="45 46 CB 00 00 37 07 00 E0 07 05 08 09 36 32";
        System.out.println(ThUtil.getCRCStr(strA));
    }

    public static  int  test2(String [] abc){
        abc[0]="cdefg";
        return 0;
    }

    @Test
    public void abc(){
        String  str= "45 46 CB 06 00 31 00 00" ;
       // String[0] str="a";
       // test2(str);
        System.out.println(ThUtil.getCRCStr(str));
    }
    @Test
    public void ac(){
        String  str= "4546CB0800311800A30000000F00000000000000FA22020004000000AF0C0C008BA8" ;
        // String[0] str="a";
        // test2(str);
        System.out.println(ThUtil.fomatStr16(str));
    }
    @Test
    public void ab(){
        int javaReadInt =2010 ;
        // 将每个字节取出来
        int m=28;
        System.out.println( ThUtil.toHexString((byte)m));
        System.out.println(ThUtil.hiString2Bytes(javaReadInt));
        // 拼装成 正确的int

    }




}