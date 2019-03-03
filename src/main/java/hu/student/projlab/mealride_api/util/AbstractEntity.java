package hu.student.projlab.mealride_api.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.converter.LocalDateTimeAttributeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class AbstractEntity implements Serializable {

    @JsonIgnore
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name="CREATION_DATE")
    private LocalDateTime creationDate;

    @JsonIgnore
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name="DELETION_DATE")
    private LocalDateTime deletionDate;


    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(LocalDateTime deletionDate) {
        this.deletionDate = deletionDate;
    }
}
