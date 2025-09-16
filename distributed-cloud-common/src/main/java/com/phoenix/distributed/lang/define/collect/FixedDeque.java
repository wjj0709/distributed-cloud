package com.phoenix.distributed.lang.define.collect;

import com.phoenix.distributed.lang.constant.Const;
import com.phoenix.distributed.lang.utils.Valid;

import java.io.Serializable;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 固长队列
 *
 * @author wjj-phoenix
 * @since 2020/11/6 11:30
 */
public class FixedDeque<E> extends ConcurrentLinkedDeque<E> implements Serializable {

    private static final long serialVersionUID = 923412312354068942L;

    private final int maxSize;

    public FixedDeque(int maxSize) {
        this.maxSize = Valid.gt(maxSize, Const.N_0);
    }

    public static <E> FixedDeque<E> create(int maxSize) {
        return new FixedDeque<>(maxSize);
    }

    @Override
    public boolean offerFirst(E s) {
        if (this.isFull()) {
            // 尾位出
            this.pollLast();
        }
        return super.offerFirst(s);
    }

    @Override
    public boolean offerLast(E s) {
        if (this.isFull()) {
            // 首位出
            this.pollFirst();
        }
        return super.offerLast(s);
    }

    /**
     * 队列是否已满
     *
     * @return 是否已满
     */
    public boolean isFull() {
        return this.size() >= maxSize;
    }

}
