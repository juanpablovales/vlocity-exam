package com.vlocity.onlineexam.service;

import com.vlocity.onlineexam.commons.AppMessageDefault;
import com.vlocity.onlineexam.commons.CustomException;
import com.vlocity.onlineexam.dto.request.ProjectRequestDTO;
import com.vlocity.onlineexam.dto.response.ProjectResponseDTO;
import com.vlocity.onlineexam.dto.response.TaskResponseDTO;
import com.vlocity.onlineexam.model.ProjectModel;
import com.vlocity.onlineexam.repository.ProjectRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gfs on 22/06/2018.
 */
@Service
@Transactional
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private TaskService taskService;

  /**
   * Method to save new project
   */
  public ProjectResponseDTO saveProject(final ProjectRequestDTO request) {
    ProjectModel model = convertToModel(request);
    model = projectRepository.save(model);
    ProjectResponseDTO response = new ProjectResponseDTO.Builder()
        .id(model.getId())
        .name(model.getName())
        .build();
    return response;
  }

  /**
   * Method to display project details
   *
   * @param id - project id
   * @param showAll - boolean flag for determining if tasks will be included in response
   */
  public ProjectResponseDTO getProjectById(final Long id, boolean showAll) {
    Optional<ProjectModel> model = projectRepository.findById(id);
    if (model.isPresent()) {
      ProjectResponseDTO response = new ProjectResponseDTO.Builder()
          .id(model.get().getId())
          .name(model.get().getName())
          .build();
      if (showAll) {
        List<TaskResponseDTO> tasks = taskService.getByProjectId(id);
        response.setTasks(tasks);
        taskService.getTotalTaskDuration(response);
      }
      return response;
    }
    throw new CustomException(String.format(AppMessageDefault.PROJECT_NOT_FOUND, id));
  }


  private ProjectModel convertToModel(ProjectRequestDTO request) {
    ProjectModel model = new ProjectModel();
    model.setName(request.getName());
    return model;
  }

}
