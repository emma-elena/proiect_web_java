package proiect.Service;

import org.springframework.stereotype.Service;
import proiect.Entity.RatingEntity;
import proiect.Exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import proiect.Repository.RatingRepository;

import java.util.UUID;

@Service
public class RatingService {

    @Autowired
    private RatingRepository repository;

    public RatingEntity create(RatingEntity rating){
        repository.save(rating);
        return rating;
    }

    public void delete(UUID id) {
        RatingEntity rating = repository.findRatingById(id).orElseThrow(() -> new NotFound("Rating not found"));
        repository.delete(rating);
    }
}
