package DVitushkin.Vitushkin_Todo_List.dto.taskDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class GetPaginatedTaskDto {
    @Min(value = 1, message = "task page greater or equal 1")
    private int page;
    @Min(value = 1, message = "tasks per page greater or equal 1")
    @Max(value = 100, message = "tasks per page less or equal 100")
    private int perPage;
    private boolean status;
}
