package uz.pdp.app_info_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_info_system.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
