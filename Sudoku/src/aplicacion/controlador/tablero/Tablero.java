/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.tablero;

import aplicacion.controlador.juego.GestionJuego;
import javax.swing.JTable;

/**
 * Tablero de juego. Es el conjunto de Cuadrados, Filas y Columnas, cada uno con sus Casillas correspondientes y sus propias
 *  listas de numeros disponibles.
 *  Para ver como funciona el reparto de numeros propio de cuadrados, filas y columnas, ver la representacion en Paint anexada.
 * Tambien contiene metodos estaticos para convertir una jTable en Tablero.
 * Ademas parte de el se genera repartiendo el trabajo mediante hilos. No hace falta que implemente o herede Runnable / Thread
 *      ya que lo hago mediante expresiones Lambda.
 * @author Mario Codes Sánchez
 * @since 30/12/2016
 */
public class Tablero {
    private boolean estadoGeneracionTablero = true; //Lo necesito para trabajar mediante hilos con Lambdas. Si al finalizar la generacion del tablero, es false; Se descartara y generara uno nuevo.
    private final Cuadrado[] CUADRADOS;
    private final Fila[] FILAS = new Fila[9];
    private final Columna[] COLUMNAS = new Columna[9];
    
    /**
     * Constructor para inicializar el Tablero de juego. El se encargara de inicializar lo que sea necesario.
     */
    public Tablero() {
        this.CUADRADOS = new Cuadrado[9];
        preparacionTablero();
    }
    
    /**
     * Constructor para resolver un Sudoku cualquiera que se meta.
     * @param cuadrados Cuadrados con sus casillas que hemos obtenido de la tabla correspondiente.
     */
    public Tablero(Cuadrado[] cuadrados) {
        this.CUADRADOS = cuadrados;
        preparacionTablero();
    }
    
    /**
     * Pasos necesarios para que el tablero este listo.
     */
    private void preparacionTablero() {
        inicializacionCuadrados(CUADRADOS);
        rellenoFilas();
        rellenoColumnas();
    }
    
    /**
     * Inicializacion de los Cuadrados con sus datos correspondientes en el caso de que estos esten a null (esto depende del constructor que se use).
     * Lo hago publico por que lo necesito a la hora de crear el tablero para resolucion, asi no duplico codigo.
     * @param cuadrados Inicializa el array de Cuadrado que se le pasa.
     */
    public static void inicializacionCuadrados(Cuadrado[] cuadrados) {
        if(cuadrados[0] == null) {
                                    //numCuadrado, numFila, numColumna.
            for (int i = 0, indiceFila = 0, indiceColumna = 0; i < cuadrados.length; i++) {
                cuadrados[i] = new Cuadrado(i, indiceFila, indiceColumna);

                if((i+1)%3 == 0) {
                    indiceFila += 3;
                    indiceColumna = 0;
                } else {
                    indiceColumna += 3;
                }
            }
        }
    }
    
    /**
     * Partido de la tarea en Hilos. Hecho para dividir la tarea en 3 partes.
     * @param indicePrimerCuadrado Indice del primer cuadrado con el cual trabajar. 0/3/6.
     */
    private void asignacionNumeroCasillasHilos(int indicePrimerCuadrado) {
        for (int indiceCuadrado = indicePrimerCuadrado; indiceCuadrado < indicePrimerCuadrado+3; indiceCuadrado++) {
            Casilla[] casillasCuadrado = CUADRADOS[indiceCuadrado].getCASILLAS();
            for(int indiceCasilla = 0; indiceCasilla < casillasCuadrado.length; indiceCasilla++) {
                int num = GestionJuego.generacionNumeroCasilla(this, casillasCuadrado[indiceCasilla]);
                if(num == -1) estadoGeneracionTablero = false;
                casillasCuadrado[indiceCasilla].setNumeroPropio(num);
            }
        }
    }
    
    /**
     * Carga de trabajo principal. Por eso he separado esta en hilos.
     * Le asignamos a cada casilla su numero propio. Si se llega a un punto muerto, se recomenzara con otro tablero.
     * @return Booleano para chequear cuando se ha generado correctamente.
     */
    public boolean asignacionNumeroCasillas() {
        Runnable task1 = () -> asignacionNumeroCasillasHilos(0); //Lambdas!
        Runnable task2 = () -> asignacionNumeroCasillasHilos(3);
        Runnable task3 = () -> asignacionNumeroCasillasHilos(6);
        
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);
        
