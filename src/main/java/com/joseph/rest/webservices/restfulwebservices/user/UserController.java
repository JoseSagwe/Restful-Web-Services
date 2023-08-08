package com.joseph.rest.webservices.restfulwebservices.user;

import com.joseph.rest.webservices.restfulwebservices.user.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
     User user = userDao.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id:"+id);

        return user;
    }

//    @PostMapping("/users")
//    public void createUser(@RequestBody User user){
//        userDao.save(user);
//    }

    //To return correct response status
    @PostMapping("/users")
    public ResponseEntity<User> createUserWithCorrectResponseStatus(@RequestBody User user){
        User savedUser = userDao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
