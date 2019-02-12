package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.UUID;

public interface MealDAO {

    public void createMeal(Meal meal);

    public void removeMeal(UUID id);

    public void updateMeal(Meal meal);

    public Meal readMeal(UUID id);
}
