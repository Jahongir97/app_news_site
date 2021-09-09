package uz.pdp.app_info_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_info_system.aop.CheckPermission;
import uz.pdp.app_info_system.aop.HuquqniTekshirish;
import uz.pdp.app_info_system.entity.User;
import uz.pdp.app_info_system.payload.ApiResponse;
import uz.pdp.app_info_system.payload.RegisterDto;
import uz.pdp.app_info_system.payload.UserDto;
import uz.pdp.app_info_system.service.AuthService;
import uz.pdp.app_info_system.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;


    @CheckPermission(value = "ADD_USER")
    @PostMapping("/add")
    public HttpEntity<?> addUser(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse=userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @HuquqniTekshirish(huquq = "EDIT_USER")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editUser(@Valid @RequestBody UserDto userDto,@PathVariable Long id){
        ApiResponse apiResponse=userService.editUser(userDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @HuquqniTekshirish(huquq = "DELETE_USER")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Long id){
        ApiResponse apiResponse=userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @HuquqniTekshirish(huquq = "VIEW_USER")
    @GetMapping("/get/{id}")
    public HttpEntity<?> getUser(@PathVariable Long id){
        ApiResponse apiResponse=userService.getUser(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @HuquqniTekshirish(huquq = "VIEW_USER")
    @GetMapping("/get")
    public HttpEntity<?> getAllUser(){
        List<User> userList=userService.getAllUser();
        return ResponseEntity.ok(userList);
    }
}
