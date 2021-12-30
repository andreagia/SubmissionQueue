
run = Channel.fromPath("run_amber.sh")
intgz = Channel.fromPath("in.tgz")
process sayHello {
    echo true
    container="andreagia/ambertoolsmpi"
    executor 'condor'
    cpus 8
    input:
    file "run_amber.sh" from run
    file "in.tgz" from intgz
    output:
    file('*') into result

    """
    #echo 'Hello world!' > file
    #ls -h ./  > pino
    #pwd > pino2
    sh ./run_amber.sh > out
    """

}
result.println()