package cn.kisoo.lasthttp.internal;

import java.io.IOException;
import java.net.CacheRequest;

import cn.kisoo.lasthttp.Request;
import cn.kisoo.lasthttp.Response;
import cn.kisoo.lasthttp.internal.cache.CacheStrategy;

/**
 * Created by kangqizhou on 2017/7/6.
 */
//内部的Cache接口。仅限内部使用
public interface InternalCache {
    Response get(Request request) throws IOException;

    CacheRequest put(Response response) throws IOException;

    //移除关于request的cache，将会在客户端不再需要这个cache，就像做post请求时候一样。
    void remove(Request request)throws IOException;

    void update(Response cached,Response network);
    //监测一个有条件的满足缓存的GET请求
    void trackConditionCacheHit();
    //监测一个http满足cache策略的响应
    void trackResponse(CacheStrategy cacheStrategy);
}
