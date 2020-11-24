package edu.pasudo123.study.web.petclinic.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
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
    public Pet deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        final ObjectMapper mapper = new ObjectMapper();

        LocalDateTime birthDate = null;

        JsonNode node = parser.getCodec().readTree(parser);

        // pet
        final long petId = node.get("id").asLong();
        final String name = node.get("name").asText(null);
        final String birthDateStr = node.get("birthDate").asText(null);

        // birthdate formatter
        birthDate = LocalDateTime.parse(birthDateStr, formatter);

        return Pet.builder()
                .id(petId)
                .name(name)
                .birthDate(birthDate)
                .build();
    }
}
