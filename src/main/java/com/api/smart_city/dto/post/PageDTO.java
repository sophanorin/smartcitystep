package com.api.smart_city.dto.post;

import com.api.smart_city.model.Post;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PageDTO {
    private Collection<PostDTO> posts = new ArrayList<>();
    private Integer totalPages;
    private Integer currentPage;
    private Integer totalElements;

    public PageDTO(Page page) {
        page.getContent().forEach(post -> posts.add(new PostDTO((Post) post)));
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getPageable().getPageNumber();
        this.totalElements = (int)page.getTotalElements();
    }

    public PageDTO(Collection<Post> _posts) {
        this.totalPages = 0;
        this.currentPage = 0;
        this.totalElements = 0;
        if(_posts.size() > 0 || _posts != null){
            this.totalElements = _posts.size();
            _posts.forEach(post -> {
                posts.add(new PostDTO(post));
            });
        }

    }

}
