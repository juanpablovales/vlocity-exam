package com.vlocity.onlineexam.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by gfs on 22/06/2018.
 */
@Entity
@Table(name = "task")
public class TaskModel extends AbstractBaseModel {

  @Column(name = "task_name")
  private String taskName;

  @Temporal(TemporalType.DATE)
  @Column(name = "start_date")
  private Date startDate;

  @Temporal(TemporalType.DATE)
  @Column(name = "end_date")
  private Date endDate;

  @Column(name = "duration")
  private Integer duration;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "project_id")
  private ProjectModel project = new ProjectModel();

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "task_dependencies",
      joinColumns = {@JoinColumn(name = "dependent_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "dependency_id", referencedColumnName = "id")})
  private Set<TaskModel> dependencies = new HashSet<>();

  @ManyToMany(mappedBy = "dependencies", cascade = CascadeType.ALL)
  private Set<TaskModel> dependents = new HashSet<>();

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
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

  public Set<TaskModel> getDependencies() {
    return dependencies;
  }

  public void setDependencies(Set<TaskModel> dependencies) {
    this.dependencies = dependencies;
  }

  public Set<TaskModel> getDependents() {
    return dependents;
  }

  public void setDependents(Set<TaskModel> dependents) {
    this.dependents = dependents;
  }

  public ProjectModel getProject() {
    return project;
  }

  public void setProject(ProjectModel project) {
    this.project = project;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }
}
