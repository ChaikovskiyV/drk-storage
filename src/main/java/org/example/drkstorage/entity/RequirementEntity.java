package org.example.drkstorage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "requirements")
public class RequirementEntity implements AbstractEntity {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator",
      parameters = @Parameter(
          name = "uuid_gen_strategy_class",
          value = "org.hibernate.id.uuid.CustomVersionOneStrategy"))
  private UUID id;
  private String description;

  @OneToOne
  @JoinColumn(name = "file_id")
  private FileEntity file;

  @ManyToOne
  @JoinColumn(name = "document_id")
  private DocumentEntity document;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;
}
