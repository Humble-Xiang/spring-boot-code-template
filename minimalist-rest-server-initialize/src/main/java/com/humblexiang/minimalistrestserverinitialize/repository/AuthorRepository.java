package com.humblexiang.minimalistrestserverinitialize.repository;

import com.humblexiang.minimalistrestserverinitialize.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Humble.X
 * @since 2022/8/31
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(nativeQuery = true, value = "select author_id, count(*) num from author a left join book b on a.id = b.author_id group by author_id")
    List<Map<String, Object>> countBookGroupByAuthor();

}
