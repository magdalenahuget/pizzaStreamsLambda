package service;

import model.Ingredient;
import model.Pizza;

import java.util.*;
import java.util.stream.Collectors;

public class PizzaServiceImpl implements PizzaService {
    @Override
    public double getPizzaPrice(Pizza pizza) {
        return pizza.getIngredients()
                .stream()
                .mapToDouble(ingredient -> ingredient.getPrice())
                .sum();
    }

    @Override
    public Pizza findCheapest() {
        return Arrays.stream(Pizza.values())
                .min(Comparator.comparing(this::getPizzaPrice)) // tu może być Pizza lub null
                .orElse(null);
    }

    @Override
    public Pizza findCheapestSpicy() {
        return Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients()
                        .stream()
                        .anyMatch(Ingredient::isSpicy)
                )
                .min(Comparator.comparing(this::getPizzaPrice))
                .orElse(null);
    }

    @Override
    public Pizza findMostExpensiveVegetarian() {
        return Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients()
                        .stream()
                        .noneMatch(Ingredient::isMeat)
                )
                .max(Comparator.comparing(this::getPizzaPrice))
                .orElse(null);

    }

    @Override
    public List<Pizza> iLikeMeat() {
        return Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients()
                        .stream()
                        .anyMatch(Ingredient::isMeat)
                )
                .sorted(Comparator.comparing(pizza -> pizza.getIngredients().size(), Comparator.reverseOrder()))
                .collect(Collectors.toList()
                );
    }

    @Override
    public Map groupByPrice() {
        return Arrays.stream(Pizza.values())
                .collect(Collectors.groupingBy(this::getPizzaPrice));
    }

    @Override
    public TreeMap<Integer, List<Pizza>> groupByNumberOfIngredientsGreaterThan4() {
        return Arrays.stream(Pizza.values())
                .collect(Collectors.groupingBy(pizza -> pizza.getIngredients().size(), TreeMap::new, Collectors.toList()));
    }

    // Zwraca menu | nazwa pizzy | ostra lub łagodna | mięsna lub wege | cena | nazwa_składnika1, ..., nazwa_składnikaN |
    @Override
    public String getMenu() {
        return Arrays.stream(Pizza.values())
                .map(pizza -> String.format("| %30s | %10s | %10s | %5.2f zł | %-100s |",
                        pizza.getName(),
                        pizza.getIngredients().stream().anyMatch(Ingredient::isSpicy) ? "ostra" : "łagodna",
                        pizza.getIngredients().stream().anyMatch(Ingredient::isMeat) ? "mięsna" : "wege",
                        this.getPizzaPrice(pizza),
                        pizza.getIngredients()
                                .stream()
                                .map(Ingredient::getName)
                                .collect(Collectors.joining(", ")
                                )
                        )
                )
                .collect(Collectors.joining("\n"));
    }
}
