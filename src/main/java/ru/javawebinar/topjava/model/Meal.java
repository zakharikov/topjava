package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends AbstractBaseEntity {

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private Integer userId;

    public Meal(LocalDateTime dateTime, String description, int calories, Integer userId) {
        this(null, dateTime, description, calories, userId);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories, Integer userId) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userId = userId;
    }

    public Integer getId() {
        return super.id;
    }

    public void setId(Integer id) {
        super.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public Integer getUserId() {
        return userId;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + super.id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
