# **********************************************************************
#
# Basado en los ejemplos de ORBacus
#
# **********************************************************************

top_srcdir	= /opt/orbacus
ORBACUSCP	= $(top_srcdir)/lib/OB.jar:$(top_srcdir)/lib/OBEvent.jar:$(top_srcdir)/lib/OBNaming.jar:$(top_srcdir)/lib/OBProperty.jar:$(top_srcdir)/lib/OBTest.jar:$(top_srcdir)/lib/OBUtil.jar
JAVAC		= javac
JIDL		= $(top_srcdir)/bin/jidl

all:: clases generado
	${JIDL} -I. --all --output-dir generado ./src/InterfaceCORBA.idl
	CLASSPATH=.:generado:${ORBACUSCP}:$$CLASSPATH \
	${JAVAC} -deprecation -d clases ./src/*.java generado/lsodtr/*.java

clases:
	mkdir clases

generado:
	mkdir generado

clean::
	rm -f *.bak
	rm -f core
	rm -f Eco.html
	rm -rf clases
	rm -rf generado
