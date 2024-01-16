package proiect.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import proiect.Micunelte.CreateOrder;
import proiect.Micunelte.Order;
import proiect.Micunelte.Role;
import proiect.Micunelte.User;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Order order;
    private User customer;
    private CreateOrder createOrder;

    @BeforeEach
    void setUp(){
        customer = new User("nameClient", "username2", "email@email.com", "Parola1234", "0722222223", "adresa2", "B999BBC", Role.CUSTOMER);
        Date date = new Date();
        order = new Order(8, date);
    }

    @Test
    void createOrder() throws Exception {
      String response =   mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        User customerResponse = objectMapper.readValue(response, User.class);

        createOrder = new CreateOrder(customerResponse.getUsername(), order);

        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createOrder)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderNumber").value(8));

    }


}
