package com.vlocity.onlineexam.controller;

import com.vlocity.onlineexam.dto.request.ProjectRequestDTO;
import com.vlocity.onlineexam.dto.response.ProjectResponseDTO;
import com.vlocity.onlineexam.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gfs on 22/06/2018.
 */
@RestController
@RequestMapping("/api")
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @ApiOperation(value = "Create new Project", response = ProjectResponseDTO.class)
  @RequestMapping(value = "/projects", method = RequestMethod.POST)
  public ResponseEntity createNew(@RequestBody final ProjectRequestDTO requestDTO) {
    return ResponseEntity.ok(projectService.saveProject(requestDTO));
  }

  @ApiOperation(value = "View a details of a Project", response = ProjectResponseDTO.class)
  @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
  public ResponseEntity createNew(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(projectService.getProjectById(id, true));
  }

}
