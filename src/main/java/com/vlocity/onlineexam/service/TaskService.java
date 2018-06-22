package com.vlocity.onlineexam.service;

import com.vlocity.onlineexam.commons.AppMessageDefault;
import com.vlocity.onlineexam.commons.CustomException;
import com.vlocity.onlineexam.dto.request.TaskRequestDTO;
import com.vlocity.onlineexam.dto.response.ProjectResponseDTO;
import com.vlocity.onlineexam.dto.response.TaskResponseDTO;
import com.vlocity.onlineexam.model.ProjectModel;
import com.vlocity.onlineexam.model.TaskModel;
import com.vlocity.onlineexam.repository.ProjectRepository;
import com.vlocity.onlineexam.repository.TaskRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gfs on 22/06/2018.
 */
@Service
public class TaskService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ProjectRepository projectRepository;


  /**
   * Method to create new task
   */
  public TaskResponseDTO createNewTask(final TaskRequestDTO taskRequestDTO) {
    LOGGER.info(taskRequestDTO.toString());
    Optional<ProjectModel> project = projectRepository.findById(taskRequestDTO.getProjectId());
    if (!project.isPresent()) {
      throw new CustomException(
          String.format(AppMessageDefault.PROJECT_NOT_FOUND, taskRequestDTO.getProjectId()));
    }
    isStartDateValid(taskRequestDTO);
    isStartDateAndEndDateValid(taskRequestDTO);
    TaskModel model = convertToModel(taskRequestDTO, project.get());
    return buildResponseDTO(taskRepository.save(model));
  }

  /**
   * Method to get tasks by project id
   */
  public List<TaskResponseDTO> getByProjectId(final Long projectId) {
    List<TaskModel> tasks = taskRepository.findByProjectIdOrderByStartDateAsc(projectId);
    List<TaskResponseDTO> dto = new ArrayList<>();
    for (TaskModel m : tasks) {
      dto.add(buildResponseDTO(m));
    }
    return dto;
  }

  /**
   * Method to get the total duration of project based on tasks
   */
  public void getTotalTaskDuration(final ProjectResponseDTO project) {
    TaskModel startTask = taskRepository.findTop1ByProjectIdOrderByStartDateAsc(project.getId());
    TaskModel endTask = taskRepository.findTop1ByProjectIdOrderByEndDateDesc(project.getId());
    if (startTask != null
        && endTask != null) {
      project.setDuration(Days.daysBetween(new DateTime(startTask.getStartDate()),
          new DateTime(endTask.getEndDate())).getDays());
      project.setStartDate(startTask.getStartDate());
      project.setEndDate(endTask.getEndDate());
    }
  }


  private void isStartDateValid(final TaskRequestDTO dto) {
    if (dto.getDependencies() != null
        && dto.getDependencies().size() > 0) {
      TaskModel model = taskRepository
          .findTop1TaskModelByIdInOrderByEndDateDesc(dto.getDependencies());

      if (!dto.getStartDate().after(model.getEndDate())) {
        throw new CustomException(
            String.format(AppMessageDefault.TASK_SDATE_NOT_VALID, model.getTaskName()));
      }
    }
  }

  private void isStartDateAndEndDateValid(final TaskRequestDTO dto) {

    if (dto.getStartDate().after(dto.getEndDate())) {
      throw new CustomException(AppMessageDefault.TASK_SDATE_EDATE_NOT_VALID);
    }

  }


  private TaskModel convertToModel(final TaskRequestDTO dto, final ProjectModel project) {
    TaskModel taskModel = new TaskModel();
    taskModel.setProject(project);
    taskModel.setTaskName(dto.getName());
    taskModel.setStartDate(dto.getStartDate());
    taskModel.setEndDate(dto.getEndDate());
    taskModel.setDuration(computeDaysBetween(dto.getStartDate(), dto.getEndDate()));
    if (dto.getDependencies() != null) {
      taskModel.setDependencies(buildDependencyModel(dto.getDependencies()));
    }
    return taskModel;
  }

  private Set<TaskModel> buildDependencyModel(final List<Long> taskId) {
    Set<TaskModel> dependencies = new HashSet<>();
    for (Long id : taskId) {
      Optional<TaskModel> model = taskRepository.findById(id);
      if (!model.isPresent()) {
        throw new CustomException(String.format(AppMessageDefault.TASK_NOT_FOUND, id));
      }
      dependencies.add(model.get());
    }
    return dependencies;
  }

  private TaskResponseDTO buildResponseDTO(final TaskModel model) {
    TaskResponseDTO dto = new TaskResponseDTO.Builder()
        .name(model.getTaskName())
        .startDate(model.getStartDate())
        .id(model.getId())
        .endDate(model.getEndDate())
        .duration(model.getDuration())
        .build();
    if (model.getDependencies() != null) {
      dto.setDependencies(buildDependencyResponse(model.getDependencies()));
    }
    return dto;
  }

  private List<TaskResponseDTO> buildDependencyResponse(final Set<TaskModel> dependencies) {
    List<TaskResponseDTO> list = new ArrayList<>();
    for (TaskModel m : dependencies) {
      list.add(buildResponseDTO(m));
    }
    return list;
  }

  private Integer computeDaysBetween(final Date start, final Date end) {
    DateTime startDate = new DateTime(start);
    DateTime endDate = new DateTime(end);
    return Days.daysBetween(startDate, endDate).getDays();
  }
}
