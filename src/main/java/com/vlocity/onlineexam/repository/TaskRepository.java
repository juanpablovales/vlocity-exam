package com.vlocity.onlineexam.repository;

import com.vlocity.onlineexam.model.TaskModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by gfs on 22/06/2018.
 */
public interface TaskRepository extends JpaRepository<TaskModel, Long> {

  @Query(nativeQuery = true, value = "SELECT TOP 1 * FROM  task where id IN (:ids)"
      + " ORDER BY end_date desc")
  TaskModel findTop1TaskModelByIdInOrderByEndDateDesc(@Param("ids") List<Long> id);

  List<TaskModel> findByProjectIdOrderByStartDateAsc(final Long projectId);

  TaskModel findTop1ByProjectIdOrderByStartDateAsc(final Long projectId);

  TaskModel findTop1ByProjectIdOrderByEndDateDesc(final Long projectId);

}
