package uz.pdp.app_info_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_info_system.aop.CheckPermission;
import uz.pdp.app_info_system.entity.Lavozim;
import uz.pdp.app_info_system.payload.ApiResponse;
import uz.pdp.app_info_system.payload.LavozimDto;
import uz.pdp.app_info_system.payload.UserDto;
import uz.pdp.app_info_system.service.LavozimService;
import uz.pdp.app_info_system.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {
    @Autowired
    LavozimService lavozimService;


    @CheckPermission(value = "ADD_LAVOZIM")//o'zim yozgan anotatsiya
    @PostMapping("/add")
    public HttpEntity<?> addLavozim(@Valid @RequestBody LavozimDto lavozimDto){
        ApiResponse apiResponse=lavozimService.addLavozim(lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_LAVOZIM')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editLavozim(@Valid @RequestBody LavozimDto lavozimDto,@PathVariable Long id){
        ApiResponse apiResponse=lavozimService.editLavozim(lavozimDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @CheckPermission(value = "VIEW_LAVOZIMLAR")//o'zim yozgan anotatsiya
    @GetMapping("/get")
    public HttpEntity<?> getLavozim(){
        List<Lavozim> lavozimList=lavozimService.getLavozim();
        return ResponseEntity.ok(lavozimList);
    }

    @CheckPermission(value = "DELETE_LAVOZIM")//o'zim yozgan anotatsiya
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteLavozim(@PathVariable Long id){
        ApiResponse apiResponse=lavozimService.deleteLavozim(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
