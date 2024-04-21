package org.example.drkstorage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "developers")
@ToString(exclude = "iterations")
public class DeveloperEntity implements AbstractEntity {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator",
      parameters = @Parameter(
          name = "uuid_gen_strategy_class",
          value = "org.hibernate.id.uuid.CustomVersionOneStrategy"))
  private UUID id;
  private DeveloperRole role;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @JsonIgnore
  @ManyToMany(mappedBy = "developer", cascade = {
      CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})
  private Set<IterationEntity> iterations;
}
