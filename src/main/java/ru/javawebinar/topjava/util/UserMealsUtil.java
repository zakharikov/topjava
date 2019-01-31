package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> exceeds = new ArrayList<>();
        Map<LocalDate, List<UserMeal>> groupedByDate = mealList.stream().collect(Collectors.groupingBy((s) -> s.getDateTime().toLocalDate()));
        for (Map.Entry<LocalDate, List<UserMeal>> pair : groupedByDate.entrySet()) {
            if (pair.getValue().stream().mapToInt((s) -> s.getCalories()).sum() > caloriesPerDay) {
                for (UserMeal userMeal : pair.getValue()) {
                    exceeds.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
                }
            } else {
                for (UserMeal userMeal : pair.getValue()) {
                    exceeds.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false));
                }
            }
        }
        return exceeds.stream().filter(s -> TimeUtil.isBetween(s.getDateTime().toLocalTime(), startTime, endTime)).collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededWithLoop(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = new HashMap<>();
        List<UserMealWithExceed> exceeds = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            map.put(userMeal.getDateTime().toLocalDate(), 0);
        }
        for (Map.Entry<LocalDate, Integer> pair : map.entrySet()) {
            for (UserMeal userMeal : mealList) {
                if (pair.getKey().equals(userMeal.getDateTime().toLocalDate())) {
                    pair.setValue(pair.getValue() + userMeal.getCalories());
                }
            }
        }
        for (Map.Entry<LocalDate, Integer> pair : map.entrySet()) {
            for (UserMeal userMeal : mealList) {
                if (pair.getKey().equals(userMeal.getDateTime().toLocalDate())) {
                    if (pair.getValue() > caloriesPerDay) {
                        exceeds.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
                    } else {
                        exceeds.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false));
                    }
                }
            }
        }
        List<UserMealWithExceed> exceeds2 = new ArrayList<>();
        for (UserMealWithExceed userMealWithExceed : exceeds) {
            if (TimeUtil.isBetween(userMealWithExceed.getDateTime().toLocalTime(), startTime, endTime)) {
                exceeds2.add(userMealWithExceed);
            }
        }
        return exceeds2;
    }
}
