package com.rookies4.myspringbootlab;

import com.rookies4.myspringbootlab.entity.Book;
import com.rookies4.myspringbootlab.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;

    @BeforeEach
    void testCreateBook() {
        bookRepository.deleteAll();

        book1 = new Book("9788956746425", "스프링 부트 입문", "홍길동", 30000, LocalDate.of(2025, 5, 7));
        book2 = new Book("9788956746432", "JPA 프로그래밍", "박둘리", 35000, LocalDate.of(2025, 5, 30));

        bookRepository.save(book1);
        bookRepository.save(book2);
    }

    @Test
    void testFindByIsbn() {
        Optional<Book> foundBook = bookRepository.findByIsbn("9788956746425");
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getTitle()).isEqualTo("스프링 부트 입문");
    }

    @Test
    void testFindByAuthor() {
        List<Book> books = bookRepository.findByAuthor("홍길동");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("스프링 부트 입문");
    }

    @Test
    void testUpdateBook() {
        book1.setTitle("스프링 부트 입문 - 개정판");
        book1.setPrice(32000);
        Book updatedBook = bookRepository.save(book1);

        assertThat(updatedBook.getTitle()).isEqualTo("스프링 부트 입문 - 개정판");
        assertThat(updatedBook.getPrice()).isEqualTo(32000);
    }

    @Test
    void testDeleteBook() {
        bookRepository.deleteById(book2.getId());
        Optional<Book> deletedBook = bookRepository.findById(book2.getId());
        assertThat(deletedBook).isEmpty();
    }
}
