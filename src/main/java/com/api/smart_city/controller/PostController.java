package com.api.smart_city.controller;

import com.api.smart_city.dto.post.AttachmentDTO;
import com.api.smart_city.dto.post.CategoryDTO;
import com.api.smart_city.dto.post.FeatureDTO;
import com.api.smart_city.dto.postRequest.CommentDTO;
import com.api.smart_city.dto.postRequest.FeedbackDTO;
import com.api.smart_city.service.PostService;
import com.api.smart_city.dto.postRequest.PostRequestDTO;
import com.api.smart_city.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @DeleteMapping(path = "/post/remove/{postId}")
    public ResponseEntity removePost(@PathVariable("postId") Long postId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/post/remove/{postId}").toUriString());
        try{
            postService.removePost(postId);
            return ResponseEntity.created(uri).body(new CustomResponse(true, List.of("remove"),"post id ${postId} deleted success"));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(false,null,ex.getMessage()));
        }
    }

    @GetMapping(path = "/post/{postId}")
    public ResponseEntity getPost(@PathVariable("postId") Long postId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/post/{postId}").toUriString());
        try{
            return ResponseEntity.created(uri).body(postService.getPost(postId));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(false,null,ex.getMessage()));
        }
    }
    @GetMapping(path = "/posts")
    public ResponseEntity getPosts(
            @RequestParam(required = false, name="userId") Long userId,
            @RequestParam(required = false, name = "page") Integer page,
            @RequestParam(required = false, name = "size") Integer size
    ){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/posts").toUriString());

        return ResponseEntity.created(uri).body(postService.getPosts(userId,page,size));
    }

    @PostMapping(path="/post/save")
    public ResponseEntity savePost(@RequestBody PostRequestDTO post) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/post/save").toUriString());
        try{
            return ResponseEntity.created(uri).body(postService.savePost(post));
        }catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse(false,null,exception.getMessage()));
        }
    }

    @PostMapping(path="/post/{postId}/update")
    public ResponseEntity updatePost(@PathVariable("postId") Long postId,@RequestBody PostRequestDTO post) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/post/{postId}/update").toUriString());
        try{
            return ResponseEntity.created(uri).body(postService.updatePost(postId,post));
        }catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse(false,null,exception.getMessage()));
        }
    }

    @PostMapping(path = "/post/{postId}/addComment")
    public ResponseEntity addComment(@PathVariable("postId") Long postId,@RequestBody CommentDTO comment) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/post/{postId}/addComment").toUriString());
        try{
            return ResponseEntity.created(uri).body(postService.addComment(postId,comment));
        }catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse(false,null,exception.getMessage()));
        }
    }

    @PostMapping(path = "/post/{postId}/addAttachments")
    public ResponseEntity addAttachments(@PathVariable("postId") Long postId,@RequestBody Collection<AttachmentDTO> attachmentDTOs) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/post/{postId}/addAttachments").toUriString());
        try{
            return ResponseEntity.created(uri).body(postService.addAttachments(postId,attachmentDTOs));
        }catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse(false,null,exception.getMessage()));
        }
    }

    @PostMapping(path = "/post/{postId}/addFeedback")
    public ResponseEntity addFeedback(@PathVariable("postId") Long postId, @RequestBody FeedbackDTO feedbackDTO) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/post/{postId}/addFeedback").toUriString());
        try{
            return ResponseEntity.created(uri).body(postService.addFeedback(postId,feedbackDTO));
        }catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse(false,null,exception.getMessage()));
        }
    }

    @PostMapping(path = "/category/add")
    public ResponseEntity saveCategory(@RequestBody CategoryDTO category){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/category/add").toUriString());
        try{
            return ResponseEntity.created(uri).body(new CategoryDTO(postService.addCategory(category)));
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse(false,null,exception.getMessage()));
        }

    }

    @PostMapping(path = "/feature/add")
    public ResponseEntity saveFeature(@RequestBody FeatureDTO feature){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/feature/add").toUriString());
        try{
            return ResponseEntity.created(uri).body(new FeatureDTO(postService.addFeature(feature)));
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse(false,null,exception.getMessage()));
        }
    }

}
