package com.recruitment.user;

import com.recruitment.user.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.recruitment.UrlMapping.ENTITY;
import static com.recruitment.UrlMapping.USERS;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping(ENTITY)
    public UserListDTO makeRecruiter(@PathVariable Long id){
        return userService.makeRecruiter(id);
    }

    @DeleteMapping(ENTITY)
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/companies")
    public List<UserListDTO> allSeekers(){
        List<UserListDTO> seekers = userService.findAllSeekers();
        return seekers;
    }
}
