package uz.pdp.app_info_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_info_system.aop.CheckPermission;
import uz.pdp.app_info_system.aop.HuquqniTekshirish;
import uz.pdp.app_info_system.entity.Comment;
import uz.pdp.app_info_system.entity.User;
import uz.pdp.app_info_system.payload.ApiResponse;
import uz.pdp.app_info_system.payload.CommentDto;
import uz.pdp.app_info_system.payload.UserDto;
import uz.pdp.app_info_system.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;


    @CheckPermission(value = "ADD_COMMENT")
    @PostMapping("/add")
    public HttpEntity<?> addComment(@Valid @RequestBody CommentDto commentDto){
        ApiResponse apiResponse= commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @HuquqniTekshirish(huquq = "EDIT_COMMENT")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editComment(@Valid @RequestBody CommentDto commentDto,@PathVariable Long id){
        ApiResponse apiResponse= commentService.editComment(commentDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @HuquqniTekshirish(huquq = "DELETE_COMMENT")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id){
        ApiResponse apiResponse= commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @HuquqniTekshirish(huquq = "DELETE_MY_COMMENT")
    @DeleteMapping("/deleteMyComment/{id}")
    public HttpEntity<?> deleteMyComment(@PathVariable Long id){
        ApiResponse apiResponse= commentService.deleteMyComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }



    @GetMapping("/get/{id}")
    public HttpEntity<?> getAllComment(@PathVariable Long postId){
        List<Comment> allComment= commentService.getAllComment(postId);
        return ResponseEntity.ok(allComment);
    }
}
