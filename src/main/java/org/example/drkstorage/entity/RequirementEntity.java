package org.example.drkstorage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "requirements")
public class RequirementEntity implements AbstractEntity {
  @Id
  private UUID id;
  private String description;
  @OneToOne
  @JoinColumn(name = "file_id")
  private FileEntity file;
  @ManyToOne
  private DocumentEntity document;
  @ManyToOne
  private CustomerEntity customer;
}
