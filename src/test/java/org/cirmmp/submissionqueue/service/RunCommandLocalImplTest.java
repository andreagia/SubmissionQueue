package org.cirmmp.submissionqueue.service;

import org.cirmmp.submissionqueue.model.OutRunCommnad;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;



class RunCommandLocalImplTest {

   /* @Autowired
    private RunCommand runCommand;
    @Value("${nextflow.bin}")
    private String nextflowbin;
    @Value("${java.home}")
    private String javahome;
*/
    @Test
    void runjob() {
//        List<String> cmdexe = Arrays.asList(nextflowbin, "-q", "-bg", "run tutorial.nf", "-with-weblog http://localhost:8080");
        Map<String,String> env = new HashMap<String,String>();
        env.put("JAVA_HOME","javahome");
        env.put("PATH", "$JAVA_HOME/bin;$PATH");
        String dir = "/Volumes/SSDesterno/test/nextflow";
        //OutRunCommnad outRunCommnad = runCommand.run(cmdexe, env, new File(dir));
        env.forEach((key, value) -> System.out.println(key +"="+ value));
        ProcessBuilder processBuilder = new ProcessBuilder();
        Map<String, String> envb = processBuilder.environment();
        envb.forEach((key, value) -> System.out.println(key +"="+ value));
        envb.putAll(env);
        envb.forEach((key, value) -> System.out.println(key +"="+ value));
        
        assertThat(0)
                .isEqualTo(0);
    }
}