package com.example.Librarymanagementsystem.services;

import com.example.Librarymanagementsystem.entity.Author;

public interface AuthorService {
    public String addAuthor(Author author);
    public String deleteAuthorById(int id);
    public String updateAuthorById(String name,int id);

}
