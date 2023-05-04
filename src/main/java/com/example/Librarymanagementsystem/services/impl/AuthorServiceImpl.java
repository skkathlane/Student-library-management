package com.example.Librarymanagementsystem.services.impl;

import com.example.Librarymanagementsystem.entity.Author;
import com.example.Librarymanagementsystem.repository.AuthorRepository;
import com.example.Librarymanagementsystem.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "author added";
    }

    @Override
    public String deleteAuthorById(int id) {
        authorRepository.deleteById(id);
        return "author has been deleted";
    }

    @Override
    public String updateAuthorById(String name, int id) {
        Author author=authorRepository.findById(id).get();
        author.setName(name);
        return "name has been updated";
    }
}
