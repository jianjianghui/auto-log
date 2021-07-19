package io.github.jianjianghui.autolog.spring.custom.util;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * http request
 *
 * @author aqiu 2020/11/29 1:52
 * @author aqiu 2020/12/13 0:35
 * @author <a href="mailto:jianjianghui@foxmail.com" >Jianghui Jian</a>
 * @date 2021/7/10 - 13:40
 */
public class HttpRequest {
    private final static Logger log = LoggerFactory.getLogger(HttpRequest.class);
    private final static String UNKNOWN = "unknown";
    private final static int MAX_LENGTH = 15;

    private final HttpServletRequest request;


    public HttpRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }


    public String ip() {
        if (Objects.isNull(this.request)) {
            return "";
        }

        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StrUtil.isEmpty(ip) || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.error("IPUtils ERROR ", e);
        }
        // 使用代理，则获取第一个IP地址
        if (!StrUtil.isEmpty(ip) && ip.length() > MAX_LENGTH) {
            if (ip.indexOf(StrUtil.COMMA) > 0) {
                ip = ip.substring(0, ip.indexOf(StrUtil.COMMA));
            }
        }
        return ip;
    }

    public HttpServletRequest sourceRequest() {
        return this.request;
    }


    /**
     * 获取当前http请求
     *
     * @return null | {@link HttpRequest}
     */
    public static HttpRequest currentRequest() {
        final RequestAttributes requestAttributes = RequestContextHolder
                .getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return new HttpRequest(((ServletRequestAttributes) requestAttributes).getRequest());
        }

        return new HttpRequest(null);
    }
}
