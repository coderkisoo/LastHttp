package cn.kisoo.lasthttp;

import java.util.Collections;
import java.util.List;

/**
 * Created by kangqizhou on 2017/7/6.
 */

public interface CookieJar {
    CookieJar NO_COOKIES = new CookieJar(){

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            return Collections.emptyList();
        }
    };

    void saveFromResponse(HttpUrl url, List<Cookie> cookies);

    List<Cookie> loadForRequest(HttpUrl url);
}
