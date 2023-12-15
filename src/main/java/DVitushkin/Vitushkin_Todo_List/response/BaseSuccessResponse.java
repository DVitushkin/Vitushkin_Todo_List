package DVitushkin.Vitushkin_Todo_List.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BaseSuccessResponse {
    private int statusCode;
    private boolean success;
}
