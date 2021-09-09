package uz.pdp.app_info_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_info_system.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long post_id);
}
