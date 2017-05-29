package cn.kisoo.lasthttp;

/**
 * Created by kangqizhou on 2017/5/29.
 */

public class LastHttpClient {


    public static final class Builder{

        Dispatcher dispatcher;

        int readTimeOut;//读超时
        int writeTimeOut;//写超时
        int pingInterval;//ping超时


        public Builder(){
            dispatcher = new Dispatcher();
        }

        Builder(LastHttpClient client){

        }
    }

}
