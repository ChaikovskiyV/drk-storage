package org.example.drkstorage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "customers")
@ToString(exclude = "documents")
public class CustomerEntity implements AbstractEntity{
  @Id
  private UUID id;
  private String name;
  @OneToMany(mappedBy = "document", cascade = {
      CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.REFRESH,
      CascadeType.PERSIST})
  @JsonIgnore
  private List<DocumentEntity> documents;
}
