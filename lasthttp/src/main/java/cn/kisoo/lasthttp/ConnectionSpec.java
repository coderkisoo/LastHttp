package cn.kisoo.lasthttp;

import android.support.annotation.Nullable;

/**
 * Created by kangqizhou on 2017/5/29.
 */
public final class ConnectionSpec {

    public final static ConnectionSpec CLEARTEXT = new Builder(false).build();
    public final static ConnectionSpec MODERN_TLS = new Builder(true).build();

    boolean tls;
    String[] cipherSuites;
    String[] tlsVersions;
    boolean supportsTlsExtensions;

    public ConnectionSpec(Builder builder) {
        this.tls = builder.tls;
        this.cipherSuites = builder.cipherSuites;
        this.tlsVersions = builder.tlsVersions;
        this.supportsTlsExtensions = builder.supportsTlsExtensions;
    }

    public static final class Builder{

        boolean tls;
        @Nullable String[] cipherSuites;
        @Nullable String[] tlsVersions;
        boolean supportsTlsExtensions;

        Builder(ConnectionSpec connectionSpec){
            this.tls = connectionSpec.tls;
            this.cipherSuites = connectionSpec.cipherSuites;
            this.tlsVersions = connectionSpec.tlsVersions;
            this.supportsTlsExtensions = connectionSpec.supportsTlsExtensions;
        }

        Builder(boolean tls){
            this.tls = tls;
        }

        public Builder allEnabledCipherSuites(){
            if (!tls) throw new IllegalStateException("no cipher suites for cleartext connections");
            this.cipherSuites = null;
            return this;
        }


        public ConnectionSpec build() {
            return new ConnectionSpec(this);
        }
    }
}
