package com.ray.cool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by en on 2016/10/27.
 */
public class HelloWorld {
    public static void main(String[] args) {
       // new byte[]{'a', 'b'};
        //45 46 CB 01 00 37 07 00 E0 07 0B 02 0A 29 24 88 DF
        int i=28;
       byte ab= 'A';
        System.out.println(   byteToInt(ab));
//     byte[] a=   int2Byte(i);
//
//        for (byte c:a
//             ) {
//            System.out.println(Integer.toBinaryString(c));
//        }
//        System.out.println(Integer.toBinaryString(i));
        Long timeStemp =  StrToDate().getTime();
        System.out.println(timeStemp );

    }
    public static byte[] int2Byte(int intValue){
        byte[] b=new byte[4];
        for(int i=0;i<4;i++){
            b[i]=(byte)(intValue>>8*(3-i) & 0xFF);
//            System.out.print(Integer.toBinaryString(b[i])+" ");
//            System.out.println("test");
//            System.out.print((b[i]& 0xFF)+" ");
        }
        return b;
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
     *
     * @param src
     *            byte数组
     * @param offset
     *            从数组的第offset位开始
     * @return int数值
     */
    public static int bytesToInt(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF)
                | ((src[offset+1] & 0xFF)<<8)
                | ((src[offset+2] & 0xFF)<<16)
                | ((src[offset+3] & 0xFF)<<24));
        return value;
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用
     */
    public static int bytesToInt2(byte[] src, int offset) {
        int value;
        value = (int) ( ((src[offset] & 0xFF)<<24)
                |((src[offset+1] & 0xFF)<<16)
                |((src[offset+2] & 0xFF)<<8)
                |(src[offset+3] & 0xFF));
        return value;
    }

    public static int byteToInt(byte b) {
        //Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return b & 0xFF;
    }


    public static Date StrToDate() {
       String  str="2016/11/02 10:41:36";
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
