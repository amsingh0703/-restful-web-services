package com.rest_webservices.RestFul_web_services;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RestFulWebServicesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Context loads successfully")
	void contextLoads() {
	}

	@Test
	@DisplayName("Unauthenticated request to protected endpoint returns 401 Unauthorized")
	void unauthenticatedRequest_ReturnsUnauthorized() throws Exception {
		mockMvc.perform(get("/users"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@DisplayName("Permitted endpoints like Swagger API docs are accessible without authentication")
	void openApiDocs_AccessibleWithoutAuth() throws Exception {
		mockMvc.perform(get("/v3/api-docs"))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("GET /hello-world returns greeting")
	void helloWorld_ReturnsGreeting() throws Exception {
		mockMvc.perform(get("/hello-world").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", is("Hello World!!")));
	}

	@Test
	@DisplayName("GET /hello-world-bean returns JSON bean")
	void helloWorldBean_ReturnsJson() throws Exception {
		mockMvc.perform(get("/hello-world-bean").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is("Hello World")));
	}

	@Test
	@DisplayName("GET /hello-world/PathVariable/{name} returns path personalized message")
	void helloWorldPathVariable_ReturnsPersonalizedMessage() throws Exception {
		mockMvc.perform(get("/hello-world/PathVariable/Amit").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is("Hello World, Amit")));
	}

	@Test
	@DisplayName("GET /hello-world-Internationalized returns i18n message")
	void helloWorldInternationalized_ReturnsMessage() throws Exception {
		mockMvc.perform(get("/hello-world-Internationalized")
				.header("Accept-Language", "nl")
				.with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", is("Goedemorgen")));
	}

	@Test
	@DisplayName("GET /users returns in-memory users list")
	void getAllUsers_ReturnsList() throws Exception {
		mockMvc.perform(get("/users").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()));
	}

	@Test
	@DisplayName("GET /users/{id} returns user with HATEOAS link")
	void getUserById_ReturnsUserWithHateoas() throws Exception {
		mockMvc.perform(get("/users/1").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Adam")))
				.andExpect(jsonPath("$._links.all-users.href", containsString("/users")));
	}

	@Test
	@DisplayName("GET /users/{id} with invalid id returns 404 UserNotFoundException")
	void getUserById_NotFound() throws Exception {
		mockMvc.perform(get("/users/9999").with(httpBasic("amit", "1234")))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", containsString("9999")));
	}

	@Test
	@DisplayName("POST /users with valid payload creates user and returns 201 Created")
	void createUser_Success() throws Exception {
		String newUserJson = """
				{
				  "name": "Sarah",
				  "birthDate": "1998-05-15"
				}
				""";

		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(newUserJson)
				.with(httpBasic("amit", "1234")))
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
	}

	@Test
	@DisplayName("POST /users with invalid payload (short name) returns 400 Bad Request")
	void createUser_ValidationError() throws Exception {
		String invalidUserJson = """
				{
				  "name": "A",
				  "birthDate": "1998-05-15"
				}
				""";

		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(invalidUserJson)
				.with(httpBasic("amit", "1234")))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", containsString("Total Errors: 1")));
	}

	@Test
	@DisplayName("GET /jpa/users returns users seeded in data.sql")
	void getJpaUsers_ReturnsSeededUsers() throws Exception {
		mockMvc.perform(get("/jpa/users").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()));
	}

	@Test
	@DisplayName("GET /jpa/users/{id}/posts returns user posts")
	void getJpaUserPosts_ReturnsPosts() throws Exception {
		mockMvc.perform(get("/jpa/users/100/posts").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	@DisplayName("GET /jpa/users/{id}/posts/{postId} returns matching post")
	void getJpaUserPostById_ReturnsPost() throws Exception {
		mockMvc.perform(get("/jpa/users/100/posts/20001").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(20001)))
				.andExpect(jsonPath("$.description", is("I want to learn AWS")));
	}

	@Test
	@DisplayName("GET /jpa/users/{id}/posts/{postId} with mismatched user returns 404")
	void getJpaUserPostById_MismatchedUser_ReturnsNotFound() throws Exception {
		// Post 20002 belongs to user 101, not user 100
		mockMvc.perform(get("/jpa/users/100/posts/20002").with(httpBasic("amit", "1234")))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", containsString("does not belong to user")));
	}

	@Test
	@DisplayName("Static filtering /filtering excludes field2")
	void filtering_StaticExcludesField2() throws Exception {
		mockMvc.perform(get("/filtering").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.field1", is("Value1")))
				.andExpect(jsonPath("$.field2").doesNotExist())
				.andExpect(jsonPath("$.field3", is("Value3")));
	}

	@Test
	@DisplayName("Versioning: URI path v1 and v2")
	void versioning_UriPath() throws Exception {
		mockMvc.perform(get("/v1/Person").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Bob Charli")));

		mockMvc.perform(get("/v2/Person").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name.firstName", is("Bob")))
				.andExpect(jsonPath("$.name.lastName", is("Charli")));
	}

	@Test
	@DisplayName("Versioning: Request parameter version=1")
	void versioning_RequestParam() throws Exception {
		mockMvc.perform(get("/Person?version=1").with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Bob Charli")));
	}

	@Test
	@DisplayName("Versioning: Request header x-api-version=1")
	void versioning_Header() throws Exception {
		mockMvc.perform(get("/Person/header")
				.header("x-api-version", "1")
				.with(httpBasic("amit", "1234")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Bob Charli")));
	}
}
