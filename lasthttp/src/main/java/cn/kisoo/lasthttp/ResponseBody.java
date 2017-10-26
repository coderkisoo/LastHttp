package cn.kisoo.lasthttp;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Created by kangqizhou on 2017/7/7.
 */

public abstract class ResponseBody implements Cloneable {

    private Reader reader;

    public abstract
    @Nullable
    MediaType contentType();

    public abstract long contentLength();

    public final InputStream byteStream() {
        return null;
    }

    public final byte[] bytes() throws IOException {
        long contentLength = contentLength();
        if (contentLength > Integer.MAX_VALUE) {
            throw new IOException("Cannot buffer entire body for content length:" + contentLength);
        }
        return null;
    }

}
