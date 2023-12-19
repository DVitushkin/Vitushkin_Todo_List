package DVitushkin.Vitushkin_Todo_List.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseSuccessResponse {
    private int statusCode;
    private boolean success;
}
