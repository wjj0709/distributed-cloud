package com.phoenix.distributed.lang.define.iterator;

import com.phoenix.distributed.lang.utils.Exceptions;

import java.io.Serializable;
import java.util.Iterator;

/**
 * 空迭代器
 *
 * @author wjj-phoenix
 * @since 2021/8/2 0:43
 */
public class EmptyIterator<E> implements Iterator<E>, Iterable<E>, Serializable {

    private static final long serialVersionUID = 8739578349180L;

    public EmptyIterator() {
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        throw Exceptions.noSuchElement();
    }

    @Override
    public void remove() {
        throw Exceptions.unsupported("this is a read-only iterator");
    }

    @Override
    public Iterator<E> iterator() {
        return this;
    }

}
