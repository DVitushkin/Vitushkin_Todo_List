package DVitushkin.Vitushkin_Todo_List.dto;

import DVitushkin.Vitushkin_Todo_List.models.Task;
import lombok.Data;

import java.util.List;

@Data
public class GetNewsDto {
    private List<Task> content;
    private int notReady;
    private int numberOfElements;
    private int ready;
}
