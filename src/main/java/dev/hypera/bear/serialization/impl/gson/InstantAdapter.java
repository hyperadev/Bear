/*
 * Bear - Simple debugging library
 *  Copyright (c) 2022 Joshua Sing
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
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
