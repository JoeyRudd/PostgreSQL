package com.creation.database.dao;

import com.creation.database.dao.impl.AuthorDaoImpl;
import com.creation.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    // create a mock jdbc
    @Mock
    private JdbcTemplate jdbcTemplate;

    // a mock of jdbc is injected into author dao instance under test
    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        // build a sample author for test
        Author author = Author.builder()
                .id(1l)
                .name("Abigail Rose")
                .age(80)
                .build();

        // call method under test, should insert author to database
        underTest.create(author);

        // veridy correct SQL insert was executed with proper values
        verify(jdbcTemplate).update(eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("Abigail Rose"), eq(80));
    }

}
