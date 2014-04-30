import java.util.*;
import org.omg.PortableServer.*;

public class ServantCORBA extends EcoPOA {

    private POA thePOA;
    private String Key_server;
    private Methods Method;

    ServantCORBA(POA poa, Key) {
        thePOA = poa;
        Key_server = Key;
        Method = new Methods();
    }

    void nuevo(in requestNuevo request, out AnswerStruct response) {
        if(request.value.key_client.equals(key_server)) {
            Answer answer = Method.nuevo(request.value.designation, request.value.maximum);
            response.value.answer = answer.getAnswer();
            response.value.error = answer.getError();
            response.value.server_error = answer.getServer_error();
        }
        else {
            response.value.answer = "";
            response.value.error = Data.OK;
            response.value.server_error = Data.AUTENTICATION_FAILED);
        }
    }
    void quita(in requestQuita request, out AnswerStruct response) {
        if(request.value.key_client.equals(key_server)) {
            Answer answer = Method.quita(request.value.code);
            response.value.answer = answer.getAnswer();
            response.value.error = answer.getError();
            response.value.server_error = answer.getServer_error();
        }
        else {
            response.value.answer = "";
            response.value.error = Data.OK;
            response.value.server_error = Data.AUTENTICATION_FAILED);
        }
    }
    void inscribe(in requestInscribe request, out AnswerStruct response) {
        Answer answer = Method.inscribe(request.value.name, request.value.alias);
        response.value.answer = answer.getAnswer();
        response.value.error = answer.getError();
        response.value.server_error = answer.getServer_error();
    }
    void plantilla(in requestPlantilla request, out AnswerStruct response) {
        if(request.value.key_client.equals(key_server)) {
            Answer answer = Method.plantilla();
            response.value.answer = answer.getAnswer();
            response.value.error = answer.getError();
            response.value.server_error = answer.getServer_error();
        }
        else {
            response.value.answer = "";
            response.value.error = Data.OK;
            response.value.server_error = Data.AUTENTICATION_FAILED);
        }
    }
    void repertorio(in requestRepertorio request, out AnswerStruct response) {
        Answer answer = Method.repertorio(request.value.minimum);
        response.value.answer = answer.getAnswer();
        response.value.error = answer.getError();
        response.value.server_error = answer.getServer_error();
    }
    void juega(in requestJuega request, out AnswerStruct response) {
        Answer answer = Method.juega(request.value.alias, request.value.code);
        response.value.answer = answer.getAnswer();
        response.value.error = answer.getError();
        response.value.server_error = answer.getServer_error();
    }
    void termina(in requestTermina request, out AnswerStruct response) {
        Answer answer = Method.termina(request.value.alias, request.value.code);
        response.value.answer = answer.getAnswer();
        response.value.error = answer.getError();
        response.value.server_error = answer.getServer_error();
    }
    void lista(in requestLista request, out AnswerStruct response) {
        Answer answer = Method.lista(request.value.code);
        response.value.answer = answer.getAnswer();
        response.value.error = answer.getError();
        response.value.server_error = answer.getServer_error();
    }

    public org.omg.PortableServer.POA _default_POA() {
        return elPOA;
    }
}