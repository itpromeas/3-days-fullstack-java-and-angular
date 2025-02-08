package com.ange.ecommerce_api.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping
    public String getAll()  {
        // todo: implement this method
        return "Not yet implemented";
    }

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable("{id}") int id)  {
        // todo: implement this method
        return "Not yet implemented";
    }

    @PostMapping("/create")
    public String create()  {
        // todo: implement this method
        return "Not yet implemented";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable("{id}") int id)  {
        // todo: implement this method
        return "Not yet implemented";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("{id}") int id)  {
        // todo: implement this method
        return "Not yet implemented";
    }
}
