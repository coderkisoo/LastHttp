package cn.kisoo.lasthttp.internal;

import cn.kisoo.lasthttp.LastHttpClient;

/**
 * Created by kangqizhou on 2017/7/6.
 */

public abstract class Internal {

    public static void initializeInstanceForTests(){
        new LastHttpClient();
    }

    public static Internal instance;

}
