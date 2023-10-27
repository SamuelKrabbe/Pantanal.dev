package dev.piraputanga;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.server.ResponseStatusException;

import dev.piraputanga.model.SocialContract;
import dev.piraputanga.repository.SocialContractRepository;
import dev.piraputanga.service.SocialContractService;

@SpringBootTest
public class SocialContractServiceTests {

    @Mock
    private SocialContractRepository repository;

    @InjectMocks
    private SocialContractService service;

    @Test
    public void testCreateSocialContract() throws Exception {
        SocialContract socialContract = new SocialContract();
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        SocialContract socialContract2 = new SocialContract();
        socialContract2.setSocialActionId(124L);
        socialContract2.setUserEmail("test2@test.com");
        socialContract2.setContractDate(new Date());
        Mockito.when(repository.existsBySocialActionId(socialContract.getSocialActionId())).thenReturn(true);
        Mockito.when(repository.existsByUserEmail(socialContract.getUserEmail())).thenReturn(true);

        Mockito.when(repository.save(Mockito.any(SocialContract.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, SocialContract.class));

        assertThrows(DuplicateKeyException.class, () -> service.createSocialContract(socialContract));
        assertDoesNotThrow(() -> service.createSocialContract(socialContract2));
    }

    @Test
    public void testDeleteSocialContract() throws Exception {
        SocialContract socialContract = new SocialContract();
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        Mockito.doNothing().when(repository).deleteById(socialContract.getId());

        assertDoesNotThrow(() -> service.deleteSocialContract(socialContract.getId()));

        Mockito.verify(repository, Mockito.times(1)).deleteById(socialContract.getId());
    }

    @Test
    public void testSearchSocialContractByEmail() throws Exception {
        SocialContract socialContract = new SocialContract();
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        List<SocialContract> socialContractsList = new ArrayList<>();
        socialContractsList.add(socialContract);

        Mockito.when(repository.findByUserEmailContainingIgnoreCase(socialContract.getUserEmail())).thenReturn(socialContractsList);

        assertDoesNotThrow(() -> service.findSocialContracts(socialContract.getUserEmail()));
        assertDoesNotThrow(() -> service.findSocialContracts(""));
        assertDoesNotThrow(() -> service.findSocialContracts(null));
    }

    @Test
    public void testSearchSocialContractBySocialActionId() throws Exception {
        SocialContract socialContract = new SocialContract();
        socialContract.setSocialActionId(123L);
        socialContract.setUserEmail("test@test.com");
        socialContract.setContractDate(new Date());

        List<SocialContract> socialContractsList = new ArrayList<>();
        socialContractsList.add(socialContract);

        Mockito.when(repository.findBySocialActionId(socialContract.getSocialActionId())).thenReturn(socialContractsList);

        assertDoesNotThrow(() -> service.findSocialContractsBySocialAction(socialContract.getSocialActionId()));
        assertDoesNotThrow(() -> service.findSocialContractsBySocialAction(0L));
        assertDoesNotThrow(() -> service.findSocialContractsBySocialAction(null));
    }

}