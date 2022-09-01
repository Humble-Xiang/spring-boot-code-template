package com.humblexiang.minimalistrestserverinitialize.controller;

import com.humblexiang.minimalistrestserverinitialize.entity.Book;
import com.humblexiang.minimalistrestserverinitialize.repository.BookRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Humble.X
 * @since 2022/9/1
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Book> findById(@PathVariable("id") Long id) {
        return bookRepository.findById(id);
    }

    @GetMapping("/byPage")
    public Page<Book> findById(Book book, @PageableDefault Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains);
        Example<Book> exampleQuery = Example.of(book, exampleMatcher);
        return bookRepository.findAll(exampleQuery, pageable);
    }

    @PostMapping
    public Book add(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
    }
}
