package DVitushkin.Vitushkin_Todo_List.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomSuccessResponse<T> extends BaseSuccessResponse {
    private T data;

    public CustomSuccessResponse(int statusCode, boolean success, T data) {
        super(statusCode, success);
        this.data = data;
    }
}
