package edu.pasudo123.study.web.petclinic.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import edu.pasudo123.study.web.petclinic.model.Pet;
import edu.pasudo123.study.web.petclinic.model.PetType;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * StdSerializer<Pet>
 * - 표준 직렬화에 사용되는 기본 클래스
 * - 커스텀 시리얼라이저에 사용할 수 있다.
 */
public class JacksonCustomPetSerializer extends StdSerializer<Pet> {

    public JacksonCustomPetSerializer(){
        this(null);
    }

    protected JacksonCustomPetSerializer(Class<Pet> t) {
        super(t);
    }

    @Override
    public void serialize(Pet pet, JsonGenerator gen, SerializerProvider provider) throws IOException {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // pet : start
        gen.writeStartObject();
        if(pet.getId() == null) {
            gen.writeNullField("id");
        } else {
            gen.writeNumberField("id", pet.getId());
        }

        gen.writeStringField("name", pet.getName());
        gen.writeStringField("birthDate", pet.getBirthDate().format(formatter));

        final PetType petType = pet.getPetType();
        // petType : start
        gen.writeObjectFieldStart("type");
        gen.writeNumberField("id", petType.getId());
        gen.writeStringField("name", petType.getName());
        gen.writeEndObject();
        // petType : end

        // pet : end
        gen.writeEndObject();
    }
}
