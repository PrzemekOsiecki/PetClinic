package przemek.spring.petclinic.service.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import przemek.spring.petclinic.model.Owner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    public static final String FIRST_OWNER_LASTNAME = "Lastname";
    public static final Long FIRST_OWNER_ID = 1L;
    public static final String SECOND_OWNER_LASTNAME = "Somename";
    public static final Long SECOND_OWNER_ID = 2L;
    OwnerServiceMap uut;

    @BeforeEach
    void setUp() {
        uut = new OwnerServiceMap(new PetServiceMap(), new PetTypeServiceMap());

        uut.save(Owner.builder().id(FIRST_OWNER_ID).lastName(FIRST_OWNER_LASTNAME).build());
        uut.save(Owner.builder().id(SECOND_OWNER_ID).lastName(SECOND_OWNER_LASTNAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = uut.findAll();
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        Owner owner = uut.findById(1L);
        assertNotNull(owner);
        assertEquals(FIRST_OWNER_ID, owner.getId());
    }

    @Test
    void delete() {
        uut.delete(uut.findById(1L));
        assertEquals(1, uut.findAll().size());
    }

    @Test
    void shouldSaveOwnerWithExistingId() {
        Owner owner = uut.save(Owner.builder().id(3L).build());
        Owner savedOwner = uut.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void shouldSaveOwnerWithoutExistingId() {
        Owner owner = uut.save(Owner.builder().build());
        Owner savedOwner = uut.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }


    @Test
    void deleteById() {
        uut.deleteById(1L);
        assertEquals(1, uut.findAll().size());
    }

    @Test
    void shouldReturnOwnerBasedOnHisLastName() {
        Owner foundOwner = uut.findByLastName("Lastname");
        assertNotNull(foundOwner);
        assertEquals(FIRST_OWNER_ID, foundOwner.getId());
    }

    @Test
    void shouldReturnBullIfOwnerWithGivenLastNameNotPresent() {
        Owner foundOwner = uut.findByLastName("food");
        assertNull(foundOwner);
    }

}