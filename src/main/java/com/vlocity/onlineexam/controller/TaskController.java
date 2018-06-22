package com.vlocity.onlineexam.controller;

import com.vlocity.onlineexam.dto.request.TaskRequestDTO;
import com.vlocity.onlineexam.dto.response.TaskResponseDTO;
import com.vlocity.onlineexam.service.ProjectService;
import com.vlocity.onlineexam.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gfs on 22/06/2018.
 */
@RestController
@RequestMapping("/api")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @Autowired
  private ProjectService projectService;


  @ApiOperation(value = "Create new Tasks under a Project", response = TaskResponseDTO.class)
  @RequestMapping(value = "/projects/tasks", method = RequestMethod.POST)
  public ResponseEntity createNew(@RequestBody final TaskRequestDTO requestDTO) {
    TaskResponseDTO responseDTO = taskService.createNewTask(requestDTO);
    responseDTO.setProject(projectService.getProjectById(requestDTO.getProjectId(), false));
    return ResponseEntity.ok(responseDTO);
  }

}
