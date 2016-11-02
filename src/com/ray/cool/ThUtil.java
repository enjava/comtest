package com.ray.cool;

/**
 * Created by en on 2016/11/2.
 */
public class ThUtil {

    //java CRC16校验
    public static int get_crc16 (byte[] bufData, int buflen, byte[] pcrc)
    {
        int ret = 0;
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;


        if (buflen == 0)
        {
            return ret;
        }
        for (i = 0; i < buflen; i++)
        {
            CRC ^= ((int)bufData[i] & 0x000000ff);
            for (j = 0; j < 8; j++)
            {
                if ((CRC & 0x00000001) != 0)
                {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                }
                else
                {
                    CRC >>= 1;
                }
            }
        }

        pcrc[0] = (byte)(CRC & 0x00ff);
        pcrc[1] = (byte)(CRC >> 8);

        return ret;
    }

    //获得CRC校验字符串  如  "88 DF"
    public static String getCRC(byte[] bytes){

        byte[] bb = new byte[2];
        get_crc16(bytes, bytes.length, bb);
        return Integer.toHexString((int)bb[0] & 0x000000ff)+" "+Integer.toHexString((int)bb[1] & 0x000000ff);
    }

    //十六进制byte转换成16进制的字符串
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    // 从十六进制字符串到字节数组转换
    public static byte[] HexString2Bytes(String hexstr) {
        hexstr=replase(hexstr);
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    public static boolean isEmpty(String str){
        if (str!=null&&str.length()>0 )
            return false;
            return true;
    }

    public static String replase(String str){
        if (isEmpty(str))
            return str;
        return str=str.replace("　","").replace("，","").replace(",","").replace(" ","");
    }


}
