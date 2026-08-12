package com.substring.service;

import com.substring.dtos.UserDTO;
import com.substring.entity.Provider;
import com.substring.entity.User;
import com.substring.exception.ResourseNotFoundException;
import com.substring.helper.UserHelper;
import com.substring.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if(userDTO.getEmail()==null || userDTO.getEmail().isBlank()){
            throw new IllegalArgumentException("Email is reqiured");
        }

        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new IllegalArgumentException("Email already exist");
        }

        User user = modelMapper.map(userDTO,User.class);

        user.setProvider(userDTO.getProvider()!=null ? userDTO.getProvider() : Provider.LOCAL);
        User savedUser = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO getUserByEmail(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(()->new ResourseNotFoundException("email not found"));

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, String userid) {
        UUID uId = UserHelper.parseUUID(userid);
        User existingUser = userRepository
                .findById(uId)
                .orElseThrow(()-> new ResourseNotFoundException("User not Found with given ID"));
        if(userDTO.getName()!=null) existingUser.setName(userDTO.getName());
        if(userDTO.getImage()!=null) existingUser.setImage(userDTO.getImage());
        if(userDTO.getProvider()!=null) existingUser.setProvider(userDTO.getProvider());
        if(userDTO.getPassword()!=null) existingUser.setPassword(userDTO.getPassword());
        existingUser.setEnable(userDTO.isEnable());

        userRepository.save(existingUser);

        return modelMapper.map(existingUser,UserDTO.class);

    }

    @Override
    public void deleteUser(String userId) {
        UUID uId = UserHelper.parseUUID(userId);
        User user = userRepository.findById(uId).orElseThrow(()-> new ResourseNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public UserDTO getUserById(String userId) {
        UUID uId = UserHelper.parseUUID(userId);
        User user = userRepository.findById(uId).orElseThrow(()-> new ResourseNotFoundException("User not Found"));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public Iterable<UserDTO> getAllUsers() {

        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserDTO.class))
                .toList();
    }
}
