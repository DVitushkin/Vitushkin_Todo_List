package DVitushkin.Vitushkin_Todo_List.response;

import lombok.*;

@Data
@AllArgsConstructor
public class BaseSuccessResponse {
    protected   long statusCode;
    protected   boolean success;
}
