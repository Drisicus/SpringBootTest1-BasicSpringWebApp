package es.springframework.springwebapp.bootstrap;

import es.springframework.springwebapp.model.Author;
import es.springframework.springwebapp.model.Book;
import es.springframework.springwebapp.repositories.AuthorRepository;
import es.springframework.springwebapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        // Create author and book
        Author finn = new Author("Finn", "Sawyer");
        Book finnBestSeller = new Book("The Adventures of Finn", "12345", "SawyerPublishers");
        finn.getBooks().add(finnBestSeller);
        finnBestSeller.getAuthors().add(finn);

        // Store in database through repository
        authorRepository.save(finn);
        bookRepository.save(finnBestSeller);

        Author bob = new Author("Bob", "Hemingway");
        Book bobBestSeller = new Book("The Garden of Bob", "54321", "HemingwayPublishers");
        bob.getBooks().add(bobBestSeller);

        authorRepository.save(bob);
        bookRepository.save(bobBestSeller);

    }
}
