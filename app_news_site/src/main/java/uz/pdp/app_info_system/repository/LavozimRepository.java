package uz.pdp.app_info_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_info_system.entity.Lavozim;

import java.util.Optional;

public interface LavozimRepository extends JpaRepository<Lavozim, Long> {


    Optional<Lavozim> findByName(String name);
}
