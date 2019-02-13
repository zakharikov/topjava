package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;

public interface MealDao {

    Meal create(Meal meal);

    Meal read(int id);

    Meal update(int id, Meal meal);

    void delete(int id);

    List<Meal> getList();

    Map<Integer, Meal> getMap();
}
