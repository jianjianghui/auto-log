package io.github.jianjianghui.autolog.springboot.autoconfigura;

import io.github.jianjianghui.autolog.spring.custom.config.SpringCustomLogProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * springboot日志配置
 *
 * @author <a href="mailto:jianjianghui@foxmail.com" >Jianghui Jian</a>
 * @date 2021/7/8 - 13:46
 */
@ConfigurationProperties(prefix = "jjh.web.auto.log")
public class SpringLogProperties {

    /**
     * 标题分隔符
     */
    public String titleSeparator = " ";
    /**
     * log开始头标题
     */
    public String logStartHead = "start --->";
    /**
     * log请求参数标题
     */
    public String logStartArgs = "[params]:";

    /**
     * log异常参数标题
     */
    public String logAbnormalHead = "exception --->";

    /**
     * log异常参数标题
     */
    public String logAbnormalArgs = "[异常信息]:";


    /**
     * log主题模板
     * {} -> 日志主题
     */
    public String logTopicTemplate = "[{}]";

    /**
     * log自定义操作模板
     * {} -> 日志操作内容
     */
    public String logOperationTemplate = "[{}]";
    /**
     * log结束头标题
     */
    public String logEndHead = "end   --->";
    /**
     * log响应参数标题
     */
    public String logEndArgs = "[result]:";
    /**
     * log多参数模板 第一个 {} 参数名， 第二个{} 参数值
     */
    public String logMultiParameterTemplate = "{}:{}";
    /**
     * log多参数分隔符
     */
    public String logMultipleParameterSeparator = ", ";
    /**
     * 响应数据标识
     */
    public ResponseTag responseTag = new ResponseTag();

    public CustomLog customLog = new CustomLog();

    public CustomLog getCustomLog() {
        return customLog;
    }

    public void setCustomLog(CustomLog customLog) {
        this.customLog = customLog;
    }

    public String getTitleSeparator() {
        return titleSeparator;
    }


    public void setTitleSeparator(String titleSeparator) {
        this.titleSeparator = titleSeparator;
    }


    public String getLogMultipleParameterSeparator() {
        return logMultipleParameterSeparator;
    }


    public void setLogMultipleParameterSeparator(String logMultipleParameterSeparator) {
        this.logMultipleParameterSeparator = logMultipleParameterSeparator;
    }

    public String getLogAbnormalHead() {
        return logAbnormalHead;
    }

    public void setLogAbnormalHead(String logAbnormalHead) {
        this.logAbnormalHead = logAbnormalHead;
    }

    public String getLogAbnormalArgs() {
        return logAbnormalArgs;
    }

    public void setLogAbnormalArgs(String logAbnormalArgs) {
        this.logAbnormalArgs = logAbnormalArgs;
    }

    public String getLogMultiParameterTemplate() {
        return logMultiParameterTemplate;
    }


    public void setLogMultiParameterTemplate(String logMultiParameterTemplate) {
        this.logMultiParameterTemplate = logMultiParameterTemplate;
    }


    public String getLogOperationTemplate() {
        return logOperationTemplate;
    }


    public void setLogOperationTemplate(String logOperationTemplate) {
        this.logOperationTemplate = logOperationTemplate;
    }


    public String getLogStartArgs() {
        return logStartArgs;
    }

    public void setLogStartArgs(String logStartArgs) {
        this.logStartArgs = logStartArgs;
    }


    public String getLogEndArgs() {
        return logEndArgs;
    }


    public void setLogEndArgs(String logEndArgs) {
        this.logEndArgs = logEndArgs;
    }


    public ResponseTag getResponseTag() {
        return responseTag;
    }

    public void setResponseTag(ResponseTag responseTag) {
        this.responseTag = responseTag;
    }


    public String getLogStartHead() {
        return logStartHead;
    }

    public void setLogStartHead(String logStartHead) {
        this.logStartHead = logStartHead;
    }

    public String getLogTopicTemplate() {
        return logTopicTemplate;
    }

    public void setLogTopicTemplate(String logTopicTemplate) {
        this.logTopicTemplate = logTopicTemplate;
    }

    public String getLogEndHead() {
        return logEndHead;
    }


    public void setLogEndHead(String logEndHead) {
        this.logEndHead = logEndHead;
    }

    public static class ResponseTag {
        /**
         * 响应错误码标识
         */
        public String codeTag = "code";

        /**
         * 响应消息标识
         */
        public String msgTag = "message";

        /**
         * 响应数据标识
         */
        public String dataTag = "data";


        public String getCodeTag() {
            return codeTag;
        }


        public void setCodeTag(String codeTag) {
            this.codeTag = codeTag;
        }


        public String getMsgTag() {
            return msgTag;
        }


        public void setMsgTag(String msgTag) {
            this.msgTag = msgTag;
        }


        public String getDataTag() {
            return dataTag;
        }


        public void setDataTag(String dataTag) {
            this.dataTag = dataTag;
        }
    }

    public static class CustomLog extends SpringCustomLogProperties {

    }

}
