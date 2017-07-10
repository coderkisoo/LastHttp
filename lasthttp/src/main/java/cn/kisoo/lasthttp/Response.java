package cn.kisoo.lasthttp;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static cn.kisoo.lasthttp.internal.StatusLine.HTTP_PERM_REDIRECT;
import static cn.kisoo.lasthttp.internal.StatusLine.HTTP_TEMP_REDIRECT;
import static java.net.HttpURLConnection.HTTP_MOVED_PERM;
import static java.net.HttpURLConnection.HTTP_MOVED_TEMP;
import static java.net.HttpURLConnection.HTTP_MULT_CHOICE;
import static java.net.HttpURLConnection.HTTP_PROXY_AUTH;
import static java.net.HttpURLConnection.HTTP_SEE_OTHER;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;


/**
 * Created by kangqizhou on 2017/5/29.
 */

public class Response {
    final Request request;
    final Protocol protocol;
    final int code;
    final String message;
    final Handshake handshake;
    final Headers headers;
    final ResponseBody body;

    final Response networkResponse;
    final Response cacheResponse;
    final Response priorResponse;
    final long sentRequestAtMillis;
    final long receivedResponseAtMillis;

    private volatile CacheControl cacheControl;//懒加载


    public Response(Builder builder) {
        this.request = builder.request;
        this.protocol = builder.protocol;
        this.code = builder.code;
        this.message = builder.message;
        this.handshake = builder.handshake;
        this.headers = builder.headers.build();
        this.body = builder.body;
        this.networkResponse = builder.networkResponse;
        this.cacheResponse = builder.cacheResponse;
        this.priorResponse = builder.priorResponse;
        this.sentRequestAtMillis = builder.sentRequestAtMillis;
        this.receivedResponseAtMillis = builder.receivedResponseAtMillis;
    }

    public Request request() {
        return request;
    }

    public Protocol protocol() {
        return protocol;
    }

    public boolean isSuccessful() {
        return code > 200 && code < 300;
    }

    public String message() {
        return message;
    }

    public Handshake shandshake() {
        return handshake;
    }

    public List<String> headers(String name) {
        return headers.valueOf(name);
    }

    public Headers headers() {
        return headers;
    }

    // TODO: 2017/7/9
    public ResponseBody peekBody(long byteCount) throws IOException {

        return null;
    }

    public @Nullable ResponseBody body(){
        return body;
    }

    public Builder newBuilder(){
        return new Builder(this);
    }

    public boolean isRedirect(){
        switch (code){
            case HTTP_PERM_REDIRECT:
            case HTTP_TEMP_REDIRECT:
            case HTTP_MULT_CHOICE:
            case HTTP_MOVED_PERM:
            case HTTP_MOVED_TEMP:
            case HTTP_SEE_OTHER:
                return true;
            default:
                return false;
        }
    }

    public Response networkResponse(){
        return networkResponse;
    }

    public Response cacheResponse(){
        return cacheResponse;
    }

    public Response priorResponse(){
        return priorResponse;
    }

    public List<Challenge> challenges(){
        String responseField;
        if (code == HTTP_UNAUTHORIZED){
            responseField = "WWW-Authenticate";
        }else if (code == HTTP_PROXY_AUTH){
            responseField = "Proxy-Authenticate";
        }else {
            return Collections.emptyList();
        }
        return HttpHeaders.parseChallenges(headers(),responseField);
    }


    public static class Builder {
        Request request;
        Protocol protocol;
        int code = -1;
        String message;
        Handshake handshake;
        Headers.Builder headers;
        ResponseBody body;

        Response networkResponse;
        Response cacheResponse;
        Response priorResponse;
        long sentRequestAtMillis;
        long receivedResponseAtMillis;

        public Builder(){
            headers = new Headers.Builder();
        }

        public Builder(Response response) {
            this.request = response.request;
            this.protocol = response.protocol;
            this.code = response.code;
            this.message = response.message;
            this.handshake = response.handshake;
            this.headers = response.headers.newBuilder();
            this.body = response.body;
            this.networkResponse = response.networkResponse;
            this.cacheResponse = response.cacheResponse;
            this.priorResponse = response.priorResponse;
            this.sentRequestAtMillis = response.sentRequestAtMillis;
            this.receivedResponseAtMillis = response.receivedResponseAtMillis;
        }
        public Builder request(Request request) {
            this.request = request;
            return this;
        }

        public Builder protocol(Protocol protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder handshake(@Nullable Handshake handshake) {
            this.handshake = handshake;
            return this;
        }

        /**
         * Sets the header named {@code name} to {@code value}. If this request already has any headers
         * with that name, they are all replaced.
         */
        public Builder header(String name, String value) {
            headers.set(name, value);
            return this;
        }

        /**
         * Adds a header with {@code name} and {@code value}. Prefer this method for multiply-valued
         * headers like "Set-Cookie".
         */
        public Builder addHeader(String name, String value) {
            headers.add(name, value);
            return this;
        }

        public Builder removeHeader(String name) {
            headers.removeAll(name);
            return this;
        }

        /** Removes all headers on this builder and adds {@code headers}. */
        public Builder headers(Headers headers) {
            this.headers = headers.newBuilder();
            return this;
        }

        public Builder body(@Nullable ResponseBody body) {
            this.body = body;
            return this;
        }

        public Builder networkResponse(@Nullable Response networkResponse) {
            if (networkResponse != null) checkSupportResponse("networkResponse", networkResponse);
            this.networkResponse = networkResponse;
            return this;
        }

        public Builder cacheResponse(@Nullable Response cacheResponse) {
            if (cacheResponse != null) checkSupportResponse("cacheResponse", cacheResponse);
            this.cacheResponse = cacheResponse;
            return this;
        }

        private void checkSupportResponse(String name, Response response) {
            if (response.body != null) {
                throw new IllegalArgumentException(name + ".body != null");
            } else if (response.networkResponse != null) {
                throw new IllegalArgumentException(name + ".networkResponse != null");
            } else if (response.cacheResponse != null) {
                throw new IllegalArgumentException(name + ".cacheResponse != null");
            } else if (response.priorResponse != null) {
                throw new IllegalArgumentException(name + ".priorResponse != null");
            }
        }

        public Builder priorResponse(@Nullable Response priorResponse) {
            if (priorResponse != null) checkPriorResponse(priorResponse);
            this.priorResponse = priorResponse;
            return this;
        }

        private void checkPriorResponse(Response response) {
            if (response.body != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public Builder sentRequestAtMillis(long sentRequestAtMillis) {
            this.sentRequestAtMillis = sentRequestAtMillis;
            return this;
        }

        public Builder receivedResponseAtMillis(long receivedResponseAtMillis) {
            this.receivedResponseAtMillis = receivedResponseAtMillis;
            return this;
        }

        public Response build() {
            if (request == null) throw new IllegalStateException("request == null");
            if (protocol == null) throw new IllegalStateException("protocol == null");
            if (code < 0) throw new IllegalStateException("code < 0: " + code);
            if (message == null) throw new IllegalStateException("message == null");
            return new Response(this);
        }

    }

}
