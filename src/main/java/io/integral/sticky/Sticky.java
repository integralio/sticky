package io.integral.sticky;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.IOException;
import java.util.Map;

@Entity
public class Sticky {

    @SuppressWarnings("unused")
    @Id
    @GeneratedValue
    private long id;

    private String content;

    static Sticky createFromJson(String jsonBody) throws IOException {
        Sticky sticky = new Sticky();

        sticky.content = getContentFromJson(jsonBody);

        return sticky;
    }

    private static String getContentFromJson(String jsonBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.readValue(jsonBody, Map.class);

        if (!result.containsKey("content"))
            throw new IOException("Missing content key");

        return (String) result.get("content");
    }

    private Sticky() {
    }

    @SuppressWarnings("unused")
    public void setContent(String content) {
        this.content = content;
    }

    @SuppressWarnings("unused")
    public String getContent() {
        return content;
    }
}
