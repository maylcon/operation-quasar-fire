package com.operation.quasar.fire.coordinates;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.operation.quasar.fire.coordinates.dtos.PositionDto;

@AutoConfigureMockMvc
@SpringBootTest
class CoordinatesApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Test
	void getMyPositionAndMessage() throws Exception {
		var positionSatelites = "{\"satellites\":[{\"distance\":100.0,\"message\":[\"este\",\"\",\"\",\"mensaje\",\"\"],\"name\":\"kenobi\"},{\"distance\":115.5,\"message\":[\"\",\"es\",\"\",\"\",\"secreto\"],\"name\":\"skywalker\"},{\"distance\":142.7,\"message\":[\"este\",\"\",\"un\",\"\",\"\"],\"name\":\"sato\"}]}";
	    var myPosition = mockMvc.perform(
	            MockMvcRequestBuilders.post("/topsecret/")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(positionSatelites))
	        .andExpect(status().isOk()).andReturn();
	    
	    var response = objectMapper.readValue(myPosition.getResponse().getContentAsString(), PositionDto.class);
	    
	    assert response.getMessage().equalsIgnoreCase("este es un mensaje secreto");
	}
	
	@Test
	void getNotFoundMessage() throws Exception {
		var positionSatelites = "{\"satellites\":[{\"distance\":100.0,\"message\":[\"este\",\"\",\"\",\"mensaje\",\"\"],\"name\":\"kenobi\"},{\"distance\":115.5,\"message\":[\"\",\"es\",\"\",\"\",\"secreto\"],\"name\":\"skywalker\"}]}";
	    var myPosition = mockMvc.perform(
	            MockMvcRequestBuilders.post("/topsecret/")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(positionSatelites))
	        .andExpect(status().isNotFound()).andReturn();
	    
	    assert myPosition.getResponse().getStatus() == 404;
	}

}
