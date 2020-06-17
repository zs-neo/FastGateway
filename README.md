# FastGateway网关
[GITHUB](https://github.com/zs-neo/FastGateway)
### 网关定位

- 支持http调用（未实现）和rpc调用（已实现）
  - 请求到来时执行：限流-黑白名单验证-签名验证解密body-通过code获取请求的api信息
    - 如果请求的是内部http接口（无code）执行：请求转发，调用结果加密，返回响应
    - 如果请求的是内部rpc的接口（有code）执行：检查api参数，泛化调用，结果加密，返回响应
- ![image-20200613220111614](https://img-blog.csdnimg.cn/20200614172648422.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjM0MDM2Ng==,size_16,color_FFFFFF,t_70)
### 数据库设计
 - 整体设计
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020061715502993.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjM0MDM2Ng==,size_16,color_FFFFFF,t_70)
 - gw_api表结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617155052643.png)
 - gw_api_param表结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617155144104.png)
 - gw_ip_bwlist表结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617155220339.png)
### 责任链模式 
- 接口限流：使用guava令牌桶实现
- 服务降级，服务熔断：业务方接口不可用的时候或者业务方处理速度变慢，进行熔断降级
- 黑名单：哪些ip不可访问
- 白名单：可以从结合业务背景从不同维度进行白名单验证，比如从商户维度或从业务维度
- 数字签名验证，解码body数据：RSA对称加密解密保证安全性
- 检查api信息，检查api参数：是否存在对应api，并将api参数拼接
- 远程调用对应的接口（zookeeper，dubbo）
- 响应加密：RSA私钥加密
- 返回响应

### 请求示例
- RPC（**已实现**）
  - url：http://www.testhttp.com/loginSystem/user/login
  - body：{“code”:"user-login","username":"tom","password":"123456"}
  - 请求到达网关-限流-黑名单-私钥解密-验签--泛化调用-响应加密-响应
- HTTP（**暂未实现**）

### 快速上手
 - 启动一个zookeeper集群，这里用了三个zk组成zk集群
![在这里插入图片描述](https://img-blog.csdnimg.cn/202006171601170.png)
 - 启动项目
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020061716021415.png) 
   - 这里一共两个项目，一个是网关的核心代码，一个是RealServer项目（用户系统）
     - 启动gateway-core
       - 控制台会打印**公钥信息**，保存一下（每次启动项目都不一样）![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617160556296.png)
     - 启动gateway-realserver
   - 打开front里的前端页面index.html，粘贴公钥信息过来![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617160726630.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjM0MDM2Ng==,size_16,color_FFFFFF,t_70)
   - 点击submit，下面就会生成body信息，复制一下。可以使用postman发送**post请求**，也可以使用test.rest去测试请求。需要**把请求的body信息缓换成刚刚复制的json串**。![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617160902457.png)
 - 得到code=200的响应就说明成功请求并拿到数据了
 - 最后让我们来看下调用日志吧~
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617161413403.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjM0MDM2Ng==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020061716143262.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjM0MDM2Ng==,size_16,color_FFFFFF,t_70) ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200617161455448.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjM0MDM2Ng==,size_16,color_FFFFFF,t_70)