package cn.kisoo.lasthttp;

import java.net.Socket;

/**
 * Created by kangqizhou on 2017/5/29.
 */

public interface Connection {

    /***
     * 返回当前连接的路由
     * @return
     */
    Route route();

    /***
     * 返回当前连接所用的connection
     * @return
     */
    Socket socket();

    /**
     * 返回Connection协商好的协议, 如果没有协商，默认HTTP1.0，甚至在远程主机使用http1.1的时候，这个方法
     * 也会返回HTTP1.1{@link Protocol#HTTP_1_1}
     */
    Protocol protocol();

    /***
     * 如果是https，返回tls握手的handshake。如果不是https，返回null
     * @return
     */
    Handshake handshake();
}
