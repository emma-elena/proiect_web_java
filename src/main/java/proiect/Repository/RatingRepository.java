package proiect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.Entity.RatingEntity;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, UUID> {
    Optional<RatingEntity> findRatingById(UUID id);
}
