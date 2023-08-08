package com.joseph.rest.webservices.restfulwebservices.user;

import com.joseph.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.joseph.rest.webservices.restfulwebservices.jpa.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAController {

    private UserRepository repository;

    public UserJPAController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/jpa/users")
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Optional<User> getUserById(@PathVariable Integer id){
     Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        return user;
    }

//    @PostMapping("/users")
//    public void createUser(@RequestBody User user){
//        userDao.save(user);
//    }

    //To return correct response status
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUserWithCorrectResponseStatus(@RequestBody User user){
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostForUser(@PathVariable Integer id){
            Optional<User> user = repository.findById(id);
            if(user.isEmpty())
                throw new UserNotFoundException("id:"+id);
            return user.get().getPosts();

    }

}
