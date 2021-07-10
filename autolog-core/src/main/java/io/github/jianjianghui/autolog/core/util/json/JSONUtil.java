package io.github.jianjianghui.autolog.core.util.json;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/**
 * json Util
 *
 * @author 菅江晖
 * @date 2021/5/11 - 15:52
 */
@SuppressWarnings("all")
public class JSONUtil {
    private static JsonMapper JSON_MAPPER = JsonMapper.JSON_MAPPER;
    private final static Logger log = LoggerFactory.getLogger(JSONUtil.class);
    private final static ReentrantLock lock = new ReentrantLock();


    public static void hotReload() {

        if (Objects.nonNull(JSON_MAPPER)) {
            return;
        }

        lock.lock();
        try {
            JSON_MAPPER = JsonMapper.JSON_MAPPER;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 对象转为json字符串
     *
     * @param object
     * @return null（toJson fail） or jsonStr
     */
    public static String toJSONString(Object object) {
        hotReload();
        log.trace("使用" + (Optional.ofNullable(JSON_MAPPER).isPresent() ? Optional.ofNullable(JSON_MAPPER.objectMapper).isPresent() ? "Jackson" : "FastJson" : "FastJson") + "处理");
        try {
            return Optional.ofNullable(JSON_MAPPER).isPresent()
                    ? Optional.ofNullable(JSON_MAPPER.objectMapper).isPresent()
                    ? JSON_MAPPER.objectMapper.writeValueAsString(object)
                    : JSON.toJSONString(object)
                    : JSON.toJSONString(object);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * json字符串转对象
     *
     * @param json
     * @param klass
     * @param <T>
     * @return null（parse fail） or Object
     */
    public static <T> T parseObject(String json, Class<T> klass) {
        hotReload();
        log.trace("使用" + (Optional.ofNullable(JSON_MAPPER).isPresent() ? Optional.ofNullable(JSON_MAPPER.objectMapper).isPresent() ? "Jackson" : "FastJson" : "FastJson") + "处理");
        try {
            return Optional.ofNullable(JSON_MAPPER).isPresent()
                    ? Optional.ofNullable(JSON_MAPPER.objectMapper).isPresent()
                    ? JSON_MAPPER.objectMapper.readValue(json, klass)
                    : JSON.parseObject(json, klass)
                    : JSON.parseObject(json, klass);
        } catch (Exception e) {
            return null;
        }
    }


}
