package com.blibli.controller;

import com.blibli.model.Book;
import com.blibli.services.BookService;
import com.blibli.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/book")
    public String listBook(Model model) {
        model = bookService.showAllListBook(model);
        return "manager/show/book";
    }

    @RequestMapping(value = "/book/", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("book") Book book, Model model) {
        return bookService.saveModal(model, book);

    }

    @RequestMapping(value = "/book/createBook", method = RequestMethod.GET)
    public String tampilFormCreateBook(Model model) {
        model = bookService.manageFormCreateBook(model);
        return "manager/edit/createBook";
    }

    @RequestMapping("/discount")
    public String listBookDiscount(Model model) {
        model = bookService.showAllListBookDiscount(model);
        return "manager/show/discount";
    }

    @RequestMapping("/emptyStok")
    public String listBookEmpty(Model model) {
        model = bookService.showAllListBookEmpty(model);
        return "manager/show/emptyStok";
    }

    @RequestMapping(value = "/book/editBook/{id}", method = RequestMethod.GET)
    public String editDataCategory(@PathVariable int id, Model model) {
        model = bookService.manageEditBook(model, id);
        return "manager/edit/createBook";
    }

    @RequestMapping(value = "/book/search", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute("searchKey") String searchKey) {
        model = bookService.searchBookByKeyword(model, searchKey);
        return "manager/show/book";
    }

    @RequestMapping(value = "/book/searchDiscount", method = RequestMethod.POST)
    public String searchDiscount(Model model, @ModelAttribute("searchKey") String searchKey) {
        model = bookService.searchBookByKeywordDiscount(model, searchKey);
        return "manager/show/book";
    }

    @RequestMapping(value = "/book/searchEmptyBook", method = RequestMethod.POST)
    public String searchEmptyBook(Model model, @ModelAttribute("searchKey") String searchKey) {
        model = bookService.searchBookByKeywordEmptyStock(model, searchKey);
        return "manager/show/book";
    }

    @RequestMapping(value = "/book/softDelete/{id}", method = RequestMethod.GET)
    public String softDeleteBook(@PathVariable Integer id) {
        bookService.softDelete(id);
        return "redirect:/book";
    }
}
