package com.joseph.rest.webservices.restfulwebservices.user;

import com.joseph.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.joseph.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.joseph.rest.webservices.restfulwebservices.jpa.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UserJPAController {

    private UserRepository repository;

    private PostRepository postRepository;

    public UserJPAController(UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
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

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @RequestBody Post post ){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();


    }

}
