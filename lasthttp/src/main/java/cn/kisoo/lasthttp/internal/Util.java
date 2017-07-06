package cn.kisoo.lasthttp.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by kangqizhou on 2017/7/6.
 */
public class Util {

    /** Returns an immutable list  **/
    public static <T> List<T> immutableList(T... elements){
        return Collections.unmodifiableList(Arrays.asList(elements.clone()));
    }

}
