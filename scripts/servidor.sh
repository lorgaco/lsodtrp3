#! /bin/csh -f
#
# Recibe un argumento: el nombre del ordenador en el que se ha arrancado
# el Servidor de Nombres. Es inmediato modificarlo para que el puerto del
# Servidor de Nombres también sea configurable
if ($#argv != 1) then
cat << end-of-help-message
   uso: $0 máquina
	  máquina    nombre de la máquina en la que corre el Servidor de Nombres
end-of-help-message
exit(-1)
endif
setenv CLASSPATH /opt/orbacus/lib/OB.jar:/opt/orbacus/lib/OBEvent.jar:/opt/orbacus/lib/OBNaming.jar:/opt/orbacus/lib/OBProperty.jar:/opt/orbacus/lib/OBTest.jar:/opt/orbacus/lib/OBUtil.jar:clases
setenv BOOTCLASSPATH -Xbootclasspath/p:$CLASSPATH
java $BOOTCLASSPATH lsodtr.Servidor -k 1234 -ORBInitRef NameService=corbaloc:iiop:${1}:4000/NameService
