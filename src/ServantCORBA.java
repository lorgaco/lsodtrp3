package lsodtrp3;

import java.util.*;
import org.omg.PortableServer.*;

public class ServantCORBA extends lsodtrp3POA {

    private POA thePOA;
    private String key_server;
    private Methods Method;

    ServantCORBA(POA poa, String Key) {
        thePOA = poa;
        Key_server = Key;
        Method = new Methods();
    }

    void nuevo(requestNuevo request, AnswerStruct response) {
        if(request.value.key_client.equals(key_server)) {
            Answer answer = Method.nuevo(request.value.designation, request.value.maximum);
            response.value.answer = answer.getAnswer();
            response.value.error = answer.getError();
            response.value.server_error = answer.getServer_error();
        }
        else {
            response.value.answer = "";
            response.value.error = Data.OK;
            response.value.server_error = Data.AUTENTICATION_FAILED;
        }
    }
    void quita(requestQuita request, AnswerStruct response) {
        if(request.value.key_client.equals(key_server)) {
            Answer answer = Method.quita(request.value.code);
            response.value.answer = answer.getAnswer();
            response.value.error = answer.getError();
            response.value.server_error = answer.getServer_error();
        }
        else {
            response.value.answer = "";
            response.value.error = Data.OK;
            response.value.server_error = Data.AUTENTICATION_FAILED;
        }
    }
    void inscribe(requestInscribe request, AnswerStruct response) {
        Answer answer = Method.inscribe(request.value.name, request.value.alias);
        response.value.answer = answer.getAnswer();
        response.value.error = answer.getError();
        response.value.server_error = answer.getServer_error();
    }
    void plantilla(requestPlantilla request, AnswerStruct response) {
        if(request.value.key_client.equals(key_server)) {
            Answer answer = Method.plantilla();
            response.value.answer = answer.getAnswer();
            response.value.error = answer.getError();
            response.value.server_error = answer.getServer_error();
        }
        else {
            response.value.answer = "";
            response.value.error = Data.OK;
            response.value.server_error = Data.AUTENTICATION_FAILED;
        }
    }
    void repertorio(requestRepertorio request, AnswerStruct response) {
        Answer answer = Method.repertorio(request.value.minimum);
        response.value.answer = answer.getAnswer();
        response.value.error = answer.getError();
        response.value.server_error = answer.getServer_error();
    }
    void juega(requestJuega request, AnswerStruct response) {
        Answer answer = Method.juega(request.value.alias, request.value.code);
        response.value.answer = answer.getAnswer();
        response.value.error = answer.getError();
        response.value.server_error = answer.getServer_error();
    }
    void termina(requestTermina request, AnswerStruct response) {
        Answer answer = Method.termina(request.value.alias, request.value.code);
        response.value.answer = answer.getAnswer();
        response.value.error = answer.getError();
        response.value.server_error = answer.getServer_error();
    }
    void lista(requestLista request, AnswerStruct response) {
        Answer answer = Method.lista(request.value.code);
        response.value.answer = answer.getAnswer();
        response.value.error = answer.getError();
        response.value.server_error = answer.getServer_error();
    }

    public org.omg.PortableServer.POA _default_POA() {
        return thePOA;
    }
}