package com.api.smart_city.repository;

import com.api.smart_city.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long>, PagingAndSortingRepository<Post,Long> {
    Optional<Post> findByIdAndIsDeleted(Long id, Boolean isDeleted);
    Page<Post> findAllByIsDeleted(Boolean isDeleted,Pageable pageable);
    Page<Post> findAllByOwner_IdAndIsDeleted(Long id,Boolean isDeleted,Pageable pageable);
    Collection<Post> findAllByOwner_IdAndIsDeleted(Long id,Boolean isDeleted);
    Collection<Post> findAllByIsDeleted(Boolean isDeleted);

}
