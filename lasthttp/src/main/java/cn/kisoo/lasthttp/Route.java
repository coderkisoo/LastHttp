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


    public Route(Address address, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (address == null)
            throw new NullPointerException("address == null");
        if (proxy == null)
            throw new NullPointerException("proxy == null");
        if (inetSocketAddress == null)
            throw new NullPointerException("inetSocketAddress == null");
        this.inetSocketAddress = inetSocketAddress;
        this.proxy = proxy;
        this.address = address;
    }

    /**
     * 判断是否要建立隧道连接的依据是代理的类型，以及连接的类型：
     *
     * @return
     */
    public boolean requiresTunnel() {
        return address.sslSocketFactory != null && proxy.type() == Proxy.Type.HTTP;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Route
                && ((Route) obj).address.equals(address)
                && ((Route) obj).proxy.equals(proxy)
                && ((Route) obj).inetSocketAddress.equals(inetSocketAddress);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + address.hashCode();
        result = 31 * result + proxy.hashCode();
        result = 31 * result + inetSocketAddress.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Route{" + inetSocketAddress + "}";
    }
}
