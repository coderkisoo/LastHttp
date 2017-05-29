package cn.kisoo.lasthttp;


import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by kangqizhou on 2017/5/29.
 */

public final class Route {
    final Address address;
    final Proxy proxy;
    final InetSocketAddress inetSocketAddress;


    public Route(Address address,Proxy proxy,InetSocketAddress inetSocketAddress) {
        if (address==null)
            throw new NullPointerException("address == null");
        if (proxy == null)
            throw new NullPointerException("proxy == null");
        if (inetSocketAddress == null)
            throw new NullPointerException("inetSocketAddress == null");
        this.inetSocketAddress = inetSocketAddress;
        this.proxy = proxy;
        this.address = address;
    }

    public boolean requiresTunnel(){
        return address.
    }


}
