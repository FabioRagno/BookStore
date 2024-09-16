package com.fabioragno.bookstore.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookStoreController {

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }
}
