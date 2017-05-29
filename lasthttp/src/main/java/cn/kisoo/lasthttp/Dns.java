package cn.kisoo.lasthttp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kangqizhou on 2017/5/29.
 */
public interface Dns {

    Dns SYSTEM = new Dns() {

        @Override
        public List<InetAddress> lookup(String hostname) throws UnknownHostException {
            if (hostname==null)
                throw new UnknownHostException("hostname == null");
            return Arrays.asList(InetAddress.getAllByName(hostname));
        }
    };

    List<InetAddress> lookup(String hostname) throws UnknownHostException;

}
