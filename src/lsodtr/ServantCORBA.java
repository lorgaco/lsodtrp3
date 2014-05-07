//package lsodtrp3;
//import Interface_lsodtrp3.*;
package lsodtr;

import java.util.*;
import org.omg.PortableServer.*;

public class ServantCORBA extends lsodtrp3POA {

    private POA thePOA;
    private String key_server;
    private Methods Method;

    ServantCORBA(POA poa, String Key) {
        thePOA = poa;
        key_server = Key;
        Method = new Methods();
    }

    public void nuevo(requestNuevo request, responseNuevoHolder response) {
        if(request.key_client.equals(key_server)) {
            sNuevo out = Method.nuevo(request.designation, request.maximum);
            responseNuevo aux_response = new responseNuevo();
            aux_response.answer = new sNuevoStruct();
            aux_response.answer.code = out.code;
            aux_response.answer.error = out.error;
            aux_response.server_error = Data.OK;
            response.value = aux_response;
        }
        else {
            responseNuevo aux_response = new responseNuevo();
            aux_response.answer = new sNuevoStruct();
            aux_response.answer.error = Data.SERVER_ERROR;
            aux_response.answer.code = -1;
            aux_response.server_error = Data.AUTENTICATION_FAILED;
            response.value = aux_response;
        }
    }
    public void quita(requestQuita request, responseIntHolder response) {
        if(request.key_client.equals(key_server)) {
            int out = Method.quita(request.code);
            responseInt aux_response = new responseInt();
            aux_response.answer = out;
            aux_response.server_error = Data.OK;
            response.value = aux_response;
        }
        else {
            responseInt aux_response = new responseInt();
            aux_response.answer = -1;
            aux_response.server_error = Data.AUTENTICATION_FAILED;
            response.value = aux_response;
        }
    }
    public void inscribe(requestInscribe request, responseIntHolder response) {
        int out = Method.inscribe(request.name, request.alias);
        responseInt aux_response = new responseInt();
        aux_response.answer = out;
        aux_response.server_error = Data.OK;
        response.value = aux_response;
    }
    public void plantilla(requestPlantilla request, responsePlantillaHolder response) {
        if(request.key_client.equals(key_server)) {
            List<Jugador> out = Method.plantilla();
            responsePlantilla aux_response = new responsePlantilla();
            aux_response.server_error = Data.OK;

            aux_response.answer = new JugadorStruct[out.size()];
            ListIterator<Jugador> it = out.listIterator();
            for(int ii=0; ii<out.size(); ii++) {
                Jugador player = it.next();
                aux_response.answer[ii].alias = player.alias;
                aux_response.answer[ii].alias = player.name;
            }

            response.value = aux_response;
        }
        else {
            responsePlantilla aux_response = new responsePlantilla();
            aux_response.answer = new JugadorStruct[0];
            aux_response.server_error = Data.AUTENTICATION_FAILED;
            response.value = aux_response;
        }
    }
    public void repertorio(requestRepertorio request, responseRepertorioHolder response) {
        List<Juego> out = Method.repertorio(request.minimum);
        responseRepertorio aux_response = new responseRepertorio();
        aux_response.server_error = Data.OK;

        JuegoStruct[] games = new JuegoStruct[out.size()];
        ListIterator<Juego> it = out.listIterator();
        for(int ii=0; ii<out.size(); ii++) {
            Juego game = it.next();
            games[ii].code = game.code;
            games[ii].designation = game.designation;
            games[ii].maximum = game.maximum;
        }
        response.value.answer = games;
        response.value = aux_response;
    }
    public void juega(requestJuega request, responseIntHolder response) {
        int out = Method.juega(request.alias, request.code);
        responseInt aux_response = new responseInt();
        aux_response.answer = out;
        aux_response.server_error = Data.OK;
        response.value = aux_response;
    }
    public void termina(requestTermina request, responseIntHolder response) {
        int out = Method.termina(request.alias, request.code);
        responseInt aux_response = new responseInt();
        aux_response.answer = out;
        aux_response.server_error = Data.OK;
        response.value = aux_response;
    }
    public void lista(requestLista request, responseListaHolder response) {
        sLista out = Method.lista(request.code);
        responseLista aux_response = new responseLista();
        aux_response.server_error = Data.OK;
        aux_response.answer = new sListaStruct();
        aux_response.answer.error = out.error;
        aux_response.answer.lista = new JugadorStruct[out.lista.size()];
        ListIterator<Jugador> it = out.lista.listIterator();
        for(int ii=0; ii<out.lista.size(); ii++) {
            Jugador player = it.next();
            aux_response.answer.lista[ii].alias = player.alias;
            aux_response.answer.lista[ii].alias = player.name;
        }

        response.value = aux_response;
    }

    public org.omg.PortableServer.POA _default_POA() {
        return thePOA;
    }
}