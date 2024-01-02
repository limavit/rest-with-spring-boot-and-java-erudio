package br.com.vitor.integrationtests.swagger;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;

import br.com.vitor.TestConfigs;
import br.com.vitor.integrationtests.testcontainers.AbstractIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest{

	@Test
	void shouldDisplaySwaggerUiPage() {
		var content =
			given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when()
					.get()
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();
		assertTrue(content.contains("Swagger UI"));
	}

}
