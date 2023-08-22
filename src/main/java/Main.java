import model.Pizza;
import service.PizzaService;
import service.PizzaServiceImpl;
import service.PizzaServiceImplMy;

public class Main {
    public static void main(String[] args) {
        PizzaService ps = new PizzaServiceImplMy();
        System.out.println("Margeritta cana: " + ps.getPizzaPrice(Pizza.MARGHERITA));
        System.out.println("Capri cana: " + ps.getPizzaPrice(Pizza.CAPRI));

        System.out.println("Najtańsza: " + ps.findCheapest());
        System.out.println("Najtańsza ostra: " + ps.findCheapestSpicy());
        System.out.println("Najdroższa wege: " + ps.findMostExpensiveVegetarian());
        System.out.println("Lista mięsnych: " + ps.iLikeMeat());
        System.out.println("Pogrupowane po cenach: " + ps.groupByPrice());
        System.out.println("Pogrupowane po liczbie składników: " + ps.groupByNumberOfIngredientsGreaterThan4());
        System.out.println("MENU");
        System.out.println(ps.getMenu());
    }
}
