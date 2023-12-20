package DVitushkin.Vitushkin_Todo_List.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrResponse extends BaseSuccessResponse{
    private String errMsg;

    public ErrResponse(String errMsg, long statusCode, boolean success) {
        super(statusCode, success);
        this.errMsg = errMsg;
    }
}
