package com.amagana.settingsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amagana.settingsservice.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
