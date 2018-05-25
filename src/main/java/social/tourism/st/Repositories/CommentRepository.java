package social.tourism.st.Repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import social.tourism.st.Models.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
        List<Comment> findByMonumentAttachedTo(String monumentAttachedTo, Sort sort);
}
