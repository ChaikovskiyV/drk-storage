package org.example.drkstorage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;
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
@Table(name = "files")
@ToString(exclude = {"requirement", "document"})
public class FileEntity {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator",
      parameters = @Parameter(
          name = "uuid_gen_strategy_class",
          value = "org.hibernate.id.uuid.CustomVersionOneStrategy"))
  private UUID id;
  private String name;
  private FileType fileType;
  private Instant uploadDate;
  private byte[] payload;

  @JsonIgnore
  @OneToOne(mappedBy = "file", fetch = FetchType.LAZY)
  private RequirementEntity requirement;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "document_id")
  private DocumentEntity document;

  @ManyToMany
  @JoinTable(
      name = "files_iterations",
      joinColumns = @JoinColumn(name = "file_id"),
      inverseJoinColumns = @JoinColumn(name = "iteration_id"))
  private Set<IterationEntity> iterations;
}
