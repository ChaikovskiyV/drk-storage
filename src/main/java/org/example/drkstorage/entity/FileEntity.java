package org.example.drkstorage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "files")
public class FileEntity {
  @Id
  private UUID id;
  private FileType fileType;
  private Instant uploadDate;
  private byte[] payload;

  @OneToOne(mappedBy = "file")
  private RequirementEntity requirement;

  @ManyToOne
  @JoinColumn(name = "document_id")
  private DocumentEntity document;

  @ManyToMany
  @JoinTable(
      name = "files_iterations",
      joinColumns = @JoinColumn(name = "file_id"),
      inverseJoinColumns = @JoinColumn(name = "iteration_id"))
  private List<IterationEntity> iterations;
}
