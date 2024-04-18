package org.example.drkstorage.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "documents")
public class DocumentEntity implements AbstractEntity {
  @Id
  private UUID id;
  private String name;
  private String article;
  private String description;
  private Instant created;
  private UUID partOf;

  @OneToMany
  @JoinColumn(name = "requirement_id")
  private List<RequirementEntity> requirements;

  @OneToMany(mappedBy = "document")
  private List<FileEntity> files;
}
