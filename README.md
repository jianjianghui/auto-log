# auto-log

#### 介绍
java 接口层请求日志记录最佳实践

* 注意: 只记录操作日志(方法内没有抛出异常,方法返回结果统一),不记录错误日志

开箱即用,详见 ``autolog-demo`` 模块  

支持自定义配置


#### 使用说明

1.  引入 autolog-spring-boot-starter
2.  Application 使用 ``@EnableAutoLog(rest='你的后端统一返回结果Class',pathBackages={"...启用自动日志的包"})``  参考``autolog-demo``
3.  配置后端统一返回结果对象``{code:0,msg:"",data:null}``在 application.yml 中 详见 ``autolog-demo application.yml``
4.  后端统一返回结果对象目前只支持 ``getXXX`` 方式获取内容，未来将支持``map``

