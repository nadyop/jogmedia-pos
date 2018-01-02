package com.blibli.services;

import com.blibli.dao_api.BookInterface;
import com.blibli.dao_api.CategoryDaoInterface;
import com.blibli.model.Book;
import com.blibli.model.Category;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
    @Autowired
    BookInterface bookDaoInterface;
    @Autowired
    CategoryDaoInterface categoryDaoInterface;

    public Model manageFormCreateBook(Model model) {
        model.addAttribute("categories", categoryDaoInterface.getAllActive());
        model.addAttribute("book", new Book());
        return model;
    }

    public Model manageEditBook(Model model, int idBook) {
        model.addAttribute("categories", categoryDaoInterface.getAllActive());
        model.addAttribute("book", bookDaoInterface.getIdBook(idBook));
        return model;
    }

    public String saveModal(Model model, Book book) {
        bookDaoInterface.saveBook(book);
        return "redirect:/book";
    }

    public void softDelete(int id) {
        bookDaoInterface.softDeleteBook(id);
    }

    public Model showAllListBook(Model model) {
        model.addAttribute("book", bookDaoInterface.getAllBooks());
        model.addAttribute("categories", categoryDaoInterface.getAllCategory());
        return model;
    }

    public Model showAllListBookDiscount(Model model) {
        model.addAttribute("book", bookDaoInterface.getAllBooksDiscount());
        model.addAttribute("categories", categoryDaoInterface.getAllCategory());
        return model;
    }

    public Model showAllListBookEmpty(Model model) {
        model.addAttribute("book", bookDaoInterface.getAllBooksEmty());
        model.addAttribute("categories", categoryDaoInterface.getAllCategory());
        return model;
    }

    public Model searchBookByKeyword(Model model, String searchKey) {
        model.addAttribute("categories", categoryDaoInterface.getAllActive());
        model.addAttribute("book", bookDaoInterface.search(searchKey));
        return model;
    }

    public Model searchBookByKeywordDiscount(Model model, String searchKey) {
        model.addAttribute("categories", categoryDaoInterface.getAllActive());
        model.addAttribute("book", bookDaoInterface.searchDiscount(searchKey));
        return model;
    }

    public Model searchBookByKeywordEmptyStock(Model model, String searchKey) {
        model.addAttribute("categories", categoryDaoInterface.getAllActive());
        model.addAttribute("book", bookDaoInterface.searchEmptyBook(searchKey));
        return model;
    }

}