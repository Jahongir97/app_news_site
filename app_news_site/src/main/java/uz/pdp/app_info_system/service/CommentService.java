package uz.pdp.app_info_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.app_info_system.entity.Comment;
import uz.pdp.app_info_system.entity.Post;
import uz.pdp.app_info_system.entity.User;
import uz.pdp.app_info_system.payload.ApiResponse;
import uz.pdp.app_info_system.payload.CommentDto;
import uz.pdp.app_info_system.repository.CommentRepository;
import uz.pdp.app_info_system.repository.PostRepository;
import uz.pdp.app_info_system.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    public ApiResponse addComment(CommentDto commentDto) {
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponse("Post topilmadi",false);
        Comment comment=new Comment(
                commentDto.getText(),
                optionalPost.get()
        );
        commentRepository.save(comment);
        return new ApiResponse("Comment qo'shildi",true);
    }

    public ApiResponse editComment(CommentDto commentDto, Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment topilmadi", false);
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponse("Post topilmadi",false);
       Comment comment=optionalComment.get();
       comment.setText(commentDto.getText());
       comment.setPost(optionalPost.get());
       commentRepository.save(comment);
       return new ApiResponse("Comment tahrirlandi",true);
    }

    public ApiResponse deleteComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment topilmadi", false);
        Comment comment = optionalComment.get();
        User userInSystem = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (comment.getCreatedBy().equals(userInSystem)) {
            commentRepository.deleteById(id);
            return new ApiResponse("Comment o'chirildi", true);
        }
        return new ApiResponse("Siz faqat o'z kommentingizni o'chira olasiz", false);
    }

    public List<Comment> getAllComment(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public ApiResponse deleteMyComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment topilmadi", false);
       commentRepository.deleteById(id);
        return new ApiResponse("Comment o'chirildi", false);
    }
}
