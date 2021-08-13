package LastTask.task.controller;

import LastTask.task.model.User;
import LastTask.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
//    Получение всех пользователей - …/api/users ( GET )
//    Добавление пользователя - …/api/users ( POST )
//    Изменение пользователя - …/api/users ( PUT )
//    Удаление пользователя - …/api/users /{id} ( DELETE )

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<User> getOneUser(@PathVariable(value = "id") Long id) {
        Optional<User> user = getAllUsers().stream().filter(u -> u.getId().equals(id)).findFirst();
        return ResponseEntity.ok().body(user.get());
    }

    @PostMapping
    public User createEmployee( @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userRepository.delete(getAllUsers().stream().filter(user -> user.getId().equals(id)).findFirst().get());
    }

}
