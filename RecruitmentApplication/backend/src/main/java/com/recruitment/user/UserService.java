package com.recruitment.user;

import com.recruitment.user.dto.UserListDTO;
import com.recruitment.user.dto.UserMinimalDTO;
import com.recruitment.user.mapper.UserMapper;
import com.recruitment.user.model.ERole;
import com.recruitment.user.model.Role;
import com.recruitment.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Set;

import static com.recruitment.user.model.ERole.SEEKER;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    public UserListDTO makeRecruiter(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityExistsException("User not found"));
        user.setRoles(Set.of(roleRepository.findByName(ERole.RECRUITER).get()));
        return userMapper.userListDtoFromUser(userRepository.save(user));
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public List<UserListDTO> findAllSeekers(){
        List<UserListDTO> seekers = userRepository.findAll()
                .stream()
                .filter(x -> x.getRoles().stream().collect(toList()).get(0).getName() == SEEKER)
                .map(userMapper::userListDtoFromUser)
                .collect(toList());

        return seekers;
    }
}
