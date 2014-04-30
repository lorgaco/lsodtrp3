import java.util.*;
import org.omg.PortableServer.*;
/**
 * Sirviente en Java de la interfaz de Eco.
 * Implementación de los métodos de servicio de Eco.
 * Utiliza el POA y el Servidor de Nombres
 *
 * @author Pedro S. Rodríguez Hernández
(basado en ejemplos de ORBacus)
 *
 @version
 3.0
 *
 **/
public class EcoSirviente extends EcoPOA {
    /**
     * El POA por defecto del Sirviente
     **/
    private POA elPOA;
    /**
     * El constructor del sirviente
     *
     * @param poa El POA que usará el Sirviente por defecto
     **/
    EcoSirviente(POA poa) {
        elPOA = poa;
    }
    /**
     * Método remoto eco_1: hace el eco en un argumento de salida,
     * que es un String
     *
     * @param entrada La cadena recibida
     * @param salida La cadena devuelta
     **/
    public void eco_1 (String entrada, org.omg.CORBA.StringHolder salida) {
        System.out.println("Recibido: " + entrada);
        salida.value = entrada;
        return;
    }
    /**
     * Método remoto eco_2: hace el eco en un argumento de salida,
     * que es una struct de CORBA (en Java, un objeto de la clase
     * laStruct)
     *
     * @param entrada La cadena recibida
     * @param salida La struct devuelta
     **/
    public void eco_2 (String entrada, laStructHolder salida) {
        System.out.println("Recibido: " + entrada);
        laStruct unaStruct = new laStruct();
        unaStruct.corto = (short)1;
        // Basura, es por decir algo
        unaStruct.largo = 10;
        // Basura, es por decir algo
        unaStruct.cadena = entrada;
        // La cadena recibida
        //Una opción más compacta es usar el constructor con
        //argumentos que ha generado el jidl, y sustituir las 4 líneas
        //anteriores por:
        //laStruct unaStruct = new laStruct((short)1, 10, entrada);
        salida.value = unaStruct;
        return;
    }
    /**
     * Método remoto eco_3: hace el eco en un argumento de salida,
     * que es una sequence de CORBA (en Java, un array de objetos
     * de la clase laStruct)
     *
     * @param entrada La cadena recibida
     * @param salida La sequence devuelta
     **/
    public void eco_3 (String entrada, laSeqHolder salida) {
        Vector<laStruct> elVector = new Vector<laStruct>();
        System.out.println("Recibido: " + entrada);
        // El vector tiene 2 elementos (por la cara). En un caso real,
        // tendrá los que dicte la aplicación...
        for (int i=0; i<2; i++) {
            laStruct unaStruct = new laStruct();
            unaStruct.corto = (short)(i+1);
            // Basura, es por decir algo
            unaStruct.largo = (i+1)*10;
            // Basura, es por decir algo
            unaStruct.cadena = entrada;
            // La cadena recibida
            //Una opción más compacta es usar el constructor con
            //argumentos que ha generado el jidl, y sustituir las 4 líneas
            //anteriores por:
            //laStruct unaStruct = new laStruct((short)(i+1), (i+1)*10, entrada);
            elVector.addElement(unaStruct);
        }
        salida.value = new laStruct[elVector.size()];
        elVector.copyInto(salida.value);
        return;
    }
    /**
     * Método remoto eco_4: hace el eco en el resultado, que es
     * una sequence de CORBA (en Java, un array de objetos de la
     * clase laStruct)
     *
     * @param entrada La cadena recibida
     * @return Una sequence de CORBA
     **/
     public laStruct[] eco_4 (String entrada) {
     Vector<laStruct> elVector = new Vector<laStruct>();
     laStruct[] resultado;
     System.out.println("Recibido: " + entrada);
     // El vector tiene 2 elementos (por la cara). En un caso real,
     // tendrá los que dicte la aplicación...
     for (int i=0; i<2; i++) {
     laStruct unaStruct = new laStruct();
     unaStruct.corto = (short)(i+1);
     // Basura, es por decir algo
     unaStruct.largo = (i+1)*10;
     // Basura, es por decir algo
     unaStruct.cadena = entrada;
     // La cadena recibida
     //Una opción más compacta es usar el constructor con
     //argumentos que ha generado el jidl, y sustituir las 4 líneas
     //anteriores por:
     //laStruct unaStruct = new laStruct((short)(i+1), (i+1)*10, entrada);
     elVector.addElement(unaStruct);
     }
     resultado = new laStruct[elVector.size()];
     elVector.copyInto(resultado);
     return resultado;
     }
     /**
      * Devuelve el POA por defecto del Sirviente
      *
      * @return un objeto de tipo POA
     **/
    public org.omg.PortableServer.POA _default_POA() {
        return elPOA;
    }
}