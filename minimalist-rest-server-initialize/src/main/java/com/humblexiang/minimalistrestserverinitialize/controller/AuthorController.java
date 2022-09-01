package com.humblexiang.minimalistrestserverinitialize.controller;

import com.humblexiang.minimalistrestserverinitialize.entity.Author;
import com.humblexiang.minimalistrestserverinitialize.repository.AuthorRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Humble.X
 * @since 2022/8/31
 */
@RestController
@RequestMapping("/authors")
public class AuthorController {

    final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Author> findById(@PathVariable("id") Long id) {
        return authorRepository.findById(id);
    }

    @GetMapping("/byPage")
    public Page<Author> findById(Author author, @PageableDefault Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("firstName", ExampleMatcher.GenericPropertyMatcher::contains).withMatcher("lastName", ExampleMatcher.GenericPropertyMatcher::contains);
        Example<Author> exampleQuery = Example.of(author, exampleMatcher);
        return authorRepository.findAll(exampleQuery, pageable);
    }

    @GetMapping("/countBookGroupByAuthor")
    public List<Map<String, Object>> countBookGroupByAuthor() {
        return authorRepository.countBookGroupByAuthor();
    }

    @PostMapping
    public Author add(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping
    public Author update(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        authorRepository.deleteById(id);
    }

}
