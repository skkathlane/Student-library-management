package com.example.Librarymanagementsystem.controllers;


import com.example.Librarymanagementsystem.entity.Author;
import com.example.Librarymanagementsystem.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author){
        return authorService.addAuthor((author));
    }
    //    delete an author by id
//    update an author by id
//    find author by id
    //find all author
    @DeleteMapping("/delete")
    public String deleteAuthorById(@RequestParam("id") int id){
        return authorService.deleteAuthorById(id);
    }
    @PutMapping("/update")
    public String updateAuthorById(@RequestParam() String name,@RequestParam() int id){
        return authorService.updateAuthorById(name,id);
    }
}
