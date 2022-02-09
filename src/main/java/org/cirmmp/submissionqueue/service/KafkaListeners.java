package org.cirmmp.submissionqueue.service;

import org.cirmmp.submissionqueue.model.Job;
import org.cirmmp.submissionqueue.model.OutRunCommnad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaListeners {

    @Autowired
    RunCommandLocal runCommandLocal;

    private final Logger LOG = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(id = "1", topics = "reflectoring-sendjobs", groupId = "reflectoring-sendjobs-mc", containerFactory = "kafkaJsonListenerContainerFactory")
    void listenerWithMessageConverter(Job job) throws Exception {
        LOG.info("MessageConverterUserListener [{}]", job);
        CompletableFuture<OutRunCommnad> outRunCommnad = runCommandLocal.runjob(job);
        LOG.info("MessageConverterUserListener [{}]", outRunCommnad);
    }
}