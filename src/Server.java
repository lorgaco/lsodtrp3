package lsodtrp3;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

final public class Server {

    static String Key;

    static int run(org.omg.CORBA.ORB theORB)
            throws org.omg.CORBA.UserException {

        try {

            POA thePOA = POAHelper.narrow(
                    theORB.resolve_initial_references("RootPOA"));

            thePOA.the_POAManager().activate();

            CORBAServant servant = new CORBAServant(thePOA, Key);

            org.omg.CORBA.Object coServant = thePOA.servant_to_reference(servant);

            NamingContext inicContext = NamingContextHelper.narrow(
                    theORB.resolve_initial_references("NameService"));

            NameComponent nc = new NameComponent("Servidor-lsodtrp3", "");
            NameComponent name[] = { nc };
            inicContext.rebind(name, coServant);

            theORB.run();

        } catch(java.io.IOException ex) {
            System.err.println("Servidor de Eco: no puedo escribir en ‘" +
                    ex.getMessage() + "’");
            return 1;
        } catch (org.omg.CORBA.SystemException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        } catch (org.omg.CORBA.UserException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        } catch (java.lang.Exception exb) {
            System.err.println("Excepción " + exb);
            System.exit(1);
        }
        return 0;
    }

    public static void main(String args[]) {

        System.setSecurityManager(new RMISecurityManager());
        if(args.length<1) {
            System.err.println("Not enough arguments");
            return;
        }
        else{
            try {
                if(args.length>2) {
                    if(args[1].equals("-k") || args[1].equals("-K")) {
                        Key = args[2];
                        System.out.println("Admin key: " + Key);
                    }
                }

                try {
                    java.util.Properties props = System.getProperties();
                    props.put("org.omg.CORBA.ORBClass", "com.ooc.CORBA.ORB");
                    props.put("org.omg.CORBA.ORBSingletonClass",
                            "com.ooc.CORBA.ORBSingleton");
                    org.omg.CORBA.ORB theORB = org.omg.CORBA.ORB.init(args, props);

                    status = run(theORB);

                    if(theORB != null) theORB.destroy();

                } catch(Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Servidor listo.");
            } catch (Exception e) {
                System.out.println("Excepción: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}