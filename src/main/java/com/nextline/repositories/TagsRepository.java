package com.nextline.repositories;

import com.nextline.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagsRepository extends JpaRepository<Tag, String>, JpaSpecificationExecutor<Tag> {

}