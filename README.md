# FastGateway网关

### 网关定位

- 支持http调用和rpc调用
  - 请求到来时执行：限流-黑白名单验证-签名验证解密body-通过code获取请求的api信息
    - 如果请求的是内部http接口（无code）执行：请求转发，调用结果加密，返回响应
    - 如果请求的是内部rpc的接口（有code）执行：检查api参数，远程调用，结果加密，返回响应
- ![image-20200613220111614](https://img-blog.csdnimg.cn/20200614172648422.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjM0MDM2Ng==,size_16,color_FFFFFF,t_70)

### 数据库设计

- ![image-20200613220917294](https://img-blog.csdnimg.cn/20200614172704652.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjM0MDM2Ng==,size_16,color_FFFFFF,t_70)

### 责任链模式 

- 接口限流
- 服务降级，服务熔断
- 黑名单
- 白名单
- 数字签名验证，解码body数据
- 检查api信息
- 检查api参数
- 远程调用对应的接口
- 调用结果加密
- 返回响应

### 请求示例

- HTTP
  - url：http://www.testhttp.com/loginSystem/user/login?sign=xxx
  - body：{"username":"tom","password":"123456"}
  - 请求到达网关-限流-黑名单-验签-私钥解密-没有code说明是http请求-请求转发-调用结果加密-响应
- RPC
  - url：http://www.testhttp.com/loginSystem/user/login?sign=xxx
  - body：{“code”:"user-login","username":"tom","password":"123456"}
  - 请求到达网关-限流-黑名单-验签-私钥解密-有code说明是RPC请求-泛化调用-调用结果加密-响应