package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public class MealDAOInMemoryImpl implements MealDAO{

    private static MealDAOInMemoryImpl instance = new MealDAOInMemoryImpl();

    private List<Meal> meals;

    private MealDAOInMemoryImpl() {
        meals = new CopyOnWriteArrayList<>();
        meals.addAll(Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)));
    }

    public static MealDAOInMemoryImpl getInstance() {
        return instance;
    }

    @Override
    public void createMeal(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void removeMeal(UUID id) {
        meals.removeIf(new Predicate<Meal>() {
            @Override
            public boolean test(Meal meal) {
                return meal.getId().equals(id);
            }
        });
    }

    @Override
    public void updateMeal(Meal meal) {
        for (Meal m : meals) {
            if (m.getId().equals(meal.getId())) {
                m.setCalories(meal.getCalories());
                m.setDateTime(meal.getDateTime());
                m.setDescription(meal.getDescription());
            }
        }
    }

    @Override
    public Meal readMeal(UUID id) {
        Meal local = null;
        for (Meal meal : meals) {
            if (meal.getId().equals(id)) {
                local = meal;
            }
        }
        return local;
    }

    public List<Meal> getMeals() {
        return meals;
    }
}
