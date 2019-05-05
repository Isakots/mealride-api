package hu.student.projlab.mealride_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.domain.converter.LocalDateTimeAttributeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @JsonIgnore
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name="CREATION_DATE")
    private LocalDateTime creationDate;

    @JsonIgnore
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name="DELETION_DATE")
    private LocalDateTime deletionDate;

}
