package DVitushkin.Vitushkin_Todo_List.dto.taskDto;

import DVitushkin.Vitushkin_Todo_List.exception.ErrorsMsg;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetPaginatedTaskDto {
    @Min(value = 1, message = ErrorsMsg.TASKS_PAGE_GREATER_OR_EQUAL_1)
    private Integer page;
    @Min(value = 1, message = ErrorsMsg.TASKS_PER_PAGE_GREATER_OR_EQUAL_1)
    @Max(value = 100, message = ErrorsMsg.TASKS_PER_PAGE_LESS_OR_EQUAL_100)
    private Integer perPage;
    @NotNull(message = ErrorsMsg.TODO_STATUS_NOT_NULL)
    private Boolean status;
}
