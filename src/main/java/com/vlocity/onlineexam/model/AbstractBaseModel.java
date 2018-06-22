package com.vlocity.onlineexam.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.joda.time.DateTime;

/**
 * Created by gfs on 22/06/2018.
 */
@MappedSuperclass
public abstract class AbstractBaseModel implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created", nullable = false, insertable = false, updatable = false,
      columnDefinition = "datetime default CURRENT_TIMESTAMP")
  private Calendar createdDate = DateTime.now().toCalendar(Locale.getDefault());

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated", nullable = false,
      columnDefinition = "datetime default CURRENT_TIMESTAMP")
  private Calendar updatedDate = DateTime.now().toCalendar(Locale.getDefault());

  @PrePersist
  protected void onCreate() {
    updatedDate = createdDate = DateTime.now().toCalendar(Locale.getDefault());
  }

  @PreUpdate
  protected void onUpdate() {
    updatedDate = DateTime.now().toCalendar(Locale.getDefault());
  }

  public Calendar getCreatedDate() {
    return createdDate;
  }

  public Calendar getUpdatedDate() {
    return updatedDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


}
