package org.cirmmp.submissionqueue.service;


import org.cirmmp.submissionqueue.model.OutRunCommnad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RunCommandImpl implements RunCommand{

    //https://mkyong.com/java/java-processbuilder-examples/

    Logger logger = LoggerFactory.getLogger(RunCommandImpl.class);
    @Override
    public OutRunCommnad run(List<String> cmdexe, Map<String, String > envl, File dir){
        OutRunCommnad outRunCommnad =new OutRunCommnad();
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> list = new ArrayList<>();
        //String list = "";
        logger.info("------------------------------------------------>    Enviroment {}", envl);
        logger.info("Command {}",cmdexe);
        Map<String, String> envb = processBuilder.environment();
        envb.forEach((key, value) -> System.out.println(key +"="+ value));
        envb.putAll(envl);
        processBuilder.command(cmdexe);
        processBuilder.redirectErrorStream(true);
        processBuilder.directory(dir);
        cmdexe.forEach(a -> logger.info(a));
        try {
            logger.info("Start process");
            Process process = processBuilder.start();
            // blocked :(
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            int exitCode = process.waitFor();
            logger.info("Exited with error code : " + exitCode);
            outRunCommnad.setStatus(exitCode);
            //list.forEach(builder::append);
            //list.join(builder.toString());
            //System.out.println(builder.toString());
            //outRunCommnad.setXmlout(builder.toString());
            outRunCommnad.setOutput(list);
            //logger.info(list);
            //list.forEach(System.out::println);
            logger.info("PRINT BUILDER");
            list.forEach(a -> logger.info(a));
            logger.info(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            outRunCommnad.setStatus(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
            outRunCommnad.setStatus(5);
        }
        return outRunCommnad;
    }
}
