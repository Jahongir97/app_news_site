package uz.pdp.app_info_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_info_system.entity.Post;
import uz.pdp.app_info_system.payload.ApiResponse;
import uz.pdp.app_info_system.payload.PostDto;
import uz.pdp.app_info_system.payload.UserDto;
import uz.pdp.app_info_system.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public ApiResponse addPost(PostDto postDto) {
        Post newPost=new Post();
        newPost.setText(postDto.getText());
        newPost.setTitle(postDto.getTitle());
        newPost.setUrl(postDto.getUrl());
        postRepository.save(newPost);
        return new ApiResponse("Post saqlandi", true);
    }

    public ApiResponse editPost(PostDto postDto, Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponse("Post topilmadi",false);
        Post newPost=optionalPost.get();
        newPost.setText(postDto.getText());
        newPost.setTitle(postDto.getTitle());
        newPost.setUrl(postDto.getUrl());
        postRepository.save(newPost);
        return new ApiResponse("Post tahrirlandi", true);
    }

    public ApiResponse deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponse("Post topilmadi",false);
        postRepository.deleteById(id);
        return new ApiResponse("Post o'chirildi",true);
    }

    public ApiResponse getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.map(post -> new ApiResponse("Post", true, post)).orElseGet(() -> new ApiResponse("Post topilmadi", false));
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }
}
