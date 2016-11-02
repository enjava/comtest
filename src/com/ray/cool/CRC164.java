package com.ray.cool;

/**
 * Created by en on 2016/11/2.
 */
public class CRC164 {
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
            //System.out.println(Integer.toHexString(CRC));
        }

        //System.out.println(Integer.toHexString(CRC));
        pcrc[0] = (byte)(CRC & 0x00ff);
        pcrc[1] = (byte)(CRC >> 8);

        return ret;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
//        byte[] aa = {0x30,0x30,0x34,0x36,0x46,0x44,0x36,0x30,0x30,0x30,0x01,0x72,0x65,
//                0x66,0x65,0x72,0x69,0x6E,0x66,0x6F,0x2E,0x63,0x73,0x76,0x00,0x00
//                ,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00
//                ,0x00,0x00,0x00,0x01,(byte)0xf4,0x01};
        byte [] abc=new byte[]{(byte)0x45,(byte)0x46,(byte)0xCB,(byte)0x01,(byte)0x00,(byte)0x37,
                (byte)0x07,(byte)0x00,(byte)0xE0,(byte)0x07,(byte)0x0B,(byte)0x02,(byte)0x0A,(byte)0X29,(byte)0x24};
        byte[] bb = new byte[2];
        CRC164.get_crc16(abc, abc.length, bb);

        System.out.println(Integer.toHexString((int)bb[0] & 0x000000ff));
        System.out.println(Integer.toHexString((int)bb[1] & 0x000000ff));


    }
}
