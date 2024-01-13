package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.UserDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Authority;
import nl.novi.techiteasy.models.User;
import nl.novi.techiteasy.repository.UserRepository;
import nl.novi.techiteasy.utils.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }


    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = repo.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = repo.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new RecordNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return repo.existsById(username);
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User newUser = repo.save(toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        repo.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!repo.existsById(username)) throw new RecordNotFoundException();
        User user = repo.findById(username).get();
        user.setPassword(newUser.getPassword());
        repo.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!repo.existsById(username)) throw new RecordNotFoundException(username);
        User user = repo.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!repo.existsById(username)) throw new RecordNotFoundException(username);
        User user = repo.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        repo.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!repo.existsById(username)) throw new RecordNotFoundException(username);
        User user = repo.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        repo.save(user);
    }

    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }
}
