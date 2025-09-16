package com.phoenix.distributed.lang.able;

/**
 * 适配器接口
 *
 * @author wjj-phoenix
 * @since 2019/11/18 18:12
 */
public interface Adaptable<N> {

    /**
     * 适配为新对象
     *
     * @return N
     */
    N forNew();

}
