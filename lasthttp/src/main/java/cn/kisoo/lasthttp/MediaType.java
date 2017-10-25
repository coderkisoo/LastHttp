package cn.kisoo.lasthttp;

/**
 * Created by kangqizhou on 2017/9/15.
 */

import android.support.annotation.Nullable;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * http请求或响应的content-type
 * 来自rfc 2045
 */
public final class MediaType {
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final Pattern TYPE_SUBTYPE = Pattern.compile(TOKEN + "/" + TOKEN);
    private static final Pattern PARAMETER = Pattern.compile(
            ";\\s*(?:" + TOKEN + "=(?:" + TOKEN + "|" + QUOTED + "))?");


    private final String mediaType;
    private final String type;
    private final String subtype;
    private final
    @Nullable
    String charset;

    private MediaType(String mediaType, String type, String subtype, @Nullable String charset) {
        this.mediaType = mediaType;
        this.type = type;
        this.subtype = subtype;
        this.charset = charset;
    }

    public static
    @Nullable
    MediaType parse(String string) {
        Matcher typeSubType = TYPE_SUBTYPE.matcher(string);
        if (!typeSubType.lookingAt()) return null;
        String type = typeSubType.group(1).toLowerCase(Locale.US);
        String subtype = typeSubType.group(2).toLowerCase(Locale.US);

        String charset = null;
        Matcher parameter = PARAMETER.matcher(string);
        // TODO: 2017/10/23
        for (int s = typeSubType.end(); s < string.length(); s = parameter.end()) {
            parameter.region(s, string.length());
            if (!parameter.lookingAt())
                return null;//不能被解析的类型
            String name = parameter.group(1);
            if (name == null || !name.equalsIgnoreCase("charset"))
                continue;
            String charsetParameter;
            String token = parameter.group(2);
            if (token != null) {
                charsetParameter = (token.startsWith("'") && token.endsWith("'") && token.length() > 2)
                        ? token.substring(1, token.length() - 1)
                        : token;
            } else {
                charsetParameter = parameter.group(3);
            }
            if (charset != null && !charsetParameter.equalsIgnoreCase(charset)) {
                return null;
            }
            charset = charsetParameter;
        }

        return new MediaType(string, type, subtype, charset);
    }
}
