package io.github.jianjianghui.autolog.spring.custom.config;

/**
 * SpringCustomLogProperties
 *
 * @author <a href="mailto:jianjianghui@foxmail.com" >Jianghui Jian</a>
 * @date 2021/7/10 - 12:43
 */
public class SpringCustomLogProperties {
    /**
     * 启用ip 记录
     */
    boolean enableIp;

    /**
     * ip标识
     */
    String ipTag;

    /**
     * 启用唯一值记录
     */
    boolean enableUniqueCode;

    /**
     * 唯一值标识
     */
    String uniqueCodeTag;


    public String getIpTag() {
        return ipTag;
    }

    public void setIpTag(String ipTag) {
        this.ipTag = ipTag;
    }

    public String getUniqueCodeTag() {
        return uniqueCodeTag;
    }

    public void setUniqueCodeTag(String uniqueCodeTag) {
        this.uniqueCodeTag = uniqueCodeTag;
    }

    public boolean isEnableIp() {
        return enableIp;
    }

    public void setEnableIp(boolean enableIp) {
        this.enableIp = enableIp;
    }

    public boolean getEnableIp() {
        return enableIp;
    }

    public boolean isEnableUniqueCode() {
        return enableUniqueCode;
    }

    public boolean getEnableUniqueCode() {
        return enableUniqueCode;
    }


    public void setEnableUniqueCode(boolean enableUniqueCode) {
        this.enableUniqueCode = enableUniqueCode;
    }
}
