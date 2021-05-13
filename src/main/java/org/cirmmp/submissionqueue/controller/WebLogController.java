package org.cirmmp.submissionqueue.controller;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cirmmp.submissionqueue.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class WebLogController {

    @Autowired
    private KafkaSender kafkaSender;

    @SneakyThrows
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Boolean> consumeEvent(@RequestBody JsonNode event) {
        // Use hashcode to see if identical events are being submitted
        log.info("Received event with hashcode: {}", event.hashCode());
        //log.info("handling event: {}", event);
        //ProducerRecord<String, JsonNode> rec = new ProducerRecord<String, JsonNode>("reflectoring-json",event);
        //kafkaSenderExample.sendJsonMessage(event);
        kafkaSender.sendJsonMessageWithCallback(event);
        //webLogService.handleEvent(event);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
