package cn.kisoo.lasthttp;

import java.util.List;

/**
 * Created by kangqizhou on 2017/7/7.
 */

public class Headers {

    public Headers(Builder builder) {

    }

    public List<String> valueOf(String name) {
        return null;
    }

    public static final class Builder{

        public Headers build() {
            return new Headers(this);
        }

        public void set(String name, String value) {

        }

        public void add(String name, String value) {

        }

        public void removeAll(String name) {

        }
    }
    public Builder newBuilder() {
        Builder result = new Builder();
        return result;
    }
}
