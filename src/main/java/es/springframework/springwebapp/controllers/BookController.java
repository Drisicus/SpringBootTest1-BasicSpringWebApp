package es.springframework.springwebapp.controllers;

import es.springframework.springwebapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){
        // add to the model, with the attribute books, the list with all the books from the repository
        model.addAttribute("books", bookRepository.findAll());
        // return the view template name
        return "books";
    }

}
