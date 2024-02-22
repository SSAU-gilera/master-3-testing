package com.example;

import com.example.Calculator.CalculatorRepository;
import com.example.Calculator.OperationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectWriter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.flywaydb.core.Flyway;

@SpringBootTest
@ComponentScan(basePackages = "com.example")
@AutoConfigureMockMvc
class Lab2ApplicationTests extends AuditVizualizationBaseTest{
	@Autowired
	CalculatorRepository calculatorRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testCalculateDecimal() throws Exception {
		mockMvc.perform(get("/calculate/10/decimal/20/decimal/add"))
				.andExpect(status().isOk())
				.andExpect(content().string("Result: 30"))
				.andDo(print());

		mockMvc.perform(get("/calculate/10/decimal/20/decimal/multiply"))
				.andExpect(status().isOk())
				.andExpect(content().string("Result: 200"))
				.andDo(print());

		mockMvc.perform(get("/calculate/20/decimal/10/decimal/divide"))
				.andExpect(status().isOk())
				.andExpect(content().string("Result: 2"))
				.andDo(print());

		mockMvc.perform(get("/calculate/20/decimal/10/decimal/subtract"))
				.andExpect(status().isOk())
				.andExpect(content().string("Result: 10"))
				.andDo(print());
	}

	@Test
	void testCalculateBinary() throws Exception {
		mockMvc.perform(get("/calculate/47/octal/13/octal/add"))
				.andExpect(status().isOk())
				.andExpect(content().string("Result: 62"))
				.andDo(print());

		mockMvc.perform(get("/calculate/56/octal/12/octal/multiply"))
				.andExpect(status().isOk())
				.andExpect(content().string("Result: 714"))
				.andDo(print());

		mockMvc.perform(get("/calculate/314/octal/23/octal/divide"))
				.andExpect(status().isOk())
				.andExpect(content().string("Result: 12"))
				.andDo(print());

		mockMvc.perform(get("/calculate/75/octal/24/octal/subtract"))
				.andExpect(status().isOk())
				.andExpect(content().string("Result: 51"))
				.andDo(print());
	}

	@Test
	void testFindAllBetweenDates() throws Exception {
		Flyway flyway = Flyway.configure()
				.dataSource(POSTGRE_SQL_CONTAINER.getJdbcUrl(), POSTGRE_SQL_CONTAINER.getUsername(), POSTGRE_SQL_CONTAINER.getPassword())
				.locations("/db.migration")
				.load();
		flyway.repair();
		flyway.migrate();

		List<OperationEntity> operationEntities = calculatorRepository.findAllByDateBetweenAndOperationTypeAndFirstNumSystemAndSecondNumSystem(
				Date.valueOf("2023-10-01"), Date.valueOf("2023-11-01"), "add", "decimal", "decimal");

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(operationEntities);

		String expectedJson = Files.readString(Paths.get("src/test/resources/expected.json"));
		assertEquals(expectedJson, json);
	}
}





