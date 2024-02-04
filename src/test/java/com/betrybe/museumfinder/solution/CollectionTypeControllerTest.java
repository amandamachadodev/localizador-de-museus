package com.betrybe.museumfinder.solution;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeControllerTest {
  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Teste CollectionTypeController")
  public void testCollectionTypeTest() throws Exception {
    String[] types = new String[]{"hist", "imag"};

      mockMvc.perform(get("/collections/count/hist,imag"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.count").value(492))
          .andExpect(jsonPath("$.collectionTypes").isArray());
  }
}
