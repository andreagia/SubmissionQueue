package org.cirmmp.submissionqueue.service;


import org.cirmmp.submissionqueue.model.Job;
import org.cirmmp.submissionqueue.model.OutRunCommnad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RunCommandLocalImpl implements RunCommandLocal{

    Logger logger = LoggerFactory.getLogger(RunCommandLocalImpl.class);

    @Value("${nextflow.bin}")
    private String nextflowbin;
    @Value("${java.home}")
    private String javahome;

    @Autowired
    private RunCommand runCommand;

    @Override
    public OutRunCommnad runjob(Job job) throws Exception {


        //instert tag to nextflow
        Path nextflowt = Paths.get(job.getDirectory()+"/tutorial.nf.t");
        Path nextflow = Paths.get(job.getDirectory()+"/tutorial.nf");
        try (Stream<String> lines = Files.lines(nextflowt)) {
            List<String> replaced = lines
                    .map(line-> line.replaceAll("#REPLACETAG#", job.getTag()))
                    .collect(Collectors.toList());
            Files.write(nextflow, replaced);
        }
        //List<String> cmdexe = Arrays.asList(nextflowbin, "-q", "-bg", "run tutorial.nf", "-with-weblog http://localhost:8080");
        List<String> cmdexe = Arrays.asList(nextflowbin, "-q", "-bg", "run", "tutorial.nf", "-with-weblog", "http://localhost:8080");
        Map<String,String> env = Collections.EMPTY_MAP;
        //env.put("JAVA_HOME",javahome);
        //env.put("PATH", "$JAVA_HOME/bin;$PATH");
        OutRunCommnad outRunCommnad = runCommand.run(cmdexe, env, new File(job.getDirectory()));
        return outRunCommnad;
    }
}
