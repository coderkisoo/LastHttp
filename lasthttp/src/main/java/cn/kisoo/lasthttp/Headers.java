package cn.kisoo.lasthttp;

/**
 * Created by kangqizhou on 2017/7/7.
 */

public class Headers {

    public Headers(Builder builder) {

    }

    public static final class Builder{

        public Headers build() {
            return new Headers(this);
        }
    }
    public Builder newBuilder() {
        Builder result = new Builder();
        return result;
    }
}
