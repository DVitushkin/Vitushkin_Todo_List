package DVitushkin.Vitushkin_Todo_List.dto.taskDto;

import java.util.List;

import DVitushkin.Vitushkin_Todo_List.models.Task;
import lombok.Data;

@Data
public class GetNewsDto {
    private List<Task> content;
    private Integer notReady;
    private Integer numberOfElements;
    private Integer ready;
}
