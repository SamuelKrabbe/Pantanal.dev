package dev.piraputanga;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.c4_soft.springaddons.security.oauth2.test.annotations.WithMockAuthentication;

import dev.piraputanga.controller.SocialActionController;
import dev.piraputanga.model.SocialAction;
import dev.piraputanga.service.SocialActionService;

@SpringBootTest
@AutoConfigureMockMvc
public class SocialActionsControllerTests {
    @MockBean
    private SocialActionService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockAuthentication(name = "user", authorities = { "user", "admin" })
    public void testSocialActionNotFound_authorizedUser() throws Exception {
        Mockito.when(service.getSocialAction(123L)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/socialactions/123")).andExpect(status().isNotFound());
    }

    @Test
    @WithMockAuthentication(name = "user", authorities = { "user", "admin" })
    public void testSocialActionFound_authorizedUser() throws Exception {
        SocialAction socialAction = new SocialAction();
        socialAction.setId(123L);
        socialAction.setName("social action 1");
        socialAction.setDescription("social action 1 test");
        socialAction.setStatus(true);
        socialAction.setStartDate(new Date());
        socialAction.setEndDate(new Date());

        Mockito.when(service.getSocialAction(123L)).thenReturn(Optional.of(socialAction));
        this.mockMvc.perform(get("/socialactions/123")).andExpect(status().isOk());
    }

    @Test
    public void testSocialActionNotFound_unauthorizedUser() throws Exception {
        Mockito.when(service.getSocialAction(123L)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/socialactions/123")).andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @WithMockAuthentication(name = "admin", authorities = { "user", "admin" })
    public void testCreateSocialAction_authorizedUser() throws Exception {
        SocialAction socialAction = new SocialAction();
        socialAction.setId(123L);
        socialAction.setName("social action 1");
        socialAction.setDescription("social action 1 test");
        socialAction.setStatus(true);
        socialAction.setStartDate(new Date());
        socialAction.setEndDate(new Date());

        // Mock the service to return the same socialAction when createSocialAction is
        // called
        Mockito.when(service.createSocialAction(socialAction)).thenReturn(socialAction);

        // Serialize the SocialAction object to JSON and provide it as content
        String jsonRequest = "{"
                + "\"name\": \"social action 1\","
                + "\"description\": \"social action test\","
                + "\"status\": true,"
                + "\"startDate\": \"2023-10-25\","
                + "\"endDate\": \"2023-10-25\""
                + "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/socialactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }
}
