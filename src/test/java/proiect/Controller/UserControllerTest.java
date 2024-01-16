package proiect.Controller;

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
import proiect.Micunelte.Role;
import proiect.Micunelte.UpdateDelivererInfo;
import proiect.Micunelte.UpdateLocation;
import proiect.Micunelte.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private User customer;
    private User administrator;
    private User deliverer;
    private UpdateLocation updateLocation;
    private UpdateDelivererInfo updateDelivererInfo;

    @BeforeEach //inainte de orice test se apeleaza setUp
    void setUp() {
        administrator = new User("name", "username1", "emailPatron@email.com", "Parola123", "0737057469", "adresa1", "B999BBB", Role.ADMINISTRATOR);
        customer = new User("nameClient", "username2", "email@email.com", "Parola1234", "0722222223", "adresa2", "B999BBC", Role.CUSTOMER);
        deliverer = new User("numeDeliverer", "username3", "email@email.com", "Parola1235", "0734567123", "adresa3", "B999BBD", Role.DELIVERER);

    }

    @Test
    void createAdministratorTest() throws Exception {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON) //pentru JSON
                .content(objectMapper.writeValueAsString(administrator))) //transforma administratorul de il declar mai sus din interfata user in JSON
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("username1")); //verific ca ce primesc inapoi este chiar username1, adica administratorul
    }

    @Test
    void createCustomerTest() throws Exception {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("username2"));
    }

    @Test
    void createDelivererTestUnauthorized() throws Exception {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deliverer)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createDelivererTest() throws Exception {
        String responseString = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administrator)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        User admin = objectMapper.readValue(responseString, User.class);

        mockMvc.perform(post("/user/createDeliverer/" + admin.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deliverer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("username3"));

    }

    @Test
    void getUserTest() throws Exception {
        String responseString = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administrator)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        User admin = objectMapper.readValue(responseString, User.class);

        mockMvc.perform(get("/user/" + admin.getUsername()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(admin.getUsername()));
    }


    @Test
    void updateLocationTest() throws Exception {
        String responseCustomer = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        User customer = objectMapper.readValue(responseCustomer, User.class);
        updateLocation = new UpdateLocation(customer.getUsername(), "locatieNoua");

        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateLocation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deliveryAddress").value("locatieNoua"));
    }

    @Test
    void updateDelivererInfoTest() throws Exception {
        String responseString = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administrator)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        User admin = objectMapper.readValue(responseString, User.class);

        String responseDeliverer = mockMvc.perform(post("/user/createDeliverer/" + admin.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deliverer)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        User deliverer = objectMapper.readValue(responseDeliverer, User.class);
        updateDelivererInfo = new UpdateDelivererInfo(deliverer.getUsername(), "B25DDD");

        mockMvc.perform(put("/user/updateDelivererInfo/" + admin.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDelivererInfo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carRegistrationNumber").value("B25DDD"));
    }

    @Test
    void deleteCustomerTest() throws Exception {
        String response = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        User customerResponse = objectMapper.readValue(response, User.class);


        String responseString = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administrator)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        User admin = objectMapper.readValue(responseString, User.class);
        mockMvc.perform(delete("/user/" + customer.getUsername()+"/"+admin.getUsername()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/customers/" + customerResponse.getUsername()))
                .andExpect(status().isNotFound());
    }
}
