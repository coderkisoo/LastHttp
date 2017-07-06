package cn.kisoo.lasthttp;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import cn.kisoo.lasthttp.internal.InternalCache;
import cn.kisoo.lasthttp.internal.Util;

/**
 * Created by kangqizhou on 2017/5/29.
 */

public class LastHttpClient implements Cloneable{

    static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableList(Protocol.HTTP_2,Protocol.HTTP_1_1);
    static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS = Util.immutableList(ConnectionSpec.MODERN_TLS,ConnectionSpec.CLEARTEXT);


    public static final class Builder{

        Dispatcher dispatcher;
        Proxy proxy;

        List<Protocol> protocols;
        List<ConnectionSpec> connectionSpecs;
        final List<Interceptor> interceptors = new ArrayList<>();
        final List<Interceptor> networkInterceptors = new ArrayList<>();

        ProxySelector proxySelector;
        CookieJar cookieJar;
        Cache cache;
        InternalCache internalCache;
        SocketFactory socketFactory;
        SSLSocketFactory sslSocketFactory;


        int readTimeOut;//读超时
        int writeTimeOut;//写超时
        int pingInterval;//ping超时


        public Builder(){
            dispatcher = new Dispatcher();
        }

        Builder(LastHttpClient client){

        }
    }

}
