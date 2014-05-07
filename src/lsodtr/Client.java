//package lsodtrp3;
//import Interface_lsodtrp3.*;
package lsodtr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
                            if(Key == null) System.out.println("No Key provided. This operation can only be done by Admin");
                            else {
                                if(strComand.length<3) System.err.println("Not enough arguments");
                                else{
                                    String designation = strComand[1].toString();
                                    for(int i = 2; i < strComand.length-1; i++) {
                                        designation = designation + " " + strComand[i].toString();
                                    }
                                    if(designation.length() > 30) System.err.println("FORMAT ERROR > 30 characters");
                                    else {
                                        try {
                                            int maximum = Integer.parseInt(strComand[strComand.length-1]);
                                            //==stub====
                                            requestNuevo request = new requestNuevo();
                                            request.designation = designation;
                                            request.maximum = maximum;
                                            request.key_client = Key;
                                            responseNuevoHolder response = new responseNuevoHolder();
                                            Interface.nuevo(request, response);
                                            int iServerError = response.value.server_error;
                                            sNuevo resultado = new sNuevo();
                                            resultado.code = response.value.answer.code;
                                            resultado.error = response.value.answer.error;
                                            //==stub====
                                            if(resultado.error!=Data.OK  || iServerError!=Data.OK) {
                                                System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                                System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado.error));
                                            }
                                            else {
                                                System.out.println("Juego creado con id " + resultado.code);
                                            }
                                        } catch (Exception e) {
                                            System.err.println("FORMAT ERROR: " + e.getMessage());
                                        }
                                    }
                                }
                            }
                        }
                        else if(method.equals("QUITA")){
                            if(Key == null) System.out.println("No Key provided. This operation can only be done by Admin");
                            else {
                                if(strComand.length<2) System.err.println("Not enough arguments");
                                else{
                                    try {
                                        short code = Short.parseShort(strComand[1].toString());
                                        //==stub====
                                        requestQuita request = new requestQuita();
                                        request.code = code;
                                        request.key_client = Key;
                                        responseIntHolder response = new responseIntHolder();
                                        Interface.quita(request, response);
                                        int iServerError = response.value.server_error;
                                        int resultado = response.value.server_error;
                                        //==stub====
                                        if(resultado!=Data.OK  || iServerError!=Data.OK) {
                                            System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                            System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado));
                                        }
                                        else {
                                            System.out.println("Juego eliminado");
                                        }
                                    } catch (Exception e) {
                                        System.err.println("FORMAT ERROR: " + e.getMessage());
                                    }
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
                                if(name.length() > 48) System.err.println("FORMAT ERROR > 48 characters");
                                else {
                                    String alias = strComand[strComand.length-1].toString();
                                    if(alias.length() > 8) System.err.println("FORMAT ERROR > 8 characters");
                                    else {
                                        //==stub====
                                        requestInscribe request = new requestInscribe();
                                        request.name = name;
                                        request.alias = alias;
                                        responseIntHolder response = new responseIntHolder();
                                        Interface.inscribe(request, response);
                                        int iServerError = response.value.server_error;
                                        int resultado = response.value.server_error;
                                        //==stub====
                                        if (resultado != Data.OK || iServerError != Data.OK) {
                                            System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                            System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado));
                                        } else {
                                            System.out.println("Inscrito");
                                        }
                                    }
                                }
                            }
                        }
                        else if(method.equals("PLANTILLA")){
                            if(Key == null) System.out.println("No Key provided. This operation can only be done by Admin");
                            else{
                                try {
                                    //==stub====
                                    requestPlantilla request = new requestPlantilla();
                                    request.key_client = Key;
                                    responsePlantillaHolder response = new responsePlantillaHolder();
                                    Interface.plantilla(request, response);
                                    int iServerError = response.value.server_error;

                                    List<Jugador> resultado = new ArrayList<Jugador>();
                                    for(int ii=0; ii<response.value.answer.length; ii++){
                                        Jugador player = new Jugador();
                                        player.alias = response.value.answer[ii].alias;
                                        player.name = response.value.answer[ii].name;
                                        resultado.add(player);
                                    }

                                    //==stub====
                                    if(iServerError!=Data.OK) {
                                        System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    }
                                    else {
                                        ListIterator<Jugador> it = resultado.listIterator();
                                        for(int ii=0; ii<resultado.size(); ii++) {
                                            Jugador player = it.next();
                                            System.out.println(player.name + " (" + player.alias + ").");
                                        }
                                    }
                                } catch (Exception e) {
                                    System.err.println("FORMAT ERROR: " + e.getMessage());
                                }
                            }
                        }
                        else if(method.equals("REPERTORIO")){
                            if(strComand.length<2) System.err.println("Not enough arguments");
                            else{
                                try {
                                    byte minimum = Byte.parseByte(strComand[1].toString());
                                    //==stub====
                                    requestRepertorio request = new requestRepertorio();
                                    request.minimum = minimum;
                                    responseRepertorioHolder response = new responseRepertorioHolder();
                                    Interface.repertorio(request, response);
                                    int iServerError = response.value.server_error;

                                    List<Juego> resultado = new ArrayList<Juego>();
                                    for(int ii=0; ii<response.value.answer.length; ii++){
                                        Juego game = new Juego();
                                        game.code = response.value.answer[ii].code;
                                        game.designation = response.value.answer[ii].designation;
                                        game.maximum = response.value.answer[ii].maximum;
                                        resultado.add(game);
                                    }

                                    //==stub====
                                    if(iServerError!=Data.OK) {
                                        System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    }
                                    else {
                                        ListIterator<Juego> it = resultado.listIterator();
                                        for(int ii=0; ii<resultado.size(); ii++) {
                                            Juego game = it.next();
                                            System.out.println(game.designation + " (" + game.code + "): Max=" + game.maximum + ".");
                                        }
                                    }
                                } catch (Exception e) {
                                    System.err.println("FORMAT ERROR: " + e.getMessage());
                                }
                            }
                        }
                        else if(method.equals("JUEGA")){
                            if(strComand.length<3) System.err.println("Not enough arguments");
                            else{
                                try {
                                    String alias = strComand[1].toString();
                                    short code = Short.parseShort(strComand[2].toString());
                                    //==stub====
                                    requestJuega request = new requestJuega();
                                    request.alias = alias;
                                    request.code = code;
                                    responseIntHolder response = new responseIntHolder();
                                    Interface.juega(request, response);
                                    int iServerError = response.value.server_error;
                                    int resultado = response.value.server_error;
                                    //==stub====
                                    if (resultado != Data.OK || iServerError != Data.OK) {
                                        System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                        System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado));
                                    } else {
                                        System.out.println("jugando");
                                    }
                                } catch (Exception e) {
                                    System.err.println("FORMAT ERROR: " + e.getMessage());
                                }
                            }
                        }
                        else if(method.equals("TERMINA")){
                            if(strComand.length<3) System.err.println("Not enough arguments");
                            else{
                                try {
                                    String alias = strComand[1].toString();
                                    short code = Short.parseShort(strComand[2].toString());
                                    requestTermina request = new requestTermina();
                                    request.alias = alias;
                                    request.code = code;
                                    responseIntHolder response = new responseIntHolder();
                                    Interface.termina(request, response);
                                    int iServerError = response.value.server_error;
                                    int resultado = response.value.server_error;
                                    //==stub====
                                    if (resultado != Data.OK || iServerError != Data.OK) {
                                        System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                        System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado));
                                    } else {
                                        System.out.println("Desconectado");
                                    }
                                } catch (Exception e) {
                                    System.err.println("FORMAT ERROR: " + e.getMessage());
                                }
                            }
                        }
                        else if(method.equals("LISTA")){
                            if(strComand.length<2) System.err.println("Not enough arguments");
                            else{
                                try {
                                    short code = Short.parseShort(strComand[1].toString());
                                    //==stub====
                                    requestLista request = new requestLista();
                                    request.code = code;
                                    responseListaHolder response = new responseListaHolder();
                                    Interface.lista(request, response);
                                    int iServerError = response.value.server_error;
                                    sLista resultado = new sLista();
                                    resultado.error = response.value.answer.error;

                                    List<Jugador> players = new ArrayList<Jugador>();
                                    for(int ii=0; ii<response.value.answer.lista.length; ii++){
                                        Jugador player = new Jugador();
                                        player.alias = response.value.answer.lista[ii].alias;
                                        player.name = response.value.answer.lista[ii].name;
                                        players.add(player);
                                    }
                                    resultado.lista = players;

                                    //==stub====
                                    if(resultado.error!=Data.OK  || iServerError!=Data.OK) {
                                        System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                        System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado.error));
                                    }
                                    else {
                                        ListIterator<Jugador> it = resultado.lista.listIterator();
                                        for(int ii=0; ii<resultado.lista.size(); ii++) {
                                            Jugador player = it.next();
                                            System.out.println(player.name + " (" + player.alias + ").");
                                        }
                                    }
                                } catch (Exception e) {
                                    System.err.println("FORMAT ERROR: " + e.getMessage());
                                }
                            }
                        }
                        else if(method.equals("FINAL")){
                            System.out.println("FINAL");
                            break;
                        }
                        else if(method.equals("UNKNOWN")){
                            System.out.println("Comando incorrecto");
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
                Key=null;
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