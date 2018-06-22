package com.vlocity.onlineexam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by gfs on 22/06/2018.
 */
@Entity
@Table(name = "project")
public class ProjectModel extends AbstractBaseModel {

  @Column(name = "name")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
