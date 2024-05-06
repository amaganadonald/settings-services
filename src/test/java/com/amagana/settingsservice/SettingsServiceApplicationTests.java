package com.amagana.settingsservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
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

import com.amagana.settingsservice.dto.AddressRequestDTO;
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
	@Order(1)
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
	@Order(2)
	void getAllCategoryTest() throws Exception {
		mockMvc.perform(get("/api/v1/category").accept("application/json").contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.results[0].id").value("1"));
	}
	
	@Test
	@Order(3)
	void getCategoryByIdTest() throws Exception {
		mockMvc.perform(get("/api/v1/category/"+1).accept("application/json").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.results.id").value("1"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.results.categoryName").value("Programming"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.results.categoryDescription").value("Java"));
	}
	
	@Test
	@Order(4)
	void updateCategoryTest() throws Exception {
		CategoryRequestDTO categoryRequestDTO = CategoryRequestDTO.builder()
				.categoryDescription("Java")
				.categoryName("Programming")
				.createdat(LocalDateTime.now())
				.createdby("Donald")
				.lastupdatedate(LocalDateTime.now())
				.lastupdatedby("Donald")
				.build();
		mockMvc.perform(put("/api/v1/category/"+1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(categoryRequestDTO))
				.accept("application/json"))
		        .andDo(print())
		        .andExpect(status().isAccepted())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.results.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.results.id").value("1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.results.categoryName").value("Programming"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.results.categoryDescription").value("Java"));
	}
	
	
	@Test
	void addNewAddressTest() throws Exception{
		AddressRequestDTO addressRequestDTO = AddressRequestDTO.builder()
				.addressCity("Huberty")
				.addressName("Jean Pièrre")
				.addressEmail("huberty@lux.lu")
				.addressNumber(37)
				.createdat(LocalDateTime.now())
				.createdby("Noe")
				.addressPhone("6894555")
				.addressProfessionalPhone("698744")
				.build();
		mockMvc.perform(post("/api/v1/address")
				.accept("application/json")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(addressRequestDTO)))
		        .andExpect(status().isCreated())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressCity").value("Huberty"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.results.id").exists());
	}
	
	@Test
	void getAllAddressTest() throws Exception {
		mockMvc.perform(get("/api/v1/address").accept("application/json").contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.results[0].id").value("1"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].addressCity").value("Huberty"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].addressName").value("Jean Pièrre"));
	}
	
	@Test
	void findAddressByIdTest() throws Exception {
		mockMvc.perform(get("/api/v1/address/"+1).accept("application/json").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isFound())
        .andDo(print())
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.results.id").value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressCity").value("Huberty"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressName").value("Jean Pièrre"));
	}
	
}
