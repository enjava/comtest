package com.ray.cool;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import static com.ray.cool.ThUtil.bytesToHexString;

/**
 * Created by en on 2016/11/2.
 */
public class TestMain {
    private static SerialPort serialPort = null;    //保存串口对象
    static {
        try {
            serialPort = SerialTool.openPort("COM1", 9600);
            SerialTool.addListener(serialPort, new SerialListener());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //serialPort = SerialTool.openPort("COM1", 9600);
        //在该串口对象上添加监听器
      //  SerialTool.addListener(serialPort, new SerialListener());
       //String strA="45 46 CB 04 00 31 18 00 A1 00 00 00 01 00 00 00 00 00 00 00 64 00 00 00 01 00 00 00 10 00 00 00 D0 EB";
        //发送                        货道编号         容量            故障码        货道价格     货道库存        货道商品ID
       // 45 46 CB 13 00 31 18 00    A1 00 00 00     01 00 00 00      00 00 00 00   64 00 00 00   01 00 00 00   10 00 00 00
     String strA="45 46 CB 08 00 31 18 00 A3 00 00 00 0F 00 00 00 00 00 00 00 FA 22 02 00 04 00 00 00 AF 0C 0C 00";
        //45 46 CB 09 00 30 1C 00 A3 00 00 00 0F 00 00 00 00 00 00 00 FA 22 02 00 AF 0C 00 00 04 00 00 00 00 00 00 00 8F 49
        //返回 4546CB080031000003B5
       //String strA="45 46 CB 09 00 30 04 00 A3 00 00 00";
        byte [] abc=ThUtil.hexString2Bytes(ThUtil.getCRCStr(strA));

        SerialTool.sendToPort(serialPort, abc);

        String str = bytesToHexString(abc);
        System.out.println(str.toUpperCase());
//        System.out.println(Arrays.toString(abc));
//        System.out.println(Arrays.toString(HexString2Bytes(str)));


    }

    // 为串口添加监听事件
    private static class SerialListener implements SerialPortEventListener {

        /**
         * 处理监控到的串口事件
         */
        public void serialEvent(SerialPortEvent serialPortEvent) {

            switch (serialPortEvent.getEventType()) {

                case SerialPortEvent.BI: // 10 通讯中断
                    System.out.println("10 通讯中断");
                    break;
                case SerialPortEvent.FE: // 9 帧错误
                    System.out.println("9 帧错误");
                    break;
                case SerialPortEvent.PE: // 8 奇偶校验错误
                    System.out.println("8 奇偶校验错误");
                    break;
                case SerialPortEvent.OE: // 7 溢位（溢出）错误
                    System.out.println("7 溢位（溢出）错误");
                    break;
                case SerialPortEvent.CD: // 6 载波检测
                    System.out.println("6 载波检测");
                    break;
                case SerialPortEvent.RI: // 5 振铃指示
                    System.out.println("5 振铃指示");
                    break;
                case SerialPortEvent.DSR: // 4 待发送数据准备好了
                    System.out.println("4 待发送数据准备好了");
                    break;
                case SerialPortEvent.CTS: // 3 清除待发送数据
                    System.out.println("3 清除待发送数据");
                    break;
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
                    System.out.println("2 输出缓冲区已清空");
                    break;

                case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据

                    byte[] data = null;

                    try {
                        if (serialPort == null)
                            System.out.println("串口对象为空！监听失败！");
                        else {
                            data = SerialTool.readFromPort(serialPort);    //读取数据，存入字节数组
                            // 自定义解析过程
                            if (data == null || data.length < 1) {    //检查数据是否读取正确
                                System.out.println("读取数据过程中未获取到有效数据！请检查设备或程序！");
                            } else {

                                System.out.println( bytesToHexString(data).toUpperCase());
                            }

                        }

                    } catch (Exception e) {
                        System.out.println("数据解析过程出错，请检查设备或程序！");
                    }

                    break;

            }

        }

    }


}
