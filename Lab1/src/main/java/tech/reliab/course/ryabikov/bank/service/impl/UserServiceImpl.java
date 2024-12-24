package tech.reliab.course.ryabikov.bank.service.impl;

import tech.reliab.course.ryabikov.bank.entity.User;
import tech.reliab.course.ryabikov.bank.service.UserService;

public class UserServiceImpl implements UserService {
    private User user;

    /**
     * Создание нового пользователя.
     */
    @Override
    public void create(User newUser) {
        this.user = newUser;
        this.user.setCreditRating(calculateCreditRating());
    }

    /**
     * Чтение данных пользователя.
     * @param id Идентификатор пользователя
     */
    @Override
    public User read(int id) {
        if (this.user != null && this.user.getId() == id) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * Обновление данных пользователя.
     */
    @Override
    public void update(User user) {
        if (this.user != null && this.user.getId() == user.getId()) {
            this.user = user;
        }
    }

    /**
     * Удаление пользователя.
     * @param id Идентификатор пользователя
     */
    @Override
    public void delete(int id) {
        if (this.user != null && this.user.getId() == id) {
            this.user = null;
        }
    }

    /**
     * Рассчитывает кредитный рейтинг пользователя
     * @return Кредитный рейтинг
     */
    public int calculateCreditRating() {
        if (this.user.getMonthlyIncome() < 1000) {
            return 100;
        } else if (this.user.getMonthlyIncome() < 2000) {
            return 200;
        } else if (this.user.getMonthlyIncome() < 3000) {
            return 300;
        } else if (this.user.getMonthlyIncome() < 4000) {
            return 400;
        } else if (this.user.getMonthlyIncome() < 5000) {
            return 500;
        } else if (this.user.getMonthlyIncome() < 6000) {
            return 600;
        } else if (this.user.getMonthlyIncome() < 7000) {
            return 700;
        } else if (this.user.getMonthlyIncome() < 8000) {
            return 800;
        } else if (this.user.getMonthlyIncome() < 9000) {
            return 900;
        } else {
            return 1000;
        }
    }
}