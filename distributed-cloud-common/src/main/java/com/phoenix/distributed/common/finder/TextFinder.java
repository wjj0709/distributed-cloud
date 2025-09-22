package com.phoenix.distributed.common.finder;

import com.phoenix.distributed.common.lang.Assert;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 * 文本查找抽象类
 */
public abstract class TextFinder implements Finder, Serializable {
    private static final long serialVersionUID = 1L;

    protected CharSequence text;
    protected int endIndex = -1;
    protected boolean negative;

    /**
     * 设置被查找的文本
     *
     * @param text 文本
     * @return this
     */
    public TextFinder setText(CharSequence text) {
        this.text = Assert.notNull(text, "Text must be not null!");
        return this;
    }

    /**
     * 断言对象是否不为{@code null} ，如果为{@code null} 抛出指定类型异常
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     * Assert.notNull(clazz, ()-&gt;{
     *      // to query relation message
     *      return new IllegalArgumentException("relation message to return");
     *  });
     * </pre>
     *
     * @param <T>           被检查对象泛型类型
     * @param <X>           异常类型
     * @param object        被检查对象
     * @param errorSupplier 错误抛出异常附带的消息生产接口
     * @return 被检查后的对象
     * @throws X if the object is {@code null}
     */
    public static <T, X extends Throwable> T notNull(T object, Supplier<X> errorSupplier) throws X {
        if (null == object) {
            throw errorSupplier.get();
        }
        return object;
    }

    /**
     * 设置查找的结束位置<br>
     * 如果从前向后查找，结束位置最大为text.length()<br>
     * 如果从后向前，结束位置为-1
     *
     * @param endIndex 结束位置（不包括）
     * @return this
     */
    public TextFinder setEndIndex(int endIndex) {
        this.endIndex = endIndex;
        return this;
    }

    /**
     * 设置是否反向查找，{@code true}表示从后向前查找
     *
     * @param negative 结束位置（不包括）
     * @return this
     */
    public TextFinder setNegative(boolean negative) {
        this.negative = negative;
        return this;
    }

    /**
     * 获取有效结束位置<br>
     * 如果{@link #endIndex}小于0，在反向模式下是开头（-1），正向模式是结尾（text.length()）
     *
     * @return 有效结束位置
     */
    protected int getValidEndIndex() {
        if(negative && -1 == endIndex){
            // 反向查找模式下，-1表示0前面的位置，即字符串反向末尾的位置
            return -1;
        }
        final int limit;
        if (endIndex < 0) {
            limit = endIndex + text.length() + 1;
        } else {
            limit = Math.min(endIndex, text.length());
        }
        return limit;
    }
}