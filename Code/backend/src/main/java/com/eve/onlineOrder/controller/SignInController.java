package com.eve.onlineOrder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// Controller 层用于接收请求，调用 Service 层的方法，将数据返回给前端页面。
@Controller // @Controller 用于标注控制层组件，即 Controller 组件。
public class SignInController {

    // ObjectMapper 用于将 Java 对象转换为 JSON 字符串。
    private final ObjectMapper objectMapper = new ObjectMapper();

    // we only process the failed login request here
    // if login successfully, it will automatically redirect to the home page

    // @RequestMapping 用于映射请求，即指定请求的 URL。
    @RequestMapping("/login") // value 属性用于指定请求的 URL。例如，/login
    // 表示请求的 URL 为 http://localhost:8080/login。
    public void login(@RequestParam(value = "error") String error, HttpServletResponse response) throws IOException {
        // @RequestParam 用于获取请求参数。
        // value 属性用于指定请求参数的名称。例如，@RequestParam(value = "error")
        // 表示获取名为 error 的请求参数。
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        // HttpStatus 是一个枚举类，包含了所有的响应状态码。
        // HttpStatus.UNAUTHORIZED 表示响应状态码为 401。
        // response.setStatus(HttpStatus.UNAUTHORIZED.value()) 表示响应状态码为 401。
        Map<String, Object> data = new HashMap<>(); // 创建一个 Map 对象。
        data.put("message", "bad credentials");
        // put 方法用于向 Map 对象中添加数据。
        // 例如，data.put("message", "bad credentials") 表示向 Map 对象中添加名为 message，值为 bad credentials 的数据。
        // bad credentials 表示用户名或密码错误。
        response.getOutputStream()
                .println(objectMapper.writeValueAsString(data));
        // getOutputStream 方法用于获取输出流。
        // objectMapper.writeValueAsString(data) 用于将 Map 对象转换为 JSON 字符串。
        // 例如，当用户名或密码错误时，需要将错误信息返回给前端页面。
    }

}
