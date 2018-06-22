package com.vlocity.onlineexam.repository;

import com.vlocity.onlineexam.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gfs on 22/06/2018.
 */
public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {

}
