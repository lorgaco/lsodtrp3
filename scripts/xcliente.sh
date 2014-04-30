#! /bin/csh -f
#
cp policy.all ~/.java.policy
setenv CLASSPATH /opt/orbacus/lib/OB.jar:/opt/orbacus/lib/OBEvent.jar:/opt/orbacus/lib/OBNaming.jar:/opt/orbacus/lib/OBProperty.jar:/opt/orbacus/lib/OBTest.jar:/opt/orbacus/lib/OBUtil.jar:clases
setenv BOOTCLASSPATH -Xbootclasspath/p:$CLASSPATH
appletviewer -J$BOOTCLASSPATH Eco.html
rm -f ~/.java.policy
