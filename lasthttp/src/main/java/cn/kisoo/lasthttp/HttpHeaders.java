package cn.kisoo.lasthttp;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by kangqizhou on 2017/7/10.
 */
public class HttpHeaders {

    private static final String TOKEN = "([^ \"=]*)";
    private static final String QUOTED_STRING = "\"([^\"]*)\"";
    private static final Pattern PARAMETER
            = Pattern.compile(" +" + TOKEN + "=(:?" + QUOTED_STRING + "|" + TOKEN + ") *(:?,|$)");

    public static List<Challenge> parseChallenges(Headers headers, String responseField) {
        return null;
    }
}
