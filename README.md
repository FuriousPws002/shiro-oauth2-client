# shiro-oauth2-client
当客户端权限框架为shiro时，可向OAuth2认证服务器获取用户信息，来进行用户认证  
认证服务器可参考[oauth2-authorization-server](https://github.com/FuriousPws002/spring-security-oauth2-example/tree/master/oauth2-authorization-server)   
当启动认证服务器和当前客户端的时候，访问如下链接，即可实现客户端的免登录认证   
 ``` 
http://localhost:8080/auth-server/oauth/authorize?client_id=testclient&response_type=code&redirect_uri=http://localhost:8082/callback?client_name=FuriousClient
 ```
[update]  
添加sso拦截器后，直接访问客户端主页即可实现免登录（当OAuth服务器端已经认证，没有认证会跳到认证服务器认证）  
 ``` 
http://localhost:8082
 ```