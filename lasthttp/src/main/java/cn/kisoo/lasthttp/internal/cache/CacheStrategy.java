package cn.kisoo.lasthttp.internal.cache;

/**
 * Created by kangqizhou on 2017/7/6.
 */

import android.support.annotation.Nullable;

import cn.kisoo.lasthttp.Request;
import cn.kisoo.lasthttp.Response;

/***
 * 给一个请求和一个cached的响应，这个类会辨别出使用网络还是缓存，还是都使用
 * 选择一个缓存策略可能会给请求添加请求头，像 If-Modified-Since这样的
 */
public final class CacheStrategy {
    /**
     * 如果这个request为null，说明其不需要使用网络
     */
    @Nullable
    public final Request networkRequest;

    /**
     * 这个cached的response是用来返回或确认的。如果这个call不需要使用网络，则其为null
     */
    @Nullable
    private final Response cacheResponse;

    public CacheStrategy(Request networkRequest, Response cacheResponse) {
        this.networkRequest = networkRequest;
        this.cacheResponse = cacheResponse;
    }

    // TODO: 2017/7/6  
    public static boolean isCachedable(Response response,Request request){
        return false;
    }
}
