package com.vlocity.onlineexam.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

/**
 * Created by gfs on 22/06/2018.
 */
@JsonInclude(Include.NON_NULL)
public class TaskResponseDTO {

  @JsonProperty("task_name")
  private String name;
  private ProjectResponseDTO project;
  @JsonProperty("task_dependencies")
  private List<TaskResponseDTO> dependencies;
  @JsonProperty("start_date")
  private Date startDate;
  @JsonProperty("end_date")
  private Date endDate;
  private int duration;
  private Long id;

  /**
   * Constructor with builder parameter
   */
  public TaskResponseDTO(Builder builder) {
    this.name = builder.name;
    this.project = builder.project;
    this.dependencies = builder.dependencies;
    this.startDate = builder.startDate;
    this.endDate = builder.endDate;
    this.duration = builder.duration;
    this.id = builder.id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProjectResponseDTO getProject() {
    return project;
  }

  public void setProject(ProjectResponseDTO project) {
    this.project = project;
  }

  public List<TaskResponseDTO> getDependencies() {
    return dependencies;
  }

  public void setDependencies(
      List<TaskResponseDTO> dependencies) {
    this.dependencies = dependencies;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public static class Builder {

    private String name;
    private ProjectResponseDTO project;
    private List<TaskResponseDTO> dependencies;
    private Date startDate;
    private Date endDate;
    private int duration;
    private Long id;


    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder project(ProjectResponseDTO project) {
      this.project = project;
      return this;
    }

    public Builder dependencies(List<TaskResponseDTO> dependencies) {
      this.dependencies = dependencies;
      return this;
    }

    public Builder startDate(Date startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder endDate(Date endDate) {
      this.endDate = endDate;
      return this;
    }

    public Builder duration(int duration) {
      this.duration = duration;
      return this;
    }

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public TaskResponseDTO build() {
      return new TaskResponseDTO(this);
    }


  }
}
