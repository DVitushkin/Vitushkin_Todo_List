package DVitushkin.Vitushkin_Todo_List.dto.taskDto;

import DVitushkin.Vitushkin_Todo_List.exception.ErrorsMsg;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeStatusTodoDto {
    @NotNull(message = ErrorsMsg.TODO_STATUS_NOT_NULL)
    private Boolean status;
}
