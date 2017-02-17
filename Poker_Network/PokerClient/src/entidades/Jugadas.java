/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase estatica para recopilar las comprobaciones de la mano que tiene cada jugador.
 * Se va recorriendo desde la mas alta, y en cuanto detecta una se devuelve esa.
 * @author Mario Codes Sánchez
 * @since 17/02/2017
 */
public class Jugadas {
    public static int valor; //fixme: cambiar a private.
    public static String jugada;
    
    /**
     * Obtener el valor numerico de una carta. A = 14, K = 13...
     * @param carta Carta de la cual obtenemos el valor.
     * @return Valor numerico de la Carta.
     */
    private static int getValor(Carta carta) {
        int valor = -1;
        String v = carta.toString().substring(0, 1);
        
        try {
            valor = Integer.parseInt(v);
        }catch(ClassCastException|NumberFormatException ex) {
            switch(v) {
                case "A":
                    valor = 14;
                    break;
                case "K":
                    valor = 13;
                    break;
                case "Q":
                    valor = 12;
                    break;
                case "J":
                    valor = 11;
                    break;
            }
        }
        
        return valor;
    }
    
    /**
     * Obtencion del palo de la carta para comprobar escaleras, fulls y demas.
     * @param carta Carta a obtener el palo.
     * @return Palo de la carta introducida.
     */
    private static String getPalo(Carta carta) {
        return carta.toString().substring(2);
    }
    
    /**
     * Check para comprobar si tiene dos parejas independientes.
     * @param propias Cartas propias del jugador.
     * @param comunes Cartas comunes a todos.
     * @return True si dispone de doble pareja.
     */
    private static boolean checkDoblePareja(ArrayList<Carta> propias, ArrayList<Carta> comunes) {
        ArrayList<Integer> valores = getValores(propias, comunes);
        boolean parejaEncontrada = false;
        int parejaUno = 0;
        
        for (int i = 0; i < valores.size() && !parejaEncontrada; i++) { //Buscado de una primera pareja.
            int valor = valores.get(i);
            for (int j = 0; j < valores.size() && !parejaEncontrada; j++) {
                int valor2 = valores.get(j);
                if(j != i) {
                    if(valor == valor2) {
                        parejaUno += valor+valor2;
                        parejaEncontrada = true;
                    }
                }
            }
        }
        
        if(parejaUno == 0) return false; //No hace falta que siga buscando.

        for (int i = 0; i < valores.size(); i++) { //Segunda pareja, que no sea la primera.
            int valor = valores.get(i);
            for (int j = 0; j < valores.size(); j++) {
                int valor2 = valores.get(j);
                if(j != i) {
                    if(valor == valor2 && ((valor+valor2) != parejaUno)) {
                        Jugadas.valor += (valor+valor2+parejaUno);
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * Comprobacion de si el jugador dispone de una pareja simple. (2 cartas iguales).
     * @param propias Cartas propias del jugador.
     * @param comunes Cartas comunes a todos.
     * @return True si dispone de pareja.
     */
    public static boolean checkPareja(ArrayList<Carta> propias, ArrayList<Carta> comunes) {
        ArrayList<Integer> valores = getValores(propias, comunes);
        
        for (int i = 0; i < valores.size(); i++) {
            int valor = valores.get(i);
            for (int j = 0; j < valores.size(); j++) {
                int valor2 = valores.get(j);
                if(j != i) {
                    if(valor == valor2) {
                        Jugadas.valor = valor+valor2;
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * Comprobacion de cual es la carta mas alta.
     * @param propias Cartas propias del jugador.
     * @param comunes Cartas comunes a todos.
     * @return True siempre ya que es la minima combinacion posible.
     */
    private static boolean checkCartaAlta(ArrayList<Carta> propias, ArrayList<Carta> comunes) {
        int maxValor = getValor(propias.get(0));
        if(getValor(propias.get(1)) > maxValor) maxValor = getValor(propias.get(1));
        else {
            for(Carta c: comunes) {
                if(getValor(c) > maxValor) maxValor = getValor(c);
            }
        }
        
        Jugadas.valor = maxValor;
        return true;
    }
    
    /**
     * Mezcla de todas las cartas para chequear combinaciones posibles en una unica ArrayList.
     * @param propias Cartas propias del Jugador.
     * @param comunes Cartas comunes a todos.
     * @return ArrayList conjunta a todos los jugadores.
     */
    private static ArrayList<Integer> getValores(ArrayList<Carta> propias, ArrayList<Carta> comunes) {
        ArrayList<Integer> valores = new ArrayList<>();
        for(Carta c: propias) valores.add(getValor(c));
        for(Carta c: comunes) valores.add(getValor(c));
        valores.sort(Comparator.naturalOrder());
        return valores;
    }
    
    /**
     * Comprobacion de la jugada posible que tiene un jugador, dado el conjunto de cartas.
     * Modifica la String para saber la jugada, y el valor por si hay empate de jugadas.
     * @param propias Cartas propias. Siempre son 2.
     * @param comunes Cartas comunes a todos, pueden ser 3, 4 o 5.
     */
    public static void checkJugada(ArrayList<Carta> propias, ArrayList<Carta> comunes) {
        if(checkDoblePareja(propias, comunes)) Jugadas.jugada = "Doble Pareja";
        else {
            if(checkPareja(propias, comunes)) Jugadas.jugada = "Pareja";
            else {
                if(checkCartaAlta(propias, comunes)) Jugadas.jugada = "Carta Alta";
            }
        }
    }
}