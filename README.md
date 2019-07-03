# Manhattan
## v1.0.0 ##
* 简单的SpringBoot+MyBatis+Bootstrap整合
* 实现登录session验证
## v1.1.0 ##
* 引入Redis进行session管理
## v1.2.0 ##
* 引入swagger2
* 增加ignore文件
## v1.3.0 ##
### 预防xss注入 ###
* thymeleaf模板
    +   标签th:text标签能够对内容进行转义，因此本身是能够预防xss注入的（富文本除外）；
    +   如果不希望对内容进行转义则使用标签th:utext
* Jsoup
>   +   从URL，文件或字符串中刮取并解析HTML
>   +   查找和提取数据，使用DOM遍历或CSS选择器
>   +   操纵HTML元素，属性和文本
>   +   根据安全的白名单清理用户提交的内容，以防止XSS攻击
>   +   输出整洁的HTML        