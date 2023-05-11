package com.devtiro.books.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.devtiro.books.domain.Book;
import com.devtiro.books.services.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/** Integration tests for {@link BookServiceImpl} */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class BookServiceImplIT {

  private final BookServiceImpl underTest;

  @Autowired
  public BookServiceImplIT(BookServiceImpl underTest) {
    this.underTest = underTest;
  }

  @Test
  public void testThatListBooksReturns100Items() {
    final Page<Book> result = underTest.listBooks(PageRequest.of(0, 100));
    assertThat(result.getContent()).hasSize(100);
  }
}
