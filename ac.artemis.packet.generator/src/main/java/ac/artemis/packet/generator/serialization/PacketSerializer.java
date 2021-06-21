package ac.artemis.packet.generator.serialization;

import ac.artemis.packet.wrapper.PacketClass;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.SneakyThrows;

import java.io.IOException;

public class PacketSerializer extends TypeAdapter<PacketClass> {
    /**
     * Writes one JSON value (an array, object, string, number, boolean or null) for {@code value}.
     *
     * @param out
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, PacketClass value) throws IOException {
        out.value(value.getClazz() == null ? null : value.getClazz().getName());
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null) and converts it to a Java object.
     * Returns the converted object.
     *
     * @param in
     *
     * @return the converted Java object. May be null.
     */
    @SneakyThrows
    @Override
    public PacketClass read(JsonReader in) throws IOException {
        final String string = in.nextString();
        return string == null ? new PacketClass(null) : new PacketClass((Class) Class.forName(string));
    }
}
