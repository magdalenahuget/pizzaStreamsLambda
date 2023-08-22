package service;

import model.Pizza;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public interface PizzaService {
    /**
     * Zwraca cenę pizzy n podstawie sumy składników
     * @param pizza
     * @return price
     */
    public double getPizzaPrice(Pizza pizza);

    /**
     * Wyszukuje i zwraca najtańszą pizze
     * @return Pizza
     */
    public Pizza findCheapest();

    /**
     * Wyszukuje i zwraca najtańszą pizze ostrą
     * @return Pizza
     */
    public Pizza findCheapestSpicy();

    /**
     * Wyszukuje najdroższą pizze wegetariańską
     * @return Pizza
     */
    public Pizza findMostExpensiveVegetarian();

    /**
     * Wyszukuje i zwraca listę pizz mięsnych posortowanych malejąco po liczbie składników
     * @return List<Pizza>
     */
    public List<Pizza> iLikeMeat();

    /**
     * Grupuje pizze po cenie
     * @return
     */
    public Map groupByPrice();
    /**
     * Grupowanie pizzy po ilości składników pod warunkiem, że jest ich więcej niż 4
     * @return
     */
    public Map<Integer, List<Pizza>> groupByNumberOfIngredientsGreaterThan4();

    // Zwraca menu | nazwa pizzy | ostra lub łagodna | mięsna lub wege | cena | nazwa_składnika1, ..., nazwa_składnikaN |
    public String getMenu();

}
