package org.cirmmp.submissionqueue.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestJson {
    @Test
    void runJson () throws JsonProcessingException {
        //http://tutorials.jenkov.com/java-json/jackson-jsonnode.html
        String json = "{ \"f1\":\"Hello\", \"f2\":null }";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        JsonNode f2FieldNode = jsonNode.get("f1");

        assertThat(f2FieldNode.textValue()).isEqualTo("Hello");
    }
}
