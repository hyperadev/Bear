package dev.hypera.bear.serialization.impl.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import org.jetbrains.annotations.NotNull;

public class InstantAdapter extends TypeAdapter<Instant> {

    private final @NotNull DateTimeFormatter formatter;

    public InstantAdapter(@NotNull DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void write(JsonWriter writer, Instant instant) throws IOException {
        if (null == instant) {
            writer.nullValue();
        } else {
            writer.value(formatter.format(instant));
        }
    }

    @Override
    public Instant read(JsonReader reader) throws IOException {
        JsonToken token = reader.peek();
        if (token.equals(JsonToken.NULL)) {
            reader.nextNull();
            return null;
        }

        String string = reader.nextString();
        if (null == string) {
            return null;
        }

        TemporalAccessor accessor;
        try {
            accessor = formatter.parse(string);
        } catch (DateTimeParseException ex) {
            return null;
        }

        return Instant.from(accessor);
    }

}
