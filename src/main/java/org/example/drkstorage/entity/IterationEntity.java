package org.example.drkstorage.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "iterations")
public class IterationEntity implements AbstractEntity {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator",
      parameters = @Parameter(
          name = "uuid_gen_strategy_class",
          value = "org.hibernate.id.uuid.CustomVersionOneStrategy"))
  private UUID id;
  private String number;
  private String reason;
  private String description;

  @ManyToMany(mappedBy = "iteration", cascade = {
      CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH})
  private Set<FileEntity> files;

  @ManyToMany
  @JoinTable(
      name = "iterations_developers",
      joinColumns = @JoinColumn(name = "iteration_id"),
      inverseJoinColumns = @JoinColumn(name = "developer_id"))
  private Set<DeveloperEntity> developers;
}
