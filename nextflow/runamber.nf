#!/usr/bin/env nextflow

run = Channel.fromPath("run_amber.sh")
intgz = Channel.fromPath("in.tgz")
process run_amber {
    publishDir "./output", mode: 'move'
    echo true
    tag #REPLACETAG#
    container="andreagia/ambertoolsmpi"
    executor 'condor'
    cpus 8
    input:
    file "run_amber.sh" from run
    file "in.tgz" from intgz
    output:
    file "pro.tgz"
    //file('*') into result

    """
    sh ./run_amber.sh
    """

}
