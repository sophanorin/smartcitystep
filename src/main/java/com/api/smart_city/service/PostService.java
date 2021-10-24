package com.api.smart_city.service;

import com.api.smart_city.dto.post.*;
import com.api.smart_city.dto.postRequest.CommentDTO;
import com.api.smart_city.dto.postRequest.FeedbackDTO;
import com.api.smart_city.dto.postRequest.PostRequestDTO;
import com.api.smart_city.model.Category;
import com.api.smart_city.model.Feature;

import java.util.Collection;

public interface PostService {
    PageDTO getPosts(Long userId,Integer page,Integer size);
    PostDTO getPost(Long id);
    PostDTO savePost(PostRequestDTO post);
    PostDTO updatePost(Long postId,PostRequestDTO post);
    void removePost(Long id);
    PostDTO addComment(Long postId, CommentDTO commentDTO);
    PostDTO addAttachments(Long postId, Collection<AttachmentDTO> AttachmentDTO);
    PostDTO addFeedback(Long postId, FeedbackDTO feedbackDTO);
    Category addCategory(CategoryDTO category);
    Feature addFeature(FeatureDTO featureDTO);
}
