package org.cirmmp.submissionqueue.service;


import org.cirmmp.submissionqueue.model.Job;
import org.cirmmp.submissionqueue.model.OutRunCommnad;

public interface RunCommandLocal {
    OutRunCommnad runjob(Job job) throws Exception;
}
