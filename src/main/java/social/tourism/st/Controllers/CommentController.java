package social.tourism.st.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import social.tourism.st.Models.Comment;
import social.tourism.st.Repositories.CommentRepository;
import social.tourism.st.Repositories.HistoricalSpotRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentRepository commentRepository;
    private HistoricalSpotRepository historicalSpotRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, HistoricalSpotRepository historicalSpotRepository){
        this.commentRepository = commentRepository;
        this.historicalSpotRepository = historicalSpotRepository;

    }

    // get all the comments
    @RequestMapping(method = RequestMethod.GET)
    public List<Comment> getAll() {
        return commentRepository.findAll(new Sort(Sort.Direction.DESC, "date"));
    }

    // add one
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> add(@Validated @RequestBody Comment comment){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (historicalSpotRepository.findByName(comment.getMonumentAttachedTo()) == null ) {
            System.out.println("this monument doesn't exist ");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
            Comment model = new Comment();
            model.setMonumentAttachedTo(comment.getMonumentAttachedTo());
            model.setAuthor(authentication.getName());
            model.setContent(comment.getContent());
            model.setDate(LocalDateTime.now().toString());
            commentRepository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    // find all the monuments in one comune
    @RequestMapping(value="/{monument}" ,method = RequestMethod.GET)
    public List<Comment> findByComune(@PathVariable String monument){
        return commentRepository.findByMonumentAttachedTo(monument, new Sort(Sort.Direction.DESC, "date"));
    }



}
