package przemek.spring.petclinic.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import przemek.spring.petclinic.model.Owner;
import przemek.spring.petclinic.service.OwnerService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerServiceMock;

    @InjectMocks
    OwnerController uut;

    MockMvc mockMvc;

    Set<Owner> owners;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(uut)
                .build();

        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/owners/", "/owners/index", "/owners/index.html", "/owners/find", "/owners/index"})
    void shouldReturnListOfOwners(String path) throws Exception {
        //when
        when(ownerServiceMock.findAll()).thenReturn(owners);

        //then
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", Matchers.equalTo(owners)))
                .andExpect(model().attribute("owners", Matchers.hasSize(2)));
    }
}