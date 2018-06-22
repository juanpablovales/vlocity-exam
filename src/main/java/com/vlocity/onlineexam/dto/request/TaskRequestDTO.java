package com.vlocity.onlineexam.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

/**
 * Created by gfs on 22/06/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRequestDTO {

  @JsonProperty("name")
  private String name;
  @JsonProperty("start_date")
  private Date startDate;
  @JsonProperty("end_date")
  private Date endDate;
  @JsonProperty("task_dependencies_id")
  private List<Long> dependencies;
  @JsonProperty("project_id")
  private Long projectId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @ApiModelProperty(example = "2018-06-22")
  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  @ApiModelProperty(example = "2018-06-22")
  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @ApiModelProperty(value = "List of task dependency id")
  public List<Long> getDependencies() {
    return dependencies;
  }

  public void setDependencies(List<Long> dependencies) {
    this.dependencies = dependencies;
  }

  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("TaskRequestDTO{");
    sb.append("name='").append(name).append('\'');
    sb.append(", startDate=").append(startDate);
    sb.append(", endDate=").append(endDate);
    sb.append(", dependencies=").append(dependencies);
    sb.append(", projectId=").append(projectId);
    sb.append('}');
    return sb.toString();
  }
}
