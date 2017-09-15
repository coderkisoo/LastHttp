package cn.kisoo.lasthttp;

import android.support.annotation.Nullable;

import java.io.Reader;

/**
 * Created by kangqizhou on 2017/7/7.
 */

public abstract class ResponseBody implements Cloneable{

    private Reader reader;

    public abstract  @Nullable MediaType contentType();

}
