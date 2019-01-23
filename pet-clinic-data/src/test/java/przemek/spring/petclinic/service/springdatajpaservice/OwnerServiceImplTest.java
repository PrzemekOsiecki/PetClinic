package przemek.spring.petclinic.service.springdatajpaservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import przemek.spring.petclinic.model.Owner;
import przemek.spring.petclinic.repository.OwnerRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

    private static final long EXPECTED_OWNER_ID = 1L;
    private static final String EXPECTED_OWNER_NAME = "Smith";
    public static final Owner EXPECTED_OWNER = Owner.builder().id(EXPECTED_OWNER_ID).lastName(EXPECTED_OWNER_NAME).build();
    @Mock
    OwnerRepository ownerRepositoryMock;
    OwnerServiceImpl uut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        uut = new OwnerServiceImpl(ownerRepositoryMock);
    }

    @Test
    void shouldReturnOwnerByLastName() {
        //when
        when(ownerRepositoryMock.findByLastName(EXPECTED_OWNER_NAME)).thenReturn(Optional.ofNullable(EXPECTED_OWNER));

        Owner actualOwner = uut.findByLastName(EXPECTED_OWNER_NAME);

        //then
        assertNotNull(actualOwner);
        assertEquals(EXPECTED_OWNER, actualOwner);
        verify(ownerRepositoryMock).findByLastName(EXPECTED_OWNER_NAME);
    }

    @Test
    void shouldReturnNullIfOwnerWithGivenLastNameIsNotPresent() {
        when(ownerRepositoryMock.findByLastName(EXPECTED_OWNER_NAME)).thenReturn(Optional.empty());

        Owner actualOwner = uut.findByLastName(EXPECTED_OWNER_NAME);

        assertNull(actualOwner);
        verify(ownerRepositoryMock).findByLastName(EXPECTED_OWNER_NAME);
    }

    @Test
    void shouldReturnAllOwners() {
        //given
        Set<Owner> expectedOwners = new HashSet<>();
        expectedOwners.add(Owner.builder().id(1L).build());
        expectedOwners.add(Owner.builder().id(2L).build());

        //when
        when(ownerRepositoryMock.findAll()).thenReturn(expectedOwners);

        Set<Owner> actualOwners = uut.findAll();

        //then
        assertNotNull(actualOwners);
        assertEquals(expectedOwners, actualOwners);
        assertEquals(2, actualOwners.size());
        verify(ownerRepositoryMock).findAll();
    }

    @Test
    void shouldReturnOwnerById() {
        //when
        when(ownerRepositoryMock.findById(EXPECTED_OWNER_ID)).thenReturn(Optional.ofNullable(EXPECTED_OWNER));

        Owner actualOwner = uut.findById(EXPECTED_OWNER_ID);

        //then
        assertNotNull(actualOwner);
        assertEquals(EXPECTED_OWNER, actualOwner);
        verify(ownerRepositoryMock).findById(1L);
    }

    @Test
    void shouldReturnNullIFOwnerWithGivenIdIsNotPresent() {
        when(ownerRepositoryMock.findById(EXPECTED_OWNER_ID)).thenReturn(Optional.empty());

        Owner actualOwner = uut.findById(EXPECTED_OWNER_ID);

        assertNull(actualOwner);
        verify(ownerRepositoryMock).findById(EXPECTED_OWNER_ID);
    }

    @Test
    void shouldSaveOwner() {
        //when
        when(ownerRepositoryMock.save(EXPECTED_OWNER)).thenReturn(EXPECTED_OWNER);

        Owner actualOwner = uut.save(EXPECTED_OWNER);

        //then
        assertNotNull(actualOwner);
        assertEquals(EXPECTED_OWNER, actualOwner);
        verify(ownerRepositoryMock).save(EXPECTED_OWNER);

    }

    @Test
    void delete() {
        //when
        uut.delete(EXPECTED_OWNER);

        //then
        verify(ownerRepositoryMock).delete(EXPECTED_OWNER);
    }

    @Test
    void deleteById() {
        //when
        uut.deleteById(EXPECTED_OWNER_ID);

        //then
        verify(ownerRepositoryMock).deleteById(EXPECTED_OWNER_ID);
    }
}