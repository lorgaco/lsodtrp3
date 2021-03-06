//package lsodtrp3;
//import Interface_lsodtrp3.*;
package lsodtr;

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

            ServantCORBA servant = new ServantCORBA(thePOA, Key);

            org.omg.CORBA.Object coServant = thePOA.servant_to_reference(servant);

            NamingContext inicContext = NamingContextHelper.narrow(
                    theORB.resolve_initial_references("NameService"));

            NameComponent nc = new NameComponent("Servidor-lsodtrp3", "");
            NameComponent name[] = { nc };
            inicContext.rebind(name, coServant);

            System.out.println("Servidor registrado");

            theORB.run();

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

        if(args.length<1) {
            System.err.println("Not enough arguments");
            return;
        }
        else{
            try {
                if(args.length>=4) {
                    if(args[0].equals("-k") || args[0].equals("-K")) {
                        Key=args[1];
                        System.out.println("Admin key: " + Key);
                        String[] args_aux = new String[2];
                        args_aux[0] = args[2];
                        args_aux[1] = args[3];
                        args = new String[2];
                        args = args_aux;
                    }
                }

                try {
                    java.util.Properties props = System.getProperties();
                    props.put("org.omg.CORBA.ORBClass", "com.ooc.CORBA.ORB");
                    props.put("org.omg.CORBA.ORBSingletonClass",
                            "com.ooc.CORBA.ORBSingleton");
                    org.omg.CORBA.ORB theORB = org.omg.CORBA.ORB.init(args, props);
                    System.out.println("Entrando");
                    run(theORB);

                    if(theORB != null) theORB.destroy();

                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("Excepción: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}