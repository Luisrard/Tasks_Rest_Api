package com.nextline.services;

import com.nextline.entities.User;
import com.nextline.mappers.UserMapper;
import com.nextline.models.UserDto;
import com.nextline.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service to manage the users
 */
@RequiredArgsConstructor
@Service
public class UserService implements CrudService<UserDto, Object>{
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    /**
     * To search an user with their own id
     * @param id the id.
     * @return the user in the case of exist or a null if has not been found.
     */
    public User getUser(Integer id){
        if(id != null){
            return usersRepository.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public UserDto find(Integer id) {
        return userMapper.userToUserDto(getUser(id));
    }

    @Override
    public UserDto save(UserDto request) throws RuntimeException {
        if(request != null){
            request.setId(null);
            User user = userMapper.userDtoToUser(request);
            User response = usersRepository.save(user);
            return userMapper.userToUserDto(response);
        }
        return null;
    }

    @Override
    public UserDto update(UserDto request) throws RuntimeException {
        if(request != null){
            if(getUser(request.getId()) != null) {
                User user = userMapper.userDtoToUser(request);
                User response = usersRepository.save(user);
                return userMapper.userToUserDto(response);
            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) throws RuntimeException {
        if(id != null){
            usersRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
