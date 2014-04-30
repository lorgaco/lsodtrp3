#! /bin/csh -f
#
# lanza el Servidor de Nombres en el puerto "4000" de la máquina local
# Es inmediato modificarlo para que el puerto sea configurable
# También se puede lanzar el de C++ :
# nameserv -IIOPport 4000
setenv CLASSPATH /opt/orbacus/lib/OB.jar:/opt/orbacus/lib/OBNaming.jar
setenv BOOTCLASSPATH -Xbootclasspath/p:$CLASSPATH
java $BOOTCLASSPATH com.ooc.CosNaming.Server -IIOPport 4000
