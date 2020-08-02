package es.springframework.springwebapp.bootstrap;

import es.springframework.springwebapp.model.Author;
import es.springframework.springwebapp.model.Book;
import es.springframework.springwebapp.model.Publisher;
import es.springframework.springwebapp.repositories.AuthorRepository;
import es.springframework.springwebapp.repositories.BookRepository;
import es.springframework.springwebapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        // Shared publisher
        Publisher publisher = new Publisher("AllPublisher", "PublisherAddress");
        publisherRepository.save(publisher);

        // Create author and book
        Author finn = new Author("Finn", "Sawyer");
        Book finnBestSeller = new Book("The Adventures of Finn", "12345", publisher);
        finn.getBooks().add(finnBestSeller);
        finnBestSeller.getAuthors().add(finn);

        // Store in database through repository
        authorRepository.save(finn);
        bookRepository.save(finnBestSeller);

        Author bob = new Author("Bob", "Hemingway");
        Book bobBestSeller = new Book("The Garden of Bob", "54321", publisher);
        bob.getBooks().add(bobBestSeller);

        authorRepository.save(bob);
        bookRepository.save(bobBestSeller);
    }
}
