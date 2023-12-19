package DVitushkin.Vitushkin_Todo_List.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTodoDto {
    @Size(min = 3, max = 160)
    private String text;
}
