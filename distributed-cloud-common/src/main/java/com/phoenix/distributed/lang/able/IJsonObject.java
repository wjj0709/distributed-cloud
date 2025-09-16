package com.phoenix.distributed.lang.able;

import com.phoenix.distributed.lang.utils.json.Jsons;

/**
 * 对象转 json 接口
 *
 * @author wjj-phoenix
 * @since 2019/8/15 20:34
 */
public interface IJsonObject {

    /**
     * 对象转 json
     *
     * @return json
     */
    default String toJsonString() {
        return Jsons.toJsonWriteNull(this);
    }

}
