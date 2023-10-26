package dev.piraputanga;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.c4_soft.springaddons.security.oauth2.test.annotations.WithMockAuthentication;

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
    @WithMockAuthentication("ROLE_realm_admin")
    public void testSocialActionNotFound_authorizedUser() throws Exception {
        Mockito.when(service.getSocialAction(123L)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/socialactions/123")).andExpect(status().isNotFound());
    }

    @Test
    @WithMockAuthentication("ROLE_realm_user")
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
    @WithMockAuthentication("ROLE_realm_admin")
    public void testCreateSocialAction_authorizedUser() throws Exception {

        SocialAction socialAction = new SocialAction();
        socialAction.setName("social action 1");
        socialAction.setDescription("social action 1 test");
        socialAction.setStatus(true);
        socialAction.setStartDate(new Date());
        socialAction.setEndDate(new Date());

        Mockito.when(service.createSocialAction(any(SocialAction.class))).thenReturn(socialAction);

        String jsonRequest = "{"
                + "\"name\": \"social action 1\","
                + "\"status\": true,"
                + "\"description\": \"social action 1 test\","
                + "\"startDate\": \"2023-10-25\","
                + "\"endDate\": \"2023-10-25\""
                + "}";

        this.mockMvc
                .perform(post("/socialactions")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockAuthentication("ROLE_realm_user")
    public void testCreateSocialAction_unauthorizedUser() throws Exception {

        SocialAction socialAction = new SocialAction();
        socialAction.setName("social action 1");
        socialAction.setDescription("social action 1 test");
        socialAction.setStatus(true);
        socialAction.setStartDate(new Date());
        socialAction.setEndDate(new Date());

        Mockito.when(service.createSocialAction(any(SocialAction.class))).thenReturn(socialAction);

        String jsonRequest = "{"
                + "\"name\": \"social action 1\","
                + "\"status\": true,"
                + "\"description\": \"social action 1 test\","
                + "\"startDate\": \"2023-10-25\","
                + "\"endDate\": \"2023-10-25\""
                + "}";

        this.mockMvc
                .perform(post("/socialactions")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @WithMockAuthentication("ROLE_realm_admin")
    public void testDeleteSocialAction_authorizedUser() throws Exception {

        SocialAction socialAction = new SocialAction();
        socialAction.setId(1L);
        socialAction.setName("social action 1");
        socialAction.setDescription("social action 1 test");
        socialAction.setStatus(true);
        socialAction.setStartDate(new Date());
        socialAction.setEndDate(new Date());

        Mockito.doNothing().when(service).deleteSocialAction(1L);
        Mockito.when(service.getSocialAction(1L)).thenReturn(Optional.of(socialAction));

        this.mockMvc
                .perform(delete("/socialactions/1").with(csrf()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockAuthentication("ROLE_realm_user")
    public void testDeleteSocialAction_unauthorizedUser() throws Exception {

        SocialAction socialAction = new SocialAction();
        socialAction.setId(1L);
        socialAction.setName("social action 1");
        socialAction.setDescription("social action 1 test");
        socialAction.setStatus(true);
        socialAction.setStartDate(new Date());
        socialAction.setEndDate(new Date());

        Mockito.doNothing().when(service).deleteSocialAction(1L);
        Mockito.when(service.getSocialAction(1L)).thenReturn(Optional.of(socialAction));

        this.mockMvc
                .perform(delete("/socialactions/1").with(csrf()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }
}
