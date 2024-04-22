package org.example.drkstorage.dto;

import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CustomerDto implements AbstractDto {
  @Pattern(regexp = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}",
      message = "The id should be like that: 46e0d4b4-ac93-ee4c-d13e-cc0f5048283d")
  private UUID id;
  @NonNull
  @Length(min = 3, max = 50, message = "The length of the customer name has to be from 3 to 50")
  private String name;
}
