package uz.pdp.app_info_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_info_system.aop.CheckPermission;
import uz.pdp.app_info_system.aop.HuquqniTekshirish;
import uz.pdp.app_info_system.entity.Post;
import uz.pdp.app_info_system.payload.ApiResponse;
import uz.pdp.app_info_system.payload.PostDto;
import uz.pdp.app_info_system.payload.UserDto;
import uz.pdp.app_info_system.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;


    @CheckPermission(value = "ADD_POST")
    @PostMapping("/add")
    public HttpEntity<?> addPost(@Valid @RequestBody PostDto postDto){
        ApiResponse apiResponse= postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @HuquqniTekshirish(huquq = "EDIT_POST")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editPost(@Valid @RequestBody PostDto postDto,@PathVariable Long id){
        ApiResponse apiResponse= postService.editPost(postDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @HuquqniTekshirish(huquq = "DELETE_POST")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deletePost(@PathVariable Long id){
        ApiResponse apiResponse= postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getPost(@PathVariable Long id){
        ApiResponse apiResponse= postService.getPost(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get")
    public HttpEntity<?> getAllPost(){
        List<Post> postList= postService.getAllPost();
        return ResponseEntity.ok(postList);
    }
}
