package org.bsim.intern.walletapp_demo2.ui.controller;

import org.apache.catalina.User;
import org.bsim.intern.walletapp_demo2.service.iservice.IUserInterface;
import org.bsim.intern.walletapp_demo2.shared.dto.UserDTO;
import org.bsim.intern.walletapp_demo2.ui.model.request.UserRequest;
import org.bsim.intern.walletapp_demo2.ui.model.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    IUserInterface userService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponse> getUsers(){
        List<UserDTO> users = userService.getListUser();
        List<UserResponse> value = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();

        for (UserDTO userDTO : users){
            value.add(mapper.map(userDTO, UserResponse.class));
        }
        return value;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse addNewUser(@RequestBody UserRequest user){
        ModelMapper mapper = new ModelMapper();
        //UserRequest --> UserDTO
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        UserDTO createUser = userService.addNewData(userDTO);
        //UserDTO --? UserResponse
        UserResponse response = mapper.map(createUser, UserResponse.class);
        return response;
    }

    @GetMapping(path = "/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})//ini untuk get by username
    public UserResponse getUserByUsername(@PathVariable String username){//di usernamenya hrus sama dengan path di getMapping
        UserDTO getUser = userService.getUserByUsername(username);
        if (getUser == null)
            return null;
        //else
        return new ModelMapper().map(getUser, UserResponse.class);
    }



}
