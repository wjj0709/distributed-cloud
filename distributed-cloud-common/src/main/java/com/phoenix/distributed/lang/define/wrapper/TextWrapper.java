package com.phoenix.distributed.lang.define.wrapper;

import com.phoenix.distributed.lang.constant.Const;
import com.phoenix.distributed.lang.define.support.CloneSupport;
import com.phoenix.distributed.lang.utils.Urls;
import com.phoenix.distributed.lang.utils.Xsses;
import com.phoenix.distributed.lang.utils.codec.Base64s;

/**
 * restful 文本包装
 *
 * @author wjj-phoenix
 * @since 2020/1/2 18:00
 */
public class TextWrapper extends CloneSupport<TextWrapper> implements Wrapper<String> {

    private static final long serialVersionUID = 5693256198048111L;

    private final StringBuilder sb;

    public TextWrapper() {
        this(new StringBuilder());
    }

    public TextWrapper(String s) {
        this(new StringBuilder(s));
    }

    public TextWrapper(StringBuilder sb) {
        if (sb == null) {
            this.sb = new StringBuilder();
        } else {
            this.sb = sb;
        }
    }

    public static TextWrapper get() {
        return new TextWrapper();
    }

    public static TextWrapper get(String s) {
        return new TextWrapper(s);
    }

    public static TextWrapper get(StringBuilder sb) {
        return new TextWrapper(sb);
    }

    public TextWrapper append(String s) {
        sb.append(s);
        return this;
    }

    public TextWrapper appendLine(String s) {
        sb.append(s).append(Const.LF);
        return this;
    }

    public TextWrapper newLine() {
        sb.append(Const.LF);
        return this;
    }

    public TextWrapper newLine(String eof) {
        sb.append(eof);
        return this;
    }

    public String encodeXss() {
        return Xsses.clean(sb.toString());
    }

    public String decodeXss() {
        return Xsses.recode(sb.toString());
    }

    public String encodeUrl() {
        return Urls.encode(sb.toString());
    }

    public String decodeUrl() {
        return Urls.decode(sb.toString());
    }

    public String encodeBase64() {
        return Base64s.encode(sb.toString());
    }

    public String decodeBase64() {
        return Base64s.decode(sb.toString());
    }

    public String text() {
        return sb.toString();
    }

    @Override
    public String toString() {
        return sb.toString();
    }

}
