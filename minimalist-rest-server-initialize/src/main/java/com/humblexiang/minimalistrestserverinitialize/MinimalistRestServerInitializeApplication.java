package com.humblexiang.minimalistrestserverinitialize;

import com.humblexiang.minimalistrestserverinitialize.entity.Author;
import com.humblexiang.minimalistrestserverinitialize.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Stream;

/**
 * @author Humble.X
 */
@Slf4j
@SpringBootApplication
public class MinimalistRestServerInitializeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinimalistRestServerInitializeApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            RestTemplate restTemplate = new RestTemplate();
            String authorRequestMapping = "http://localhost:8080/authors";
            String bookRequestMapping = "http://localhost:8080/books";
            // 新增
            Stream.iterate(1, i -> i + 1).limit(3).forEach(i -> {
                Author author = restTemplate.postForEntity(authorRequestMapping, Author.builder().firstName("author" + i).lastName(i.toString()).build(), Author.class).getBody();
                log.info("author add : {}", author);
                Stream.iterate(1, j -> j + 1).limit(10).forEach(j -> {
                    log.info("book add : {}", restTemplate.postForEntity(bookRequestMapping, Book.builder().author(author).name("author" + i + " book" + j).build(), Book.class));
                });
            });
            // 查全部
            log.info("author findAll : {}", restTemplate.getForEntity(authorRequestMapping, String.class));
            // 分页带条件查
            log.info("book byPage : {}", restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(bookRequestMapping + "/byPage").queryParam("name", "author1").queryParam("page", 0).queryParam("size", 5).build().toString(), String.class));
            // 统计查询
            log.info("author countBookGroupByAuthor : {}", restTemplate.getForEntity(authorRequestMapping + "/countBookGroupByAuthor", String.class));
            // 根据 id 查
            Author author = restTemplate.getForEntity(authorRequestMapping + "/1", Author.class).getBody();
            log.info("findById : {}", author);
            // 修改
            if (author != null) {
                author.setLastName("bala");
                restTemplate.put(authorRequestMapping, author);
                log.info("update : {}", restTemplate.getForEntity(authorRequestMapping + "/1", Author.class).getBody());
            }
            // 删除
            restTemplate.delete(authorRequestMapping + "/2");
            log.info("delete : {}", restTemplate.getForEntity(authorRequestMapping + "/2", Author.class).getBody());
        };
    }

}
