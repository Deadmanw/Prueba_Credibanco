package banck.inc.prueba;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PruebaApplicationTests {

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Value("${spring.datasource.username}")
	private String dataSourceUsername;

	@Value("${spring.datasource.password}")
	private String dataSourcePassword;

	// Inyectar el DataSource de Spring Boot
	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
		assertNotNull(dataSourceUrl, "dataSourceUrl should not be null");
		assertNotNull(dataSourceUsername, "dataSourceUsername should not be null");
		assertNotNull(dataSourcePassword, "dataSourcePassword should not be null");

		try (Connection connection = dataSource.getConnection()) {
			assertTrue(connection.isValid(1), "Connection to the database should be valid");
		} catch (SQLException e) {
			fail("Failed to connect to the database: " + e.getMessage());
		}
	}
}
