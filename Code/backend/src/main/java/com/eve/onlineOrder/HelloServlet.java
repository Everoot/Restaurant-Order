package com.eve.onlineOrder;

import java.io.*;

import com.eve.onlineOrder.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // Read customer information from request body
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = objectMapper.readValue(request.getInputStream(), Customer.class);

        System.out.println(customer.getEmail());
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastName());
        // Return status = ok as response body
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEnabled(true);

        response.getWriter().print(mapper.writeValueAsString(customer));
    }

    public void destroy() {
    }
}