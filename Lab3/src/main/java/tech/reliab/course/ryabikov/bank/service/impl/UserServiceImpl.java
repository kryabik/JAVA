package tech.reliab.course.ryabikov.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.reliab.course.ryabikov.bank.entity.Bank;
import tech.reliab.course.ryabikov.bank.entity.User;
import tech.reliab.course.ryabikov.bank.repository.*;
import tech.reliab.course.ryabikov.bank.service.UserService;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Создание нового пользователя.
     * @return Новый пользователь
     */
    @Override
    public User create(String fullName, LocalDate birthDate, String job) {
        User user = new User(0, fullName, birthDate, job);
        user.setCreditRating(calculateCreditRating(user));
        user.setMonthlyIncome(new Random().nextInt(1001));

        return userRepository.save(user);
    }

    /**
     * Чтение данных пользователя.
     * @param id Идентификатор пользователя
     */
    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findAll().stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    /**
     * Рассчитывает кредитный рейтинг пользователя
     * @return Кредитный рейтинг
     */
    public int calculateCreditRating(User user) {
        if (user.getMonthlyIncome() < 1000) {
            return 100;
        } else if (user.getMonthlyIncome() < 2000) {
            return 200;
        } else if (user.getMonthlyIncome() < 3000) {
            return 300;
        } else if (user.getMonthlyIncome() < 4000) {
            return 400;
        } else if (user.getMonthlyIncome() < 5000) {
            return 500;
        } else if (user.getMonthlyIncome() < 6000) {
            return 600;
        } else if (user.getMonthlyIncome() < 7000) {
            return 700;
        } else if (user.getMonthlyIncome() < 8000) {
            return 800;
        } else if (user.getMonthlyIncome() < 9000) {
            return 900;
        } else {
            return 1000;
        }
    }

    /**
     * Получение списка всех пользователей
     * @return Пользователи
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Получение пользователя
     * @param id АЙДИ пользователя
     * @return Пользователь, если найден, иначе выбрасывается исключение
     */
    @Override
    public User getUserIfExists(int id) {
        return getUserById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    /**
     * Получение списка пользователей, связанных с банком
     * @param bank Банк для поиска пользователей
     * @return Список пользователей, связанных с банком
     */
    @Override
    public List<User> getUsersByBank(Bank bank) {
        return List.of();
    }
}