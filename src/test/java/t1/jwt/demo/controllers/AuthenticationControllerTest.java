package t1.jwt.demo.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import t1.jwt.demo.model.SignUpRequest;
import t1.jwt.demo.utils.EndPoint;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void greatNewUser() throws Exception {

        SignUpRequest request = new SignUpRequest("new", "new@mail.ru", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.post(EndPoint.API + EndPoint.AUTH + EndPoint.REGISTER)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.token").exists())
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getWithoutToken() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get(EndPoint.EXAMPLE)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    void auth() throws Exception {

        SignUpRequest request = new SignUpRequest("new", "new@mail.ru", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post(EndPoint.API + EndPoint.AUTH + EndPoint.LOGIN)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.token").exists())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\"}", "");
        System.out.println(token + " - token");
        mockMvc.perform(
                        MockMvcRequestBuilders.get(EndPoint.EXAMPLE)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    //todo негативный тест + удаление после кейса + на обновления тест
}
