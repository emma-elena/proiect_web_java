package proiect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.Entity.MenuEntity;

import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, UUID> {
}
