package com.ray.cool;

import gnu.io.*;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by en on 2016/11/2.
 */
public class TestRxtx {

    public static final byte[] init = new byte[] { 0x1B, 0x40 };
    public static final byte[] clean = new byte[] { 0x0C };
    public static final byte[] pre_display = new byte[] { 0x1B, 0x51, 0x41 };
    public static final byte[] post_display = new byte[] { 0x0D };

    public static void displayCustomerScreen(String data, byte[] mode) {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM1"); //串口号
            SerialPort serialPort = (SerialPort) portIdentifier.open("test",2000); //使用者  和 最大响应时长(ms)
            serialPort.setSerialPortParams(9600,  //波特率
                    SerialPort.DATABITS_8,          //校验位
                    SerialPort.STOPBITS_1,          //数据位
                    SerialPort.PARITY_NONE);        //停止位
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            try {
                OutputStream outputStream = serialPort.getOutputStream();
                if (mode != null) {
                    outputStream.write(mode);
                }
                if (data != null) {
                    outputStream.write(pre_display);
                    outputStream.write(data.getBytes());
                    outputStream.write(post_display);
                }
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                serialPort.close();
            }
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (PortInUseException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        byte [] abc=new byte[]{(byte)0x45,(byte)0x46,(byte)0xCB,(byte)0x01,(byte)0x00,(byte)0x37,
                (byte)0x07,(byte)0x00,(byte)0xE0,(byte)0x07,(byte)0x0B,(byte)0x02,(byte)0x0A,(byte)0X29,(byte)0x24,(byte)0x88,(byte)0xDF};
        displayCustomerScreen(null,abc);
    }
}
