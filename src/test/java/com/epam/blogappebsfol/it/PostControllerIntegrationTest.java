package com.epam.blogappebsfol.it;

import com.epam.blogappebsfol.BlogappEbsFolApplication;
import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.service.TestDataProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BlogappEbsFolApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllPosts() throws Exception {
        mockMvc.perform(get("/api/v1/posts"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("java talk")))
                .andExpect(jsonPath("$[0].content", is("we going to talk about java core")))
                .andExpect(jsonPath("$[0].tags", hasSize(2)));
    }

    @Test
    public void getPostsPerPage() throws Exception {
        mockMvc.perform(get("/api/v1/posts?pageNumber=1&pageSize=2"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].title", is("spring sql")))
                .andExpect(jsonPath("$[0].content", is("data storage and spring sql")))
                .andExpect(jsonPath("$[0].tags", hasSize(3)));
    }

    @Test
    public void getPostsByTag() throws Exception {
        mockMvc.perform(get("/api/v1/posts/filter?tags=hibernate"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].tags", hasItem("hibernate")));
    }

    @Test
    public void getPostsByTagPerPage() throws Exception {
        mockMvc.perform(get("/api/v1/posts/filter?pageNumber=1&pageSize=2&tags=spring"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].tags", hasItem("spring")))
                .andExpect(jsonPath("$[0].tags", hasItem("spring")));
    }

    @Test
    public void postCreateNewPost() throws Exception {
        String postDtoString = getPostDtoAsString(false);

        mockMvc.perform(post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(postDtoString))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(6)))
                .andExpect(jsonPath("$.title", is("post")))
                .andExpect(jsonPath("$.content", is("post content")))
                .andExpect(jsonPath("$.tags", hasItem("tag")));
    }
    @Test
    public void postCreateExistingPost() throws Exception {
        String postDtoString = getPostDtoAsString(true);

        mockMvc.perform(post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(postDtoString))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void patchUpdatePost() throws Exception {
        String[] tags = {"core", "java", "talk"};
        ObjectMapper mapper = new ObjectMapper();
        String tagsString = mapper.writeValueAsString(tags);

        mockMvc.perform(patch("/api/v1/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(tagsString))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("java talk")))
                .andExpect(jsonPath("$.content", is("we going to talk about java core")))
                .andExpect(jsonPath("$.tags", hasSize(3)))
                .andExpect(jsonPath("$.tags", hasItem("talk")));
    }

    @Test
    public void deletePost() throws Exception {
        String postDtoString = getPostDtoAsString(true);

        mockMvc.perform(delete("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(postDtoString))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String getPostDtoAsString(boolean withId) throws JsonProcessingException {
        PostDto postDto = PostDto.builder()
                .id(withId ? 1L : null)
                .title("post")
                .content("post content")
                .tags(Set.of("tag"))
                .build();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(postDto);
    }
}
