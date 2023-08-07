package com.joseph.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserDataAccessObjectService userDao;

    public UserController(UserDataAccessObjectService userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id){
        return userDao.findOne(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        userDao.save(user);
    }

}
