package es.ceu.gisi.modcomp.cyk_algorithm.algorithm;

import es.ceu.gisi.modcomp.cyk_algorithm.algorithm.exceptions.CYKAlgorithmException;
import es.ceu.gisi.modcomp.cyk_algorithm.algorithm.interfaces.CYKAlgorithmInterface;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Esta clase contiene la implementación de la interfaz CYKAlgorithmInterface
 * que establece los métodos necesarios para el correcto funcionamiento del
 * proyecto de programación de la asignatura Modelos de Computación.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class CYKAlgorithm implements CYKAlgorithmInterface {

    private ArrayList<Character> conjuntoNoTerminales;
    private ArrayList<Character> conjuntoTerminales;
    private Character axioma;
    private Map<Character, ArrayList<String>> producciones;

    /**
     * Constructor de CYKAlgorithm que inicializa los juntos de elementos y
     * producciones
     */
    public CYKAlgorithm() {
        this.axioma = null;
        this.conjuntoNoTerminales = new ArrayList();
        this.conjuntoTerminales = new ArrayList();
        this.producciones = new TreeMap< Character, ArrayList<String>>();
    }

    @Override
    /**
     * Método que añade los elementos no terminales de la gramática.
     *
     * @param nonterminal Por ejemplo, 'S'
     * @throws CYKAlgorithmException Si el elemento no es una letra mayúscula.
     */
    public void addNonTerminal(char nonterminal) throws CYKAlgorithmException {

        if (Character.isUpperCase(nonterminal)) {

            if (this.conjuntoNoTerminales.contains(nonterminal)) {
                throw new CYKAlgorithmException();
            }

            this.conjuntoNoTerminales.add(nonterminal);
        } else {
            throw new CYKAlgorithmException();
        }
    }

    @Override
    /**
     * Método que añade los elementos terminales de la gramática.
     *
     * @param terminal Por ejemplo, 'a'
     * @throws CYKAlgorithmException Si el elemento no es una letra minúscula.
     */
    public void addTerminal(char terminal) throws CYKAlgorithmException {
        if (Character.isLowerCase(terminal)) {
            this.conjuntoTerminales.add(terminal);
        } else {
            throw new CYKAlgorithmException();
        }
    }

    @Override
    /**
     * Método que indica, de los elementos no terminales, cuál es el axioma de
     * la gramática.
     *
     * @param nonterminal Por ejemplo, 'S'
     * @throws CYKAlgorithmException Si el elemento insertado no forma parte del
     * conjunto de elementos no terminales.
     */
    public void setStartSymbol(char nonterminal) throws CYKAlgorithmException {

        if (conjuntoNoTerminales.contains(nonterminal)) {
            this.axioma = nonterminal;
        } else {
            throw new CYKAlgorithmException();
        }

    }

    @Override
    /**
     * Método utilizado para construir la gramática. Admite producciones en FNC,
     * es decir de tipo A::=BC o A::=a
     *
     * @param nonterminal A
     * @param production "BC" o "a"
     * @throws CYKAlgorithmException Si la producción no se ajusta a FNC o está
     * compuesta por elementos (terminales o no terminales) no definidos
     * previamente.
     */
    public void addProduction(char nonterminal, String production) throws CYKAlgorithmException {
        if (!this.conjuntoNoTerminales.contains(nonterminal)) {
            throw new CYKAlgorithmException();
        }
        if (production.length() == 1) {

            if (!this.conjuntoTerminales.contains(production.charAt(0))) {
                throw new CYKAlgorithmException();
            }
            if (this.producciones.containsKey(nonterminal)) {

                for (String produccion : this.producciones.get(nonterminal)) {
                    if (produccion == production) {
                        throw new CYKAlgorithmException();
                    }
                }

                this.producciones.get(nonterminal).add(production);
            } else {
                this.producciones.put(nonterminal, new ArrayList());
                this.producciones.get(nonterminal).add(production);
            }

        } else if (production.length() == 2) {

            char letra1 = production.charAt(0);
            char letra2 = production.charAt(1);
            if ((!this.conjuntoNoTerminales.contains(letra1)) || (!this.conjuntoNoTerminales.contains(letra2))) {
                throw new CYKAlgorithmException();
            }
            if (this.producciones.containsKey(nonterminal)) {
                this.producciones.get(nonterminal).add(production);
            } else {
                this.producciones.put(nonterminal, new ArrayList());
                this.producciones.get(nonterminal).add(production);
            }

        } else {
            throw new CYKAlgorithmException();
        }
    }

    @Override
    /**
     * Método que indica si una palabra pertenece al lenguaje generado por la
     * gramática que se ha introducido.
     *
     * @param word La palabra a verificar, tiene que estar formada sólo por
     * elementos no terminales.
     * @return TRUE si la palabra pertenece, FALSE en caso contrario
     * @throws CYKAlgorithmException Si la palabra proporcionada no está formada
     * sólo por terminales, si está formada por terminales que no pertenecen al
     * conjunto de terminales definido para la gramática introducida, si la
     * gramática es vacía o si el autómata carece de axioma.
     */
    public boolean isDerived(String word) throws CYKAlgorithmException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    /**
     * Método que, para una palabra, devuelve un String que contiene todas las
     * celdas calculadas por el algoritmo (la visualización debe ser similar al
     * ejemplo proporcionado en el PDF que contiene el paso a paso del
     * algoritmo).
     *
     * @param word La palabra a verificar, tiene que estar formada sólo por
     * elementos no terminales.
     * @return Un String donde se vea la tabla calculada de manera completa,
     * todas las celdas que ha calculado el algoritmo.
     * @throws CYKAlgorithmException Si la palabra proporcionada no está formada
     * sólo por terminales, si está formada por terminales que no pertenecen al
     * conjunto de terminales definido para la gramática introducida, si la
     * gramática es vacía o si el autómata carece de axioma.
     */
    public String algorithmStateToString(String word) throws CYKAlgorithmException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    /**
     * Elimina todos los elementos que se han introducido hasta el momento en la
     * gramática (elementos terminales, no terminales, axioma y producciones),
     * dejando el algoritmo listo para volver a insertar una gramática nueva.
     */
    public void removeGrammar() {
        this.conjuntoTerminales.clear();
        this.conjuntoNoTerminales.clear();
        this.producciones.clear();
        this.axioma = null;
    }

    @Override
    /**
     * Devuelve un String que representa todas las producciones que han sido
     * agregadas a un elemento no terminal.
     *
     * @param nonterminal
     * @return Devuelve un String donde se indica, el elemento no terminal, el
     * símbolo de producción "::=" y las producciones agregadas separadas, única
     * y exclusivamente por una barra '|' (no incluya ningún espacio). Por
     * ejemplo, si se piden las producciones del elemento 'S', el String de
     * salida podría ser: "S::=AB|BC".
     */
    public String getProductions(char nonterminal) {

        String cadenaProducciones = "";
        String stringFinal = "";
        int indice = 0;

        if (this.producciones.get(nonterminal) != null) {
            for (String produccion : this.producciones.get(nonterminal)) {
                cadenaProducciones = cadenaProducciones + produccion;

                indice++;
                if (indice != this.producciones.get(nonterminal).size()) {
                    cadenaProducciones = cadenaProducciones + "|";
                }
            }

            stringFinal = nonterminal + "::=" + cadenaProducciones;
        }

        return stringFinal;
    }

    @Override
    /**
     * Devuelve un String con la gramática completa.
     *
     * @return Devuelve el agregado de hacer getProductions sobre todos los
     * elementos no terminales.
     */
    public String getGrammar() {
        String gramatica = "";
        for (char noTerminal : this.conjuntoTerminales) {
            gramatica = gramatica + getProductions(noTerminal) + "\n";
        }

        return gramatica;
    }

}
