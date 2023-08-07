package com.joseph.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDataAccessObjectService {

//    Static Array List
    private static List<User> users = new ArrayList<>();

    private static  int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Joseph", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Sagwe", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Birisio", LocalDate.now().minusYears(20)));
    }



    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }


}
