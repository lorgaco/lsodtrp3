#! /bin/csh -f
#
# Recibe un argumento: el nombre del ordenador en el que se ha arrancado
# el Servidor de Nombres. Es inmediato modificarlo para que el puerto del
# Servidor de Nombres también sea configurable
if ($#argv < 1) then
cat << end-of-help-message
   uso: $0 máquina
	  máquina    nombre de la máquina en la que corre el Servidor de Nombres
end-of-help-message
exit(-1)
endif
setenv CLASSPATH /opt/orbacus/lib/OB.jar:/opt/orbacus/lib/OBEvent.jar:/opt/orbacus/lib/OBNaming.jar:/opt/orbacus/lib/OBProperty.jar:/opt/orbacus/lib/OBTest.jar:/opt/orbacus/lib/OBUtil.jar:clases
setenv BOOTCLASSPATH -Xbootclasspath/p:$CLASSPATH
if ($#argv == 1) then
    java $BOOTCLASSPATH lsodtr.Client -ORBInitRef NameService=corbaloc:iiop:${1}:4005/NameService
endif
if ($#argv == 2) then
    java $BOOTCLASSPATH lsodtr.Client -k ${2} -ORBInitRef NameService=corbaloc:iiop:${1}:4005/NameService
endif
if ($#argv == 3) then
    if (${2} == -nk) then
        java $BOOTCLASSPATH lsodtr.Client < ${3} -ORBInitRef NameService=corbaloc:iiop:${1}:4005/NameService
    else
        java $BOOTCLASSPATH lsodtr.Client -k ${2} < ${3} -ORBInitRef NameService=corbaloc:iiop:${1}:4005/NameService
    endif
endif