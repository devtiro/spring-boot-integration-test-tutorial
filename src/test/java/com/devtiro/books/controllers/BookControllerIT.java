package com.devtiro.books.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/** Integration tests for {@link BookController} */
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIT {

  private final MockMvc mockMvc;

  @Autowired
  public BookControllerIT(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  public void testListBooksReturnsCorrectTotalElements() throws Exception {

    mockMvc
        .perform(MockMvcRequestBuilders.get("/books"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(100));
  }

  @Test
  public void testListBooksSupportsPageSize() throws Exception {

    mockMvc
        .perform(MockMvcRequestBuilders.get("/books?size=2"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)));
  }
}
