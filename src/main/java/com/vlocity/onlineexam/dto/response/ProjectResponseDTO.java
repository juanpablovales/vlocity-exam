package com.vlocity.onlineexam.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;

/**
 * Created by gfs on 22/06/2018.
 */
@JsonInclude(Include.NON_NULL)
public class ProjectResponseDTO {

  @JsonProperty(value = "id")
  private Long id;
  @JsonProperty(value = "project_name")
  private String projectName;
  private Integer duration;
  @JsonProperty(value = "start_date")
  private Date startDate;
  @JsonProperty(value = "end_date")
  private Date endDate;
  private List<TaskResponseDTO> tasks;

  /**
   * Constructor with builder parameter
   */
  public ProjectResponseDTO(Builder builder) {
    this.id = builder.id;
    this.projectName = builder.name;
    this.duration = builder.duration;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public List<TaskResponseDTO> getTasks() {
    return tasks;
  }

  public void setTasks(List<TaskResponseDTO> tasks) {
    this.tasks = tasks;
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

  public static class Builder {

    private Long id;
    private String name;
    private Integer duration;
    private List<TaskResponseDTO> tasks;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder duration(Integer duration) {
      this.duration = duration;
      return this;
    }

    public Builder tasks(List<TaskResponseDTO> tasks) {
      this.tasks = tasks;
      return this;
    }


    public ProjectResponseDTO build() {
      return new ProjectResponseDTO(this);
    }


  }
}
