package io.integral.sticky;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StickyTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StickyRepository repository;

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    private ResultActions postSticky(String content) throws Exception {
        return mvc.perform(
                post("/sticky")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"" + content + "\"}"));
    }

    private ResultActions getStickies() throws Exception {
        return mvc.perform(get("/sticky"));
    }

    @Test
    public void responseContainsEmptyArray_whenThereAreNoStickies() throws Exception {
        getStickies()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    public void responseOK_whenAddingSticky() throws Exception {
        postSticky("hello")
                .andExpect(status().isOk());
    }


    @Test
    public void responseContainsArrayWithOneSticky_whenAddingOneSticky() throws Exception {
        postSticky("hello2");
        getStickies()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("hello2"));
    }

    @Test
    public void responseBadRequest_whenInvalidJson() throws Exception {
        mvc.perform(post("/sticky")
                .contentType(MediaType.APPLICATION_JSON)
                .content("invalid text"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void responseBadRequest_whenJSONMissingContentKey() throws Exception {
        mvc.perform(post("/sticky")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"someotherkey\": \"myvalue\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void responseOK_whenJSONHasAdditionalInfo() throws Exception {

        String json =
                "{ \"content\": \"content\" , \n" +
                        "\"somethingelse\": \"value\"\n" +
                        "}";


        mvc.perform(post("/sticky")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }
}
