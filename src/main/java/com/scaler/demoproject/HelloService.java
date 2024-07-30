package com.scaler.demoproject;

import org.springframework.web.bind.annotation.*;

@RestController//will tell this class contains service and there are apis inside it
public class HelloService {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello Suresh";
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String modifyUser(@PathVariable("id") String id) {
        return "Hello World " + id;
    }
}
