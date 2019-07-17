package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedHashMap;

import static ar.edu.unlam.tallerweb1.modelo.UrlPinMapConstants.*;

public class UrlPinMapImpl implements UrlPinMap{

    private static UrlPinMap instance;

    private UrlPinMapImpl(){

    }

    public static UrlPinMap getInstance(){
        if (instance == null){
            instance = new UrlPinMapImpl();
        }

        return instance;
    }

    @Override
    public LinkedHashMap<String, String> getUrlPinMapReference(){
        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        result.put(BluePinConstant, "Tu ubicación actual"); // azul
        result.put(RedPinConstant, "Comercio con el mejor ranking"); // rojo
        result.put(GreenPinConstant, "Comercio con el producto más barato"); // verde
        result.put(GreenAndRedPinConstant, "Comercio con el producto más barato y el mejor ranking"); //rojo y verde
        result.put(VioletPinConstant, "Comercio con el producto en stock");// violeta A319FF

        return result;
    }
}
