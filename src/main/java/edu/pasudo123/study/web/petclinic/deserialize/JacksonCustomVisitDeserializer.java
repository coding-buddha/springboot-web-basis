package edu.pasudo123.study.web.petclinic.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.pasudo123.study.web.petclinic.model.Pet;
import edu.pasudo123.study.web.petclinic.model.Visit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JacksonCustomVisitDeserializer extends StdDeserializer<Visit> {

    public JacksonCustomVisitDeserializer() {
        this(null);
    }

    public JacksonCustomVisitDeserializer(Class<Visit> t) {
        super(t);
    }

    @Override
    public Visit deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        final ObjectMapper mapper = new ObjectMapper();
        final Visit visit = new Visit();

        LocalDateTime visitDate = null;

        JsonNode node = parser.getCodec().readTree(parser);
        JsonNode petNode = node.get("pet");

        // pet
        final Pet pet = mapper.treeToValue(petNode, Pet.class);

        // visit
        final long visitId = node.get("id").asLong();
        final String visitDateStr = node.get("date").asText(null);
        final String description = node.get("description").asText(null);

        // visitDate formatter
        visitDate = LocalDateTime.parse(visitDateStr, formatter);

        if (!(visitId == 0)) {
            visit.setId(visitId);
        }

        visit.setDate(visitDate);
        visit.setDescription(description);
        visit.setPet(pet);

        return visit;
    }
}
