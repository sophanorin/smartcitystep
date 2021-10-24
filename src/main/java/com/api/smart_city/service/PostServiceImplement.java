package com.api.smart_city.service;

import com.api.smart_city.dto.post.*;
import com.api.smart_city.dto.postRequest.CommentDTO;
import com.api.smart_city.dto.postRequest.FeedbackDTO;
import com.api.smart_city.model.*;
import com.api.smart_city.repository.*;
import com.api.smart_city.dto.postRequest.PostRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServiceImplement implements PostService{
    private final PostRepository postRepository;
    private final CityRepository cityRepository;
    private final FeatureRepository featureRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public PageDTO getPosts(Long userId,Integer page,Integer size) {

        if(userId == null && page != null && size != null) {
            Page<Post> posts = postRepository.findAllByIsDeleted(false, PageRequest.of(page, size));
            return new PageDTO(posts);
        }
        else if(userId != null){
            if(page == null && size == null){
                Collection posts = postRepository.findAllByOwner_IdAndIsDeleted(userId,false);
                return new PageDTO(posts);
            }
            Page<Post> posts = postRepository.findAllByOwner_IdAndIsDeleted(userId,false, PageRequest.of(page, size));
            return new PageDTO(posts);
        }


        return new PageDTO(postRepository.findAllByIsDeleted(false));
    }

    @Override
    public PostDTO getPost(Long id) {
        Post post = postRepository
                .findByIdAndIsDeleted(id,false)
                .orElseThrow(() ->  new IllegalStateException("Post with id " + id + " does not exists"));

        PostDTO postTDO = new PostDTO(post);

        return postTDO;
    }

    @Override
    public PostDTO savePost(PostRequestDTO post) {
        Post _post = new Post();

        // fetch user
        Optional<User> owner = userRepository.findById(post.getOwnerId());

        // fetch a city
        City city = cityRepository.getById(post.getLocation().getCityId());

        city.getPosts().add(_post);

        // fetch all categories
        Collection<Category> categories  = categoryRepository.findAllById(post.getCategories());

        categories.forEach(category -> {
            category.getPosts().add(_post);
        });

        // fetch all features
        Collection<Feature> features = featureRepository
                .findAllById(post.getFeatures());

        features.forEach(feature -> {
            feature.getPosts().add(_post);
        });

        // Opening times
        Collection<OpeningTime> times = new ArrayList<>();
        post.getOpeningTimes().forEach(time -> {
            times.add(new OpeningTime(null,time.getFrom(),time.getTo(),_post));
        });

        // Attachments
        Collection<Attachment> attachments = new ArrayList<>();
        post.getAttachments().forEach(attachment ->{
           attachments.add(new Attachment(null,attachment.getTitle(),attachment.getUrl(),_post));
        });

        // location
        Location location = new Location(
                null,
                post.getLocation().getMapb_location(),
                _post
        );

        _post.setTitle(post.getTitle());
        _post.setDescription(post.getDescription());
        _post.setEmail(post.getEmail());
        _post.setPhoneNumber(post.getPhoneNumber());
        _post.setStatus(post.getStatus());
        _post.setClaimed(post.getClaimed());
        _post.setLocation(location);
        _post.setOwner(owner.get());
        _post.getFeatures().addAll(features);
        _post.getOpeningTimes().addAll(times);
        _post.getCategories().addAll(categories);
        _post.getAttachments().addAll(attachments);
        _post.setCity(city);


        return new PostDTO(postRepository.save(_post));

    }

    @Override
    public PostDTO updatePost(Long postId,PostRequestDTO post) {
        Post _post = postRepository
                .findByIdAndIsDeleted(postId,false)
                .orElseThrow(() ->  new IllegalStateException("Post with id " + postId + " does not exists"));

        // fetch all categories
        if(post.getCategories().size() > 0){
            Collection<Category> categories  = categoryRepository.findAllById(post.getCategories());
            categories.forEach(category -> {
                category.getPosts().add(_post);
            });
            _post.setCategories(categories);
        }

        // fetch all features
        if(post.getFeatures().size() > 0){
            Collection<Feature> features = featureRepository
                    .findAllById(post.getFeatures());
            features.forEach(feature -> {
                feature.getPosts().add(_post);
            });
            _post.setFeatures(features);
        }

        // Opening times
        if(post.getOpeningTimes().size() > 0) {

            Collection<OpeningTime> times = new ArrayList<>();
            post.getOpeningTimes().forEach(time -> {
                times.add(new OpeningTime(null, time.getFrom(), time.getTo(), _post));
            });
            _post.getOpeningTimes().clear();
            _post.getOpeningTimes().addAll(times);
        }
        // Attachments
        if(post.getAttachments().size() > 0) {
            Collection<Attachment> attachments = new ArrayList<>();
            post.getAttachments().forEach(attachment -> {
                attachments.add(new Attachment(null, attachment.getTitle(), attachment.getUrl(), _post));
            });
            _post.setAttachments(attachments);
        }

        _post.setTitle(post.getTitle());
        _post.setDescription(post.getDescription());
        _post.setEmail(post.getEmail());
        _post.setPhoneNumber(post.getPhoneNumber());
        _post.setStatus(post.getStatus());
        _post.setClaimed(post.getClaimed());
        _post.getLocation().setMapb_location(post.getLocation().getMapb_location());
        _post.setCity(cityRepository.findById(post.getLocation().getCityId()).get());

        return new PostDTO(_post);
    }

    @Override
    public void removePost(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Post with id " + id + " does not exists"));

        post.setIsDeleted(true);
    }

    @Override
    public PostDTO addComment(Long postId, CommentDTO commentDTO) {
        Post post = postRepository
                .findByIdAndIsDeleted(postId,false)
                .orElseThrow(() -> new IllegalStateException("Post with id " + postId + " does not exists"));

        User user = userRepository
                .findById(commentDTO.getCommentById())
                .orElseThrow(() -> new IllegalStateException("User with id " + commentDTO.getCommentById() + " does not exists"));

        post.getComments().add(new Comment(null,commentDTO.getComment(),user,post));

        return new PostDTO(postRepository.save(post));
    }

    @Override
    public PostDTO addAttachments(Long postId, Collection<AttachmentDTO> attachmentDTOs) {
        Post post = postRepository
                .findByIdAndIsDeleted(postId,false)
                .orElseThrow(() -> new IllegalStateException("Post with id " + postId + " does not exists"));

        Collection<Attachment> attachments = new ArrayList<>();

        attachmentDTOs.forEach(attachmentDTO -> {
            Attachment attachment = new Attachment(null,attachmentDTO.getTitle(),attachmentDTO.getUrl(),post);
            attachments.add(attachment);
        });

        post.getAttachments().addAll(attachments);

        return new PostDTO(postRepository.save(post));
    }

    @Override
    public PostDTO addFeedback(Long postId, FeedbackDTO feedbackDTO) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new IllegalStateException("Post with id " + postId + " does not exists"));

        User user = userRepository
                .findById(feedbackDTO.getFeedbackById())
                .orElseThrow(() -> new IllegalStateException("User with id " +feedbackDTO.getFeedbackById() + " does not exists"));

        post.getFeedbacks().add(new Feedback(null,feedbackDTO.getFeedback(),user,post));

        return new PostDTO(postRepository.save(post));
    }

    @Override
    public Category addCategory(CategoryDTO category) {
        Optional<Category> _category =  categoryRepository.findByCategory(category.getCategory());

        if(_category.isPresent())
            throw new IllegalStateException("Category name:" + category.getCategory() + " already existing");

        return categoryRepository.save(new Category(null,category.getCategory(),null));
    }

    @Override
    public Feature addFeature(FeatureDTO featureDTO) {
        Optional<Feature> feature = featureRepository.findByFeature(featureDTO.getFeature());

        if(feature.isPresent())
            throw new IllegalStateException("Feature name:" + featureDTO.getFeature() + " already existing");

        return featureRepository.save(new Feature(null,featureDTO.getFeature(),null));
    }
}
