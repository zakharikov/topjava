package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealDao meals = new MealDaoInMemoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        List<MealTo> mealsWithExcess = MealsUtil.getWithExcess(meals.getList(), 2000);
        req.setAttribute("meals", mealsWithExcess);
        getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("dateTime") != null && req.getParameter("calories") != null && req.getParameter("description") != null) {
            LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
            int calories = Integer.parseInt(req.getParameter("calories"));
            String description = req.getParameter("description");
            Meal meal = new Meal(dateTime, description, calories);
            meals.create(meal);
            doGet(req, resp);
        } else if (req.getParameter("idToDelete") != null && meals.getMap().containsKey(Integer.parseInt(req.getParameter("idToDelete")))) {
            int id = Integer.parseInt(req.getParameter("idToDelete"));
            meals.delete(id);
            doGet(req, resp);
        } else if (req.getParameter("idToUpdate") != null && meals.getMap().containsKey(Integer.parseInt(req.getParameter("idToUpdate")))) {
            int id = Integer.parseInt(req.getParameter("idToUpdate"));
            LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTimeToUpdate"));
            int calories = Integer.parseInt(req.getParameter("caloriesToUpdate"));
            String description = req.getParameter("descriptionToUpdate");
            Meal meal = new Meal(dateTime, description, calories);
            meals.update(id, meal);
            doGet(req, resp);
        } else if (req.getParameter("idToRead") != null && meals.getMap().containsKey(Integer.parseInt(req.getParameter("idToRead")))) {
            int id = Integer.parseInt(req.getParameter("idToRead"));
            Meal toRead = meals.read(id);
            req.setAttribute("mealToPrint", toRead.toString());
            doGet(req, resp);
        } else {
            doGet(req, resp);
        }
    }
}
