package com.sprk.first;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {

//    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String sayHello(){
        return "<h1>Hello World From SPRK</h1>";
    }

    @GetMapping("/sum")
    public int addition(){
        return 5+4;
    }

    @GetMapping("/fruits")
    public List<String> myFruits(){
        return Arrays.asList("Banana","Orange","Mango","Litchi","Apple","Apple","Banana");

    }

    // @RequestParam
    @GetMapping("/sumReq")
    public StringBuilder additionOfNumbers(@RequestParam int n1, @RequestParam(defaultValue = "10") int n2){
        int sum = n1+n2;

        String result =  String.format("%d + %d = %d", n1, n2, sum);
        StringBuilder builder = new StringBuilder();
        builder.append("The addition of ");
        builder.append(result);

        return builder;
    }

    // @PathVariable
    @GetMapping("/sum/{n1}/{n2}")
    public String additionOf2Numbers(@PathVariable int n1, @PathVariable int n2){

        return String.format("The addition with path variable is %d and %d is %d", n1, n2, (n1+n2));
    }
}