        t1.start();
        t2.start();
        t3.start();        
        
        try {
            t1.join();
            t2.join();
            t3.join();
        }catch(InterruptedException ex) {
            ex.printStackTrace();
        }
                
        return estadoGeneracionTablero;
    }
    
    /**
     * Introduccion en si misma. Se debera repetir 3 veces.
     * Funciona. Evitar tocarlo a no ser que sea extremamente necesario.
     * @param numPrimerCuadrado Numero del primer cuadrado. Debera ser 0/3/6.
     */
    private void introduccionCasillasCuadradosEnFilas(int numPrimerCuadrado) {
        Casilla[] casillasTmp; //Almacen temporal hasta que este la fila completa y se introduzcan.
        
        for (int indiceFila = numPrimerCuadrado, indiceCasillaTmp, indiceCasillaCuadrado, indiceCasillaBaseCuadrado = 0; indiceFila < numPrimerCuadrado+3; indiceFila++, indiceCasillaCuadrado++, indiceCasillaBaseCuadrado += 3) {
            casillasTmp = new Casilla[9];
            indiceCasillaCuadrado = indiceCasillaBaseCuadrado;
            indiceCasillaTmp = 0;
            for (int x = 0; x <= 2; x++, indiceCasillaTmp++, indiceCasillaCuadrado++) {
                casillasTmp[indiceCasillaTmp] = CUADRADOS[numPrimerCuadrado].getCASILLAS()[indiceCasillaCuadrado];
                casillasTmp[indiceCasillaTmp+3] = CUADRADOS[numPrimerCuadrado+1].getCASILLAS()[indiceCasillaCuadrado];
                casillasTmp[indiceCasillaTmp+6] = CUADRADOS[numPrimerCuadrado+2].getCASILLAS()[indiceCasillaCuadrado];
            }
            FILAS[indiceFila] = new Fila(casillasTmp);
        }
    }
    
    /**
     * Introduccion de las Casillas de los Cuadrados en las Filas.
     */
    private void rellenoFilas() {
        for (int i = 0; i <= 6; i += 3) {
            introduccionCasillasCuadradosEnFilas(i); //0, 3, 6.
        }
    }

    /**
     * Introduccion de casillas en columnas. Se debera repetir tres veces.
     * Funciona. Evitar tocarlo a no ser que sea extremamente necesario.
     * @param numPrimerCuadrado Numero del primer cuadrado del set de 3.
     */
    private void introduccionCasillasCuadradosEnColumnas(int numPrimerCuadrado) {
        Casilla[] casillasTmp;
        
        for(int indiceColumna = numPrimerCuadrado*3, indiceCasillaTmp, indiceCasillaCuadrado, indiceCasillaCuadradoBase = 0; indiceColumna < ((numPrimerCuadrado*3)+3); indiceColumna++, indiceCasillaCuadradoBase++) {
            casillasTmp = new Casilla[9];
            indiceCasillaCuadrado = indiceCasillaCuadradoBase;
            indiceCasillaTmp = 0;
            for (int x = 0; x <= 2; x++, indiceCasillaTmp++, indiceCasillaCuadrado+=3) {
                casillasTmp[indiceCasillaTmp] = CUADRADOS[numPrimerCuadrado].getCASILLAS()[indiceCasillaCuadrado];
                casillasTmp[indiceCasillaTmp+3] = CUADRADOS[numPrimerCuadrado+3].getCASILLAS()[indiceCasillaCuadrado];
                casillasTmp[indiceCasillaTmp+6] = CUADRADOS[numPrimerCuadrado+6].getCASILLAS()[indiceCasillaCuadrado];
            }
            COLUMNAS[indiceColumna] = new Columna(casillasTmp);
        }
    }
    
    /**
     * Introduccion de las Casillas de los Cuadrados en las Columnas.
     */
    private void rellenoColumnas() {
        for (int i = 0; i < 3; i++) {
            introduccionCasillasCuadradosEnColumnas(i); //0, 1, 2.
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Diferentes partes del tablero para comprobar que todas ellas coinciden y cuadran: \nCuadrados: \n");
        
        for(Cuadrado cuad : CUADRADOS) {
            sb.append(cuad);
        }
        
        sb.append("Filas: \n");
        
        for(Fila fila: FILAS) {
            sb.append(fila);
            sb.append("\n");
        }
        
        sb.append("Columnas: \n");
        
        for(Columna columna: COLUMNAS) {
            sb.append(columna);
            sb.append("\n");
        }
        
        return sb.toString();
    }

    /*
        Generacion de un Tablero correcto a partir de una jTable rellena.
    */
    
    /**
        * Relleno de Cuadrado[] con los valores de la tabla de su homologo Cuadrado.
        * Arreglada la chapuza inicial que monte con los tries y catches.
        * @param cuadrados Cuadrados donde almaceno los datos de cada casilla.
        * @param tabla Tabla grafica de donde sacamos los datos.
        * @param numeroCuadrado Numero de cuadrado que toca rellenar en esta iteracion.
        */
       private static void rellenoCuadradoTablero(Cuadrado[] cuadrados, JTable tabla, int numeroCuadrado) {
           for (int i = 0, indiceCasilla = 0, indiceColumna = cuadrados[numeroCuadrado].getCASILLAS()[0].getNUMERO_COLUMNA(); i < 3; indiceColumna++, indiceCasilla++, i++) { //Una fila de un cuadrado.
               for (int x = 0, indiceCasillaLocal = indiceCasilla; x < 3; x++, indiceCasillaLocal += 3) {
                    try {
                        int indiceFila = cuadrados[numeroCuadrado].getCASILLAS()[0].getNUMERO_FILA()+x; //+0, +1, +2.
                        cuadrados[numeroCuadrado].getCASILLAS()[indiceCasillaLocal].setNumeroPropio(Integer.parseInt(tabla.getValueAt(indiceFila, indiceColumna).toString()));
                    }catch(NumberFormatException | NullPointerException ex) {} //Para que los casos donde haya una cell en blanco, no pete y siga adelante.
                }
           }
       }

       /**
        * Rellenamos cada Casilla con su 'casilla' correspondiente de la tabla.
        * @param tabla Tabla de la que sacamos los datos.
        * @param cuadrados Cuadrado[] que rellenamos.
        */
       private static void rellenoTablaConNumeros(JTable tabla, Cuadrado[] cuadrados) {
           for(int i = 0; i < cuadrados.length; i++) {
               rellenoCuadradoTablero(cuadrados, tabla, i);
           }
       }

       /**
        * Quitamos los numeros disponibles de las ArrayLists, de los numeros que ya se han puesto a mano.
        * Tengo que hacer un parse a (Object) ya que si no, toma el numero como si fuera el indice y no el objeto que quiero quitar.
        * @param tablero Tablero del cual quitamos los numeros ya puestos.
        */
       private static void quitarNumerosYaPuestosDeListas(Tablero tablero) {
           for(Fila fila: tablero.getFILAS()) {
               for(Casilla cas: fila.getCASILLAS()) {
                   if(cas.getNumeroPropio() != 0) {
                       tablero.getCUADRADOS()[cas.getNUMERO_CUADRADO()].getNumerosDisponiblesCuadrado().remove((Object) cas.getNumeroPropio());
                       tablero.getFILAS()[cas.getNUMERO_FILA()].getNumerosDisponiblesFila().remove((Object) cas.getNumeroPropio());
                       tablero.getCOLUMNAS()[cas.getNUMERO_COLUMNA()].getNumerosDisponiblesColumna().remove((Object) cas.getNumeroPropio());
                   }
               }
           }
       }

       /**
        * Generacion de un tablero a traves de una tabla grafica. Utiliza la tabla que paso como parametro por el constructor.
        * Es muy muy parecido al metodo que hay en Tablero para rellenar una tabla con el contenido de las casillas, pero aqui
        *  interesa rellenas las casillas con el contenido de la tabla.
        * @param tabla tabla grafica que quiero convertir en tablero.
        * @return Tablero generado.
        */
       public static Tablero generacionTablero(JTable tabla) {
           Cuadrado[] cuadrados = new Cuadrado[9];
           Tablero.inicializacionCuadrados(cuadrados);
           rellenoTablaConNumeros(tabla, cuadrados);
           Tablero tablero = new Tablero(cuadrados);
           quitarNumerosYaPuestosDeListas(tablero);
           return tablero;
       }
    
    /**
     * @return the CUADRADOS
     */
    public Cuadrado[] getCUADRADOS() {
        return CUADRADOS;
    }

    /**
     * @return the FILAS
     */
    public Fila[] getFILAS() {
        return FILAS;
    }

    /**
     * @return the COLUMNAS
     */
    public Columna[] getCOLUMNAS() {
        return COLUMNAS;
    }
}
