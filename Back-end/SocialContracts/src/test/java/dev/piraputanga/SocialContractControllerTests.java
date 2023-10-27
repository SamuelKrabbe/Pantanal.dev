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

import dev.piraputanga.model.SocialContract;
import dev.piraputanga.service.SocialContractService;
import lombok.With;

@SpringBootTest
@AutoConfigureMockMvc
public class SocialContractControllerTests {
    @MockBean
    private SocialContractService service;

    @Autowired
    private MockMvc mockMvc;

    /*@Test
    @WithMockAuthentication("ROLE_realm_admin")
    public void testSocialContractNotFound_authorizedUser() throws Exception {
        Mockito.when(service.getSocialContract(123L)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/socialcontracts/123")).andExpect(status().isNotFound());
    }*/

    @Test
    @WithMockAuthentication("ROLE_realm_user")
    public void testSocialContractFound_authorizedUser() throws Exception {
        SocialContract socialContract = new SocialContract();
        socialContract.setId(123L);
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());
        Mockito.when(service.getSocialContract(123L)).thenReturn(Optional.of(socialContract));
        this.mockMvc.perform(get("/socialcontracts/123")).andExpect(status().isOk());
    }

    @Test
    public void testSocialContractNotFound_unauthorizedUser() throws Exception {
        Mockito.when(service.getSocialContract(123L)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/socialcontracts/123")).andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @WithMockAuthentication("ROLE_realm_admin")
    public void testCreateSocialContract_authorizedUser() throws Exception {

        SocialContract socialContract = new SocialContract();
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        Mockito.when(service.createSocialContract(any(SocialContract.class))).thenReturn(socialContract);

        String jsonRequest = "{"
                + "\"socialActionId\": 123,"
                + "\"userEmail\": \"test@test.com\","
                + "\"contractDate\": \"2023-10-26T00:00:00.000Z\""
                + "}";

        this.mockMvc
                .perform(post("/socialcontracts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockAuthentication("ROLE_realm_user")
    public void testCreateSocialContract_unauthorizedUser() throws Exception {

        SocialContract socialContract = new SocialContract();
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        Mockito.when(service.createSocialContract(any(SocialContract.class))).thenReturn(socialContract);

        String jsonRequest = "{"
                + "\"socialActionId\": 123,"
                + "\"userEmail\": \"test@test.com\","
                + "\"contractDate\": \"2023-10-26T00:00:00.000Z\""
                + "}";

        this.mockMvc
                .perform(post("/socialcontracts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @WithMockAuthentication("ROLE_realm_admin")
    public void testDeleteSocialContract_authorizedUser() throws Exception {

        SocialContract socialContract = new SocialContract();
        socialContract.setId(1L);
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        Mockito.doNothing().when(service).deleteSocialContract(1L);
        Mockito.when(service.getSocialContract(1L)).thenReturn(Optional.of(socialContract));

        this.mockMvc
                .perform(delete("/socialcontracts/1").with(csrf()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockAuthentication("ROLE_realm_user")
    public void testDeleteSocialAction_unauthorizedUser() throws Exception {

        SocialContract socialContract = new SocialContract();
        socialContract.setId(1L);
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        Mockito.doNothing().when(service).deleteSocialContract(1L);
        Mockito.when(service.getSocialContract(1L)).thenReturn(Optional.of(socialContract));

        this.mockMvc
                .perform(delete("/socialcontracts/1").with(csrf()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @WithMockAuthentication("ROLE_realm_user")
    public void testGetAllContracts_unauthorizedUser() throws Exception{

        SocialContract socialContract = new SocialContract();
        socialContract.setId(1L);
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        Mockito.when(service.createSocialContract(any(SocialContract.class))).thenReturn(socialContract);
        
        this.mockMvc.perform(get("/socialcontracts")).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
        


    }

    @Test
    @WithMockAuthentication("ROLE_realm_admin")
    public void testGetAllContracts_authorizedUser() throws Exception{

        SocialContract socialContract = new SocialContract();
        socialContract.setId(1L);
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        Mockito.when(service.createSocialContract(any(SocialContract.class))).thenReturn(socialContract);
        
        this.mockMvc.perform(get("/socialcontracts")).andExpect(status().isOk());
        


    }

    @Test
    @WithMockAuthentication("ROLE_realm_user")
    public void testGetUserContracts_authorizedUser() throws Exception{

        SocialContract socialContract = new SocialContract();
        socialContract.setId(1L);
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        Mockito.when(service.createSocialContract(any(SocialContract.class))).thenReturn(socialContract);

        this.mockMvc.perform(get("/socialcontracts/usersocialcontracts")).andExpect(status().isOk());

    }

}



