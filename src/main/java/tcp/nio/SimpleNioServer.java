package tcp.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>简单的支持NIO操作的TCP服务</p>
 *
 * */
public class SimpleNioServer {

      public static void main(String[] args){

            // 创建Selector和Channel
            try (Selector selector = Selector.open();
                 ServerSocketChannel serverSocket = ServerSocketChannel.open();) {
                  serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
                  serverSocket.configureBlocking(false);
                  // 注册到Selector，并说明关注点
                  serverSocket.register(selector, SelectionKey.OP_ACCEPT);
                  while (true) {
                        // 阻塞等待就绪的Channel，这是关键点之一
                        selector.select();
                        Set<SelectionKey> selectedKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iter = selectedKeys.iterator();
                        while (iter.hasNext()) {
                              SelectionKey key = iter.next();
                              ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                              iter.remove();
                        }
                   }
            } catch (IOException e) {
                  e.printStackTrace();
            }

      }


}
