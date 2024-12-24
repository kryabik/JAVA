package tech.reliab.course.ryabikov.bank.service;

import tech.reliab.course.ryabikov.bank.entity.User;

public interface UserService extends CrudOperations<User> {
    int calculateCreditRating();
}