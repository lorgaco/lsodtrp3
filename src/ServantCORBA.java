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

    void nuevo(requestNuevoHolder request, AnswerStructHolder response) {
        if(request.value.key_client.equals(key_server)) {
            Answer answer = Method.nuevo(request.value.designation, request.value.maximum);
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
    void quita(requestQuitaHolder request, AnswerStructHolder response) {
        if(request.value.key_client.equals(key_server)) {
            Answer answer = Method.quita(request.value.code);
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
    void inscribe(requestInscribeHolder request, AnswerStructHolder response) {
        Answer answer = Method.inscribe(request.value.name, request.value.alias);
        AnswerStruct aux_response = new AnswerStruct();
        aux_response.answer = answer.getAnswer();
        aux_response.error = answer.getError();
        aux_response.server_error = answer.getServer_error();
        response.value = aux_response;
    }
    void plantilla(requestPlantillaHolder request, AnswerStructHolder response) {
        if(request.value.key_client.equals(key_server)) {
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
    void repertorio(requestRepertorioHolder request, AnswerStructHolder response) {
        Answer answer = Method.repertorio(request.value.minimum);
        AnswerStruct aux_response = new AnswerStruct();
        aux_response.answer = answer.getAnswer();
        aux_response.error = answer.getError();
        aux_response.server_error = answer.getServer_error();
        response.value = aux_response;
    }
    void juega(requestJuegaHolder request, AnswerStructHolder response) {
        Answer answer = Method.juega(request.value.alias, request.value.code);
        AnswerStruct aux_response = new AnswerStruct();
        aux_response.answer = answer.getAnswer();
        aux_response.error = answer.getError();
        aux_response.server_error = answer.getServer_error();
        response.value = aux_response;
    }
    void lista(requestListaHolder request, AnswerStructHolder response) {
        Answer answer = Method.lista(request.value.code);
        AnswerStruct aux_response = new AnswerStruct();
        aux_response.answer = answer.getAnswer();
        aux_response.error = answer.getError();
        aux_response.server_error = answer.getServer_error();
        response.value = aux_response;
    }
    void termina(requestTerminaHolder request, AnswerStructHolder response) {
        Answer answer = Method.termina(request.value.alias, request.value.code);
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