//package lsodtrp3;
//import Interface_lsodtrp3.*;
package Interface_lsodtrp3;

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

    public void nuevo(requestNuevo request, AnswerStructHolder response) {
        if(request.key_client.equals(key_server)) {
            Answer answer = Method.nuevo(request.designation, request.maximum);
            AnswerStruct aux_response = new AnswerStruct();
            aux_response.answer = answer.getAnswer();
            aux_response.error = answer.getError();
            aux_response.server_error = answer.getServer_error();
            response.value = aux_response;
        }
        else {
            AnswerStruct aux_response = new AnswerStruct();
            aux_response.answer = "";
            aux_response.error = Data.OK;
            aux_response.server_error = Data.AUTENTICATION_FAILED;
            response.value = aux_response;
        }
    }
    public void quita(requestQuita request, AnswerStructHolder response) {
        if(request.key_client.equals(key_server)) {
            Answer answer = Method.quita(request.code);
            AnswerStruct aux_response = new AnswerStruct();
            aux_response.answer = answer.getAnswer();
            aux_response.error = answer.getError();
            aux_response.server_error = answer.getServer_error();
            response.value = aux_response;
        }
        else {
            AnswerStruct aux_response = new AnswerStruct();
            aux_response.answer = "";
            aux_response.error = Data.OK;
            aux_response.server_error = Data.AUTENTICATION_FAILED;
            response.value = aux_response;
        }
    }
    public void inscribe(requestInscribe request, AnswerStructHolder response) {
        Answer answer = Method.inscribe(request.name, request.alias);
        AnswerStruct aux_response = new AnswerStruct();
        aux_response.answer = answer.getAnswer();
        aux_response.error = answer.getError();
        aux_response.server_error = answer.getServer_error();
        response.value = aux_response;
    }
    public void plantilla(requestPlantilla request, AnswerStructHolder response) {
        if(request.key_client.equals(key_server)) {
            Answer answer = Method.plantilla();
            AnswerStruct aux_response = new AnswerStruct();
            aux_response.answer = answer.getAnswer();
            aux_response.error = answer.getError();
            aux_response.server_error = answer.getServer_error();
            response.value = aux_response;
        }
        else {
            AnswerStruct aux_response = new AnswerStruct();
            aux_response.answer = "";
            aux_response.error = Data.OK;
            aux_response.server_error = Data.AUTENTICATION_FAILED;
            response.value = aux_response;
        }
    }
    public void repertorio(requestRepertorio request, AnswerStructHolder response) {
        Answer answer = Method.repertorio(request.minimum);
        AnswerStruct aux_response = new AnswerStruct();
        aux_response.answer = answer.getAnswer();
        aux_response.error = answer.getError();
        aux_response.server_error = answer.getServer_error();
        response.value = aux_response;
    }
    public void juega(requestJuega request, AnswerStructHolder response) {
        Answer answer = Method.juega(request.alias, request.code);
        AnswerStruct aux_response = new AnswerStruct();
        aux_response.answer = answer.getAnswer();
        aux_response.error = answer.getError();
        aux_response.server_error = answer.getServer_error();
        response.value = aux_response;
    }
    public void termina(requestTermina request, AnswerStructHolder response) {
        Answer answer = Method.termina(request.alias, request.code);
        AnswerStruct aux_response = new AnswerStruct();
        aux_response.answer = answer.getAnswer();
        aux_response.error = answer.getError();
        aux_response.server_error = answer.getServer_error();
        response.value = aux_response;
    }
    public void lista(requestLista request, AnswerStructHolder response) {
        Answer answer = Method.lista(request.code);
        AnswerStruct aux_response = new AnswerStruct();
        aux_response.answer = answer.getAnswer();
        aux_response.error = answer.getError();
        aux_response.server_error = answer.getServer_error();
        response.value = aux_response;
    }

    public org.omg.PortableServer.POA _default_POA() {
        return thePOA;
    }
}