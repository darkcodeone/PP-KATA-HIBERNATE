package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser(new User("Mark", "Lipkin", (byte) 27));
        userService.saveUser(new User("Nikita", "Dunichev", (byte) 37));
        userService.saveUser(new User("Valery", "Jmishenko", (byte) 54));
        userService.saveUser(new User("Valirya", "Valerievna", (byte) 47));
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
