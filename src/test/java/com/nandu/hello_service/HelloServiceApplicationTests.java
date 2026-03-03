package com.nandu.hello_service;

import com.nandu.hello_service.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void helloEndpointReturnsMessage() throws Exception {
		mockMvc.perform(get("/api/hello"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is("Hello from Jenkins Pipeline!")))
				.andExpect(jsonPath("$.status",  is("UP")));
	}

	@Test
	void healthEndpointReturnsUp() throws Exception {
		mockMvc.perform(get("/api/health"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is("UP")));
	}

	@Test
	void greetEndpointReturnsPersonalisedGreeting() throws Exception {
		mockMvc.perform(post("/api/greet")
						.contentType("application/json")
						.content(""" 
								{"name": "Jenkins"}
								"""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.greeting", is("Hello, Jenkins!")));
	}
}