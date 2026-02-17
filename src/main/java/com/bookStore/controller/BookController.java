package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

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
    public String bookRegister() {
        return "bookRegister";
    }

    @GetMapping("/availableBooks")
    public ModelAndView getAllBooks() {
        List<Book> list = bookService.getAllBooks();
        return new ModelAndView("bookList", "books", list);
    }

    @GetMapping("/myBooks")
    public ModelAndView getMyBooks() {
        List<MyBookList> list = myBookService.getAllMyBooks();
        return new ModelAndView("myBooks", "myBooks", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/availableBooks";
    }

    // add book to my list
    @GetMapping("/myList/{id}")
    public String addToMyList(@PathVariable int id) {

        Book book = bookService.getBookById(id);

        MyBookList myBook = new MyBookList(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getPrice()
        );

        myBookService.saveMyBooks(myBook);
        return "redirect:/myBooks";
    }
    
    @GetMapping("/editBook/{id}")
    public ModelAndView editBook(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        return new ModelAndView("editBook", "book", book);
    }
    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book) {
        bookService.save(book); // save = update if ID exists
        return "redirect:/availableBooks";
    }
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteById(id);
        return "redirect:/availableBooks";
    }

 // Remove book from My Book List
    @GetMapping("/removeMyBook/{id}")
    public String removeMyBook(@PathVariable int id) {
        myBookService.deleteById(id);
        return "redirect:/myBooks";
    }


}
