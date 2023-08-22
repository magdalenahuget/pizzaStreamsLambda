package service;

import model.Ingredient;
import model.Pizza;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PizzaServiceImplMy implements PizzaService{
    @Override
    public double getPizzaPrice(Pizza pizza) {
        return pizza.getIngredients().stream().mapToInt(Ingredient::getPrice).sum();
    }

    @Override
    public Pizza findCheapest() {
        //Pizza.values().stream
        //Arrays.stream(Pizza.values());
        return Arrays.stream(Pizza.values())
                .min(Comparator.comparing(this::getPizzaPrice))
                .get();
        //orElse(null) zamiast get;
    }

    @Override
    public Pizza findCheapestSpicy() {
        return Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients()
                        .stream()
                        .anyMatch(Ingredient::isSpicy))
                .min(Comparator.comparing(this::getPizzaPrice))
                .orElse(null);
    }

    @Override
    public Pizza findMostExpensiveVegetarian() {
        return Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients()
                        .stream()
                        .noneMatch(Ingredient::isMeat))
                .max(Comparator.comparing(this::getPizzaPrice))
                .orElse(null);
    }

    @Override
    public List<Pizza> iLikeMeat() {
        return Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients()
                        .stream()
                        .anyMatch(Ingredient::isMeat))
                .sorted(Comparator.comparing(pizza -> pizza.getIngredients().size(), Comparator.reverseOrder()))
                        .collect(Collectors.toList());
    }

    @Override
    public Map groupByPrice() {
        return Arrays.stream(Pizza.values())
                .collect(Collectors.groupingBy(this::getPizzaPrice));
    }

    @Override
    public Map<Integer, List<Pizza>> groupByNumberOfIngredientsGreaterThan4() {
        return Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients().size() > 4)
                .collect(Collectors.groupingBy(pizza -> pizza.getIngredients().size()));
    }

    @Override
    public String getMenu() {
        return Arrays.stream(Pizza.values())
                .map(Pizza::getName)
                        .collect(Collectors.joining("\n"));
    }
}
