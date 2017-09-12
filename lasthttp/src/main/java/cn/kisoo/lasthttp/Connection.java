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


    Protocol protocol();

    /***
     * 如果是https，返回tls握手的handshake。如果不是https，返回null
     * @return
     */
    Handshake handshake();
}
