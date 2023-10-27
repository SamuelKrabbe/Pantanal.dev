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

import dev.piraputanga.model.SocialAction;
import dev.piraputanga.repository.SocialActionRepository;
import dev.piraputanga.service.SocialActionService;

@SpringBootTest
public class SocialActionsServiceTests {

    @Mock
    private SocialActionRepository repository;

    @InjectMocks
    private SocialActionService service;

    @Test
    public void testCreateSocialAction() throws Exception {
        SocialAction socialAction = new SocialAction();
        socialAction.setName("social action 1");
        socialAction.setDescription("social action 1 test");
        socialAction.setStatus(true);
        socialAction.setStartDate(new Date());
        socialAction.setEndDate(new Date());

        SocialAction socialAction2 = new SocialAction();
        socialAction2.setName("social action 2");
        socialAction2.setDescription("social action 2 test");
        socialAction2.setStatus(true);
        socialAction2.setStartDate(new Date());
        socialAction2.setEndDate(new Date());

        Mockito.when(repository.existsByName(socialAction.getName())).thenReturn(true);
        Mockito.when(repository.existsByName(socialAction2.getName())).thenReturn(false);

        Mockito.when(repository.save(Mockito.any(SocialAction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, SocialAction.class));

        assertThrows(DuplicateKeyException.class, () -> service.createSocialAction(socialAction));
        assertDoesNotThrow(() -> service.createSocialAction(socialAction2));
    }

    @Test
    public void testUpdateSocialAction() throws Exception {
        SocialAction socialAction = new SocialAction();
        socialAction.setName("social action 1");
        socialAction.setDescription("social action 1 test");
        socialAction.setStatus(true);
        socialAction.setStartDate(new Date());
        socialAction.setEndDate(new Date());

        SocialAction socialAction2 = new SocialAction();
        socialAction2.setName("social action 2");
        socialAction2.setDescription("social action 2 test");
        socialAction2.setStatus(true);
        socialAction2.setStartDate(new Date());
        socialAction2.setEndDate(new Date());

        Mockito.when(repository.findById(socialAction.getId())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.updateById(socialAction.getId(), socialAction2));

        Mockito.when(repository.findById(socialAction.getId())).thenReturn(Optional.of(socialAction2));

        assertDoesNotThrow(() -> service.updateById(socialAction.getId(), socialAction2));
    }

    @Test
    public void testDeleteSocialAction() throws Exception {
        SocialAction socialAction = new SocialAction();
        socialAction.setName("social action 1");
        socialAction.setDescription("social action 1 test");
        socialAction.setStatus(true);
        socialAction.setStartDate(new Date());
        socialAction.setEndDate(new Date());

        Mockito.doNothing().when(repository).deleteById(socialAction.getId());

        assertDoesNotThrow(() -> service.deleteSocialAction(socialAction.getId()));

        Mockito.verify(repository, Mockito.times(1)).deleteById(socialAction.getId());
    }

    @Test
    public void testSearchSocialAction() throws Exception {
        SocialAction socialAction = new SocialAction();
        socialAction.setName("social action 1");
        socialAction.setDescription("social action 1 test");
        socialAction.setStatus(true);
        socialAction.setStartDate(new Date());
        socialAction.setEndDate(new Date());

        List<SocialAction> socialActionsList = new ArrayList<>();
        socialActionsList.add(socialAction);

        Mockito.when(repository.findByNameContainingIgnoreCase(socialAction.getName())).thenReturn(socialActionsList);

        assertDoesNotThrow(() -> service.findSocialActions(socialAction.getName()));
        assertDoesNotThrow(() -> service.findSocialActions(""));
        assertDoesNotThrow(() -> service.findSocialActions(null));
    }
}
