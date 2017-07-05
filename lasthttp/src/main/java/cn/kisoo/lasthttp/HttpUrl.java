package cn.kisoo.lasthttp;

import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by kangqizhou on 2017/5/29.
 */
public class HttpUrl {

    public static final String SCHEME_HTTP = "http";
    public static final String SCHEME_HTTPS = "https";

    final String scheme;
    final String host;
    final int port;
    final String url;

    private HttpUrl(Builder builder) {
        this.scheme = builder.scheme;
        this.host = builder.host;
        this.port = builder.effectivePort();
        this.url = builder.toString();
    }

    public String host() {
        return host;
    }

    public int port() {
        return port;
    }

    public static final class Builder {
        @Nullable
        String scheme;
        @Nullable
        String host;
        int port;

        public int effectivePort() {
            return port == -1 ? defaultPort(scheme) : port;
        }

        private int defaultPort(String scheme) {
            switch (scheme) {
                case SCHEME_HTTP:
                    return 80;
                case SCHEME_HTTPS:
                    return 443;
                default:
                    return -1;
            }
        }

        public Builder scheme(String scheme) {
            if (TextUtils.isEmpty(scheme))
                throw new NullPointerException("scheme == null");
            if (scheme.equalsIgnoreCase(SCHEME_HTTP))
                this.scheme = SCHEME_HTTP;
            else if (scheme.equalsIgnoreCase(SCHEME_HTTPS))
                this.scheme = SCHEME_HTTPS;
            else
                throw new IllegalArgumentException("unexpected scheme:" + scheme);
            this.scheme = scheme;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            if (port <= 0 || port > 65535) throw new IllegalArgumentException("unexpected port: " + port);
            this.port = port;
            return this;
        }

        public HttpUrl build() {
            if (scheme == null) throw new IllegalStateException("scheme == null");
            if (host == null) throw new IllegalStateException("host == null");
            return new HttpUrl(this);
        }
    }
}
