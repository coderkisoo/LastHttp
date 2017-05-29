package cn.kisoo.lasthttp;

import java.io.IOException;

/**
 * Created by kangqizhou on 2017/5/29.
 */

public interface Interceptor {
    Response intercept() throws IOException;

    interface Chain{
        Request request();

        Response preceed(Request request)throws IOException;


        Connection connection();
    }
}
