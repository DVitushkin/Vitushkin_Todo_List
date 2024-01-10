package DVitushkin.Vitushkin_Todo_List.dto.taskDto;

import DVitushkin.Vitushkin_Todo_List.exception.ErrorsMsg;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangeTextTodoDto {
    @Size(min = 3, max = 160, message = ErrorsMsg.TODO_TEXT_SIZE_NOT_VALID)
    @NotNull(message = ErrorsMsg.TODO_TEXT_NOT_NULL)
    private String text;
}
