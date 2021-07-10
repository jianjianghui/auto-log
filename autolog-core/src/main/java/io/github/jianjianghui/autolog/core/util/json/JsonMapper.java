package io.github.jianjianghui.autolog.core.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonMapper
 *
 * @author 菅江晖
 * @date 2021/5/11 - 15:52
 */
public class JsonMapper {
    static volatile JsonMapper JSON_MAPPER;

    final public ObjectMapper objectMapper;

    public JsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        JSON_MAPPER = this;
    }


}
