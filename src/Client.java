//package lsodtrp3;
//import Interface_lsodtrp3.*;
package lsodtr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.omg.CosNaming.*;

public class Client {

    private static final long serialVersionUID = 3;
    static String Key = null;

    /**
     * Contiene el código que ejecuta el cliente
     **/
    static int run(org.omg.CORBA.ORB theORB, String[] args) throws org.omg.CORBA.UserException {
        try {

            NamingContext inicContext = NamingContextExtHelper.narrow(
                    theORB.resolve_initial_references("NameService"));

            NameComponent nc = new NameComponent("Servidor-lsodtrp3", "");
            NameComponent nameComponent[] = { nc };

            lsodtr.lsodtrp3 Interface = lsodtr.lsodtrp3Helper.narrow(
                    inicContext.resolve(nameComponent));


            BufferedReader brComand = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                System.out.println("Escriba comando:");
                try {
                    String strComand[] = brComand.readLine().split(" ");
                    if(strComand.length<1){
                        System.err.println("Not enough arguments");
                        break;
                    }
                    else{
                        String method = Data.PromptToMethod(strComand[0].toString());
                        if(method.equals("NUEVO")){
                            if(strComand.length<3) System.err.println("Not enough arguments");
                            else{
                                String designation = strComand[1].toString();
                                for(int i = 2; i < strComand.length-1; i++) {
                                    designation = designation + " " + strComand[i].toString();
                                }
                                int maximum = Integer.parseInt(strComand[strComand.length-1]);
                                requestNuevo request = new requestNuevo();
                                request.designation = designation;
                                request.maximum = maximum;
                                request.key_client = Key;
                                System.out.println("request.designation: " + request.designation + " / designation: " + designation);
                                System.out.println("request.maximum: " + request.maximum + " / maximum: " + maximum);
                                System.out.println("request.key_client: " + request.key_client + " / Key: " + Key);
                                AnswerStructHolder response = new AnswerStructHolder();
                                Interface.nuevo(request, response);
                                int iError = response.value.error;
                                int iServerError = response.value.server_error;
                                String sResponse = response.value.answer;
                                if(iError!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                                }
                                else {
                                    System.out.println("Juego creado con id " + sResponse);
                                }
                            }
                        }
                        else if(method.equals("QUITA")){
                            if(strComand.length<2) System.err.println("Not enough arguments");
                            else{
                                short code = Short.parseShort(strComand[1].toString());
                                requestQuita request = new requestQuita();
                                request.code = code;
                                request.key_client = Key;
                                AnswerStructHolder response = new AnswerStructHolder();
                                Interface.quita(request, response);
                                int iError = response.value.error;
                                int iServerError = response.value.server_error;
                                if(iError!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                                }
                                else {
                                    System.out.println("Juego eliminado");
                                }
                            }
                        }
                        else if(method.equals("INSCRIBE")){
                            if(strComand.length<3) System.err.println("Not enough arguments");
                            else{
                                String name = strComand[1].toString();
                                for(int i = 2; i < strComand.length-1; i++) {
                                    name = name + " " + strComand[i].toString();
                                }
                                String alias = strComand[strComand.length-1].toString();
                                requestInscribe request = new requestInscribe();
                                request.name = name;
                                request.alias = alias;
                                AnswerStructHolder response = new AnswerStructHolder();
                                Interface.inscribe(request, response);
                                int iError = response.value.error;
                                int iServerError = response.value.server_error;
                                if(iError!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                                }
                                else {
                                    System.out.println("Inscrito");
                                }
                            }
                        }
                        else if(method.equals("PLANTILLA")){
                            requestPlantilla request = new requestPlantilla();
                            request.key_client = Key;
                            AnswerStructHolder response = new AnswerStructHolder();
                            Interface.plantilla(request, response);
                            int iError = response.value.error;
                            int iServerError = response.value.server_error;
                            String sResponse = response.value.answer;
                            if(iError!=Data.OK  || iServerError!=Data.OK) {
                                System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                            }
                            else {
                                String[] aResponse = sResponse.split("(, )|\\]|\\[");
                                for(int ii=0; ii<aResponse.length; ii++){
                                    System.out.println(aResponse[ii]);
                                }
                            }
                        }
                        else if(method.equals("REPERTORIO")){
                            if(strComand.length<2) System.err.println("Not enough arguments");
                            else{
                                byte minimum = Byte.parseByte(strComand[1].toString());
                                requestRepertorio request = new requestRepertorio();
                                request.minimum = minimum;
                                AnswerStructHolder response = new AnswerStructHolder();
                                Interface.repertorio(request, response);
                                int iError = response.value.error;
                                int iServerError = response.value.server_error;
                                String sResponse = response.value.answer;
                                if(iError!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                                }
                                else {
                                    String[] aResponse = sResponse.split("(, )|\\]|\\[");
                                    for(int ii=0; ii<aResponse.length; ii++){
                                        System.out.println(aResponse[ii]);
                                    }
                                }
                            }
                        }
                        else if(method.equals("JUEGA")){
                            if(strComand.length<3) System.err.println("Not enough arguments");
                            else{
                                String alias = strComand[1].toString();
                                short code = Short.parseShort(strComand[2].toString());
                                requestJuega request = new requestJuega();
                                request.alias = alias;
                                request.code = code;
                                AnswerStructHolder response = new AnswerStructHolder();
                                Interface.juega(request, response);
                                int iError = response.value.error;
                                int iServerError = response.value.server_error;
                                if(iError!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                                }
                                else {
                                    System.out.println("jugando");
                                }
                            }
                        }
                        else if(method.equals("TERMINA")){
                            if(strComand.length<3) System.err.println("Not enough arguments");
                            else{
                                String alias = strComand[1].toString();
                                short code = Short.parseShort(strComand[2].toString());
                                requestTermina request = new requestTermina();
                                request.alias = alias;
                                request.code = code;
                                AnswerStructHolder response = new AnswerStructHolder();
                                Interface.termina(request, response);
                                int iError = response.value.error;
                                int iServerError = response.value.server_error;
                                if(iError!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                                }
                                else {
                                    System.out.println("Desconectado");
                                }
                            }
                        }
                        else if(method.equals("LISTA")){
                            if(strComand.length<2) System.err.println("Not enough arguments");
                            else{
                                short code = Short.parseShort(strComand[1].toString());
                                requestLista request = new requestLista();
                                request.code = code;
                                AnswerStructHolder response = new AnswerStructHolder();
                                Interface.lista(request, response);
                                int iError = response.value.error;
                                int iServerError = response.value.server_error;
                                String sResponse = response.value.answer;
                                if(iError!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                                }
                                else {
                                    String[] aResponse = sResponse.split("(, )|\\]|\\[");
                                    for(int ii=0; ii<aResponse.length; ii++){
                                        System.out.println(aResponse[ii]);
                                    }
                                }
                            }
                        }
                        else if(method.equals("FINAL")){
                            System.out.println("FINAL");
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.err.println("ERROR: " + e.getMessage());
                    break;
                }
            }
        } catch (org.omg.CORBA.SystemException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        } catch (org.omg.CORBA.UserException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        return 0;
    }

    /**
     * Inicialización de la aplicación
     **/
    public static void main(String args[]) {

        if(args.length<1) {
            System.err.println("Not enough arguments");
            return;
        }
        else{
            try {
                if(args.length>4) {
                    if(args[1].equals("-k") || args[1].equals("-K")) {
                        Key=args[2];
                        System.out.println("Admin key: " + Key);
                        String[] args_aux = new String[3];
                        args_aux[0] = args[0];
                        args_aux[1] = args[3];
                        args_aux[2] = args[4];
                        args = new String[3];
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
                    run(theORB, args);

                    if(theORB != null) theORB.destroy();

                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            } catch(Exception e) {
                System.err.println("Excepción de Sistema: " + e);
            }
        }
    }
}