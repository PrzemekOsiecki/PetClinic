package przemek.spring.petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import przemek.spring.petclinic.model.Vet;
import przemek.spring.petclinic.service.VetService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    VetService vetServiceMock;

    @InjectMocks
    VetController uut;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(uut).build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"/vets", "/vets/", "/vets/index", "/vets/index.html"})
    void shouldReturnVetsIndexPage(String path) throws Exception {
        //given
        Set<Vet> vets = new HashSet<>();
        vets.add(Vet.builder().id(1L).build());
        vets.add(Vet.builder().id(2L).build());

        when(vetServiceMock.findAll()).thenReturn(vets);

        mockMvc.perform(get(path)).andExpect(status().isOk());
    }
}