package org.example.drkstorage.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "documents")
public class DocumentEntity implements AbstractEntity {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator",
      parameters = @Parameter(
          name = "uuid_gen_strategy_class",
          value = "org.hibernate.id.uuid.CustomVersionOneStrategy"))
  private UUID id;
  private String name;
  private String article;
  private String description;
  private Instant created;
  private UUID partOf;

  @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
  private Set<RequirementEntity> requirements;

  @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
  private Set<FileEntity> files;
}
