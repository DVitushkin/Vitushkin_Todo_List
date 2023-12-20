package DVitushkin.Vitushkin_Todo_List.response;

import DVitushkin.Vitushkin_Todo_List.exception.AbstractErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrResponse implements AbstractErrorResponse {
    private String errMsg;
    private long statusCode;
    private boolean success;

    public ErrResponse(String errMsg, long statusCode, boolean success) {
        this.errMsg = errMsg;
        this.statusCode = statusCode;
        this.success = success;
    }
}
