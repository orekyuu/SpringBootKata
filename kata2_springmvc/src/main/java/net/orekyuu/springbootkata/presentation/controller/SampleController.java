package net.orekyuu.springbootkata.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class SampleController {

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/calc")
    public ResponseEntity<String> calc(@RequestParam(defaultValue = "0") int a, @RequestParam int b) {
        int sum = a + b;
        return ResponseEntity.ok(String.valueOf(sum));
    }

    @GetMapping("/sumAll")
    public ResponseEntity<String> sumAll(@RequestParam List<Integer> value) {
        int sum = value.stream().mapToInt(Integer::intValue).sum();
        return ResponseEntity.ok(String.valueOf(sum));
    }

    @PostMapping("/hello")
    public ResponseEntity<String> helloMessage(@RequestParam String name) {
        return ResponseEntity.ok("Hello " + name);
    }

    @PostMapping("/hello/{name}")
    public ResponseEntity<String> helloMessageFromPath(@PathVariable String name) {
        if (name.equals("hello")) {
            throw new InvalidParameterException();
        }
        return ResponseEntity.ok("Hello " + name);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<String> invalidParameterErrorResponse(InvalidParameterException e) {
        return ResponseEntity.badRequest().body("invalid parameter");
    }
}
