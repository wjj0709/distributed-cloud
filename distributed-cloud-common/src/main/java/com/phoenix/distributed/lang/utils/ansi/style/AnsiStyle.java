package com.phoenix.distributed.lang.utils.ansi.style;

import com.phoenix.distributed.lang.utils.ansi.AnsiElement;

/**
 * ANSI 样式
 *
 * @author wjj-phoenix
 * @since 2023/8/28 15:15
 */
public interface AnsiStyle extends AnsiElement {

    /**
     * 添加样式
     *
     * @param next next
     * @return style
     */
    default AnsiStyle and(AnsiStyle next) {
        if (this instanceof AnsiStyleChain) {
            return this.and(next);
        } else {
            return AnsiStyleChain.create(this).and(next);
        }
    }

    /**
     * 获取 SGR Code
     *
     * @return SGR Code
     */
    String getCode();

}
