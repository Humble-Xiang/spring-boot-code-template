package com.humblexiang.minimalistrestserverinitialize.repository;

import com.humblexiang.minimalistrestserverinitialize.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Humble.X
 * @since 2022/9/1
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
