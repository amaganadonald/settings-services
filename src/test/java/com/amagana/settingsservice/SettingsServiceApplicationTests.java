package com.amagana.settingsservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;

import com.amagana.settingsservice.dto.CategoryRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class SettingsServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres");

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	}
	
	@BeforeAll
	static void beforeAll() {
		postgreSQLContainer.start();
	}
	
	@AfterAll
	static void afterAll() {
		postgreSQLContainer.stop();
	}
	
	@Test
	void addNewCategoryTest() throws Exception {
		CategoryRequestDTO categoryRequestDTO = CategoryRequestDTO.builder()
				.categoryDescription("Roman en livre")
				.categoryName("Roman")
				.createdat(LocalDateTime.now())
				.createdby("Donald")
				.lastupdatedate(LocalDateTime.now())
				.lastupdatedby("Donald")
				.build();
		mockMvc.perform(post("/api/v1/category")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(categoryRequestDTO))
				.accept("application/json"))
		        .andExpect(status().isCreated())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.results.id").exists());
	}
	
	@Test
	void getAllCategoryTest() throws Exception {
		mockMvc.perform(get("/api/v1/category").accept("application/json").contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.results[0].id").value("1"));
	}
	
}
