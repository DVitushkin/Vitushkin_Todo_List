package DVitushkin.Vitushkin_Todo_List.response;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CustomSuccessResponse<T> extends BaseSuccessResponse {
    private T data;

    public CustomSuccessResponse(T data, long statusCode, boolean success) {
        super(statusCode, success);
        this.data = data;
    }
}
