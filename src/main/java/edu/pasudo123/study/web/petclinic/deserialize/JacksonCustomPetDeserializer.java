package edu.pasudo123.study.web.petclinic.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.pasudo123.study.web.petclinic.model.Pet;
import edu.pasudo123.study.web.petclinic.model.PetType;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JacksonCustomPetDeserializer extends StdDeserializer<Pet> {

    public JacksonCustomPetDeserializer() {
        this(null);
    }

    public JacksonCustomPetDeserializer(Class<Pet> t) {
        super(t);
    }

    @Override
    public Pet deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        final ObjectMapper mapper = new ObjectMapper();
        final Pet pet = new Pet();

        LocalDateTime birthDate = null;

        JsonNode node = parser.getCodec().readTree(parser);
        JsonNode typeNode = node.get("type");

        // type
        final PetType type = mapper.treeToValue(typeNode, PetType.class);

        // pet
        final long petId = node.get("id").asLong();
        final String name = node.get("name").asText(null);
        final String birthDateStr = node.get("birthDate").asText(null);

        // birthdate formatter
        birthDate = LocalDateTime.parse(birthDateStr, formatter);

        if(petId != 0) {
            pet.setId(petId);
        }

        pet.setName(name);
        pet.setBirthDate(birthDate);
        pet.setPetType(type);

        return pet;
    }
}
