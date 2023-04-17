package com.bridgelabz.googlekeep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.googlekeep.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {

}
