package DVitushkin.Vitushkin_Todo_List.response;

import java.util.List;

import DVitushkin.Vitushkin_Todo_List.exception.AbstractErrorResponse;
import lombok.Data;

@Data
public class MultiErrorResponse implements AbstractErrorResponse {
    private List<String> errsMsgs;
    private List<Long> codes;
    private boolean success;

    public MultiErrorResponse(List<String> errsMsgs, List<Long> codes, boolean success) {
        this.errsMsgs = errsMsgs;
        this.codes = codes;
        this.success = success;
    }
}
