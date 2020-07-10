package edu.pasudo123.study.web.petclinic.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import edu.pasudo123.study.web.petclinic.model.Pet;
import edu.pasudo123.study.web.petclinic.model.PetType;
import edu.pasudo123.study.web.petclinic.model.Visit;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class JacksonCustomVisitSerializer extends StdSerializer<Visit> {

    public JacksonCustomVisitSerializer() {
        this(null);
    }

    public JacksonCustomVisitSerializer(Class<Visit> t) {
        super(t);
    }

    @Override
    public void serialize(Visit visit, JsonGenerator gen, SerializerProvider provider) throws IOException {

        if ((visit == null) || (visit.getPet() == null)) {
            throw new IOException("Cannot serialize Visit object - visit or visit.pet is null");
        }

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // visit : start
        gen.writeStartObject();
        if(visit.getId() == null) {
            gen.writeNullField("id");
        } else {
            gen.writeNumberField("id", visit.getId());
        }

        gen.writeStringField("date", visit.getDate().format(formatter));
        gen.writeStringField("description", visit.getDescription());

        // pet : start
        final Pet pet = visit.getPet();
        gen.writeObjectFieldStart("pet");
        if(pet.getId() == null) {
            gen.writeNullField("id");
        } else {
            gen.writeNumberField("id", pet.getId());
        }

        gen.writeStringField("name", pet.getName());
        gen.writeStringField("birthDate", pet.getBirthDate().format(formatter));

        // petType : start
        final PetType petType = pet.getPetType();
        gen.writeObjectFieldStart("type");
        if(petType.getId() == null) {
            gen.writeNullField("id");
        } else {
            gen.writeNumberField("id", petType.getId());
        }
        gen.writeStringField("name", petType.getName());
        // petType : end
        gen.writeEndObject();

        // pet : end
        gen.writeEndObject();

        // visit : end
        gen.writeEndObject();
    }
}
