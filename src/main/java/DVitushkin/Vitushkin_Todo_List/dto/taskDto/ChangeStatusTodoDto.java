package DVitushkin.Vitushkin_Todo_List.dto.taskDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeStatusTodoDto {
    @NotNull(message = "todo status not null")
    private Boolean status;
}