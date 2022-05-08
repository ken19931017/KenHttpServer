package tcp.bio;

import java.io.*;
import java.net.Socket;

/**
 * <p> 简单的发起请求的TCP客户端</p>
 *
 * */
public class SimpleBioClient {

    public static void main(String[] args){
        try {
            //进行三次握手的tcp链接
            Socket client = new Socket("0.0.0.0",8888);
            //客户端输出流-》用户态-》内核态-》网卡-》服务端（SimpleBioServer）的网卡（DMA）
            OutputStream outputStream = client.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write("hello ken!\n");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
