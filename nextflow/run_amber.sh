#!/bin/bash
#
# amber_run.sh - This script is meant to run on Grid or Container
#

[ -z "$AMBERMPI" ] && RUN=$AMBERHOME/bin/sander || RUN="mpirun -np 8 sander.MPI"

#RUN="mpirun -np 8 sander.MPI"

tar xvfz in.tgz


#AMBER_COMMAND
$RUN -O -i sander0.in -o sander0.out -p prmtop -c prmcrd -r sander0.crd -ref  prmcrd
$AMBERHOME/bin/ambpdb -p prmtop -c sander0.crd > amber_final0.pdb
perl gettensor.pl sander0.out
$RUN -O -i sander1.in -o sander1.out -p prmtop -c sander0.crd -r sander1.crd -ref  sander0.crd
$AMBERHOME/bin/ambpdb -p prmtop -c sander1.crd > amber_final1.pdb
$RUN -O -i sander2.in -o sander2.out -p prmtop -c sander1.crd -r sander2.crd -ref  sander1.crd
$AMBERHOME/bin/ambpdb -p prmtop -c sander2.crd > amber_final2.pdb
$RUN -O -i sander3.in -o sander3.out -p prmtop -c sander2.crd -r sander3.crd -ref  sander2.crd
$AMBERHOME/bin/ambpdb -p prmtop -c sander3.crd > amber_final3.pdb
$RUN -O -i sander4.in -o sander4.out -p prmtop -c sander3.crd -r sander4.crd -ref  sander3.crd
$AMBERHOME/bin/ambpdb -p prmtop -c sander4.crd > amber_final4.pdb
$RUN -O -i sander5.in -o sander5.out -p prmtop -c sander4.crd -r sander5.crd -ref  sander4.crd
$AMBERHOME/bin/ambpdb -p prmtop -c sander5.crd > amber_final5.pdb


#$DIRAE/sander -O -i sander.in -o sander.out -p prmtop -c prmcrd -r prm_out.crd
#$DIRAE/ambpdb -p prmtop < prm_out.crd > amber_final.pdb

tar cvfz pro.tgz ./* --exclude in.tgz --exclude run_amber.sh --exclude gettensor.pl
