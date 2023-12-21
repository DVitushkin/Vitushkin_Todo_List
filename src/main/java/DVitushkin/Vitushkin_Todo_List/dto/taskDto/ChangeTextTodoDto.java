package DVitushkin.Vitushkin_Todo_List.dto.taskDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangeTextTodoDto {
    @Size(min = 3, max = 160, message = "size not valid")
    @NotNull(message = "todo text not null")
    private String text;
}
