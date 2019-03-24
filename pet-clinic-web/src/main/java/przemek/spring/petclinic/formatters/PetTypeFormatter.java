package przemek.spring.petclinic.formatters;

import lombok.AllArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import przemek.spring.petclinic.model.PetType;
import przemek.spring.petclinic.service.PetTypeService;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@AllArgsConstructor
@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Optional<PetType> type = petTypeService.findAll().stream().filter(petType -> petType.getName().equals(text)).findFirst();

        return type.orElseThrow(() -> new ParseException("type not found: " + text, 0));
//        Collection<PetType> findPetTypes = petTypeService.findAll();
//
//        for (PetType type : findPetTypes) {
//            if (type.getName().equals(text)) {
//                return type;
//            }
//        }

//        throw new ParseException("type not found: " + text, 0);
    }
}
