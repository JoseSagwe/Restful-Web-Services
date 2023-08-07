package com.joseph.rest.webservices.restfulwebservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String greet(){
        return "Hello Developer Joseph";
    }


    @GetMapping("/hello-bean")
    public HelloBean bean(){
        return new HelloBean("Hello Developer") ;
    }


    @GetMapping("/hello-bean/{name}")
    public HelloBean beanPathVariable(@PathVariable String name){
        return new HelloBean("Hello Developer " + name) ;
//        USING STRING FORMAT
//        return new HelloBean(String.format("Hello Developer,   %s" , name)) ;
    }
}
