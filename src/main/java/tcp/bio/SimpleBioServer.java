package tcp.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>简单的支持BIO操作的TCP服务</p>
 *
 * **/
public class SimpleBioServer {


    public static void main(String[] args){

        try {
            //三次握手创建TCP链接
            ServerSocket serverSocket = new ServerSocket(10003);
            //死循环可以保证服务端口的一直响应
            while (true){
                //从已经创建好的链接链表backlog里获取一个链接转换为socket(四元组)->fd
                Socket client = serverSocket.accept();
                //客户端的请求ip
                System.out.println(client.getInetAddress().getHostAddress());
                //网卡（DMA）-》内核-》应用态-》输入流-》转换为字符流进行查看
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String test = reader.readLine();
                System.out.println(test);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
