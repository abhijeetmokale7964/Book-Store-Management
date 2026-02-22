package com.bookStore.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.entity.User;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MyBookListService myBookService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/bookRegister")
    public String bookRegister(HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/login";
        return "bookRegister";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/availableBooks";
    }

    @GetMapping("/availableBooks")
    public ModelAndView getAllBooks() {
        return new ModelAndView("bookList", "books", bookService.getAllBooks());
    }

    @GetMapping("/editBook/{id}")
    public ModelAndView editBook(@PathVariable int id) {
        return new ModelAndView("editBook", "book", bookService.getBookById(id));
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/availableBooks";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteById(id);
        return "redirect:/availableBooks";
    }

    @GetMapping("/myList/{id}")
    public String addToMyList(@PathVariable int id, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Book book = bookService.getBookById(id);

        MyBookList myBook = new MyBookList();
        myBook.setName(book.getName());
        myBook.setAuthor(book.getAuthor());
        myBook.setPrice(book.getPrice());

        myBook.setUser(user);   // 🔥 THIS WAS MISSING

        myBookService.saveMyBooks(myBook);

        return "redirect:/myBooks";
    }


    @GetMapping("/myBooks")
    public ModelAndView myBooks(HttpSession session) {

        // Get logged-in user from session
        User user = (User) session.getAttribute("user");

        // If user not logged in, redirect to login
        if (user == null) {
            return new ModelAndView("redirect:/login");
        }

        // Fetch books only for this user
        List<MyBookList> myBooks = myBookService.getByUser(user);

        return new ModelAndView("myBooks", "myBooks", myBooks);
    }

    @GetMapping("/removeMyBook/{id}")
    public String removeMyBook(@PathVariable int id) {
        myBookService.deleteById(id);
        return "redirect:/myBooks";
    }
    
    
}
