package org.cirmmp.submissionqueue.service;


import org.cirmmp.submissionqueue.model.Job;
import org.cirmmp.submissionqueue.model.OutRunCommnad;

import java.util.concurrent.CompletableFuture;

public interface RunCommandLocal {
    CompletableFuture<OutRunCommnad> runjob(Job job) throws Exception;
}
