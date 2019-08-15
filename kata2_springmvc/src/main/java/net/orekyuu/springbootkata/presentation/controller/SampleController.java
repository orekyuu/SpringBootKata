package net.orekyuu.springbootkata.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.InvalidParameterException;
import java.util.List;

@Controller
@RequestMapping
public class SampleController {

    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World");
    }

    public ResponseEntity<String> calc(int a, int b) {
        int sum = a + b;
        return ResponseEntity.ok(String.valueOf(sum));
    }

    public ResponseEntity<String> sumAll(List<Integer> value) {
        int sum = value.stream().mapToInt(Integer::intValue).sum();
        return ResponseEntity.ok(String.valueOf(sum));
    }

    public ResponseEntity<String> helloMessage(String name) {
        return null;
    }

    public ResponseEntity<String> helloMessageFromPath(String name) {
        if (name.equals("hello")) {
            throw new InvalidParameterException();
        }
        return null;
    }

    public ResponseEntity<String> invalidParameterErrorResponse(InvalidParameterException e) {
        return ResponseEntity.badRequest().body("invalid parameter");
    }
}
