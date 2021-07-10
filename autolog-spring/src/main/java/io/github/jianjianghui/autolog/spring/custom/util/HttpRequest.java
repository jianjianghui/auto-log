package io.github.jianjianghui.autolog.spring.custom.util;

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
    private final HttpServletRequest request;


    public HttpRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }


    public String ip() {
        if (Objects.isNull(this.request)) {
            return "";
        }

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
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
