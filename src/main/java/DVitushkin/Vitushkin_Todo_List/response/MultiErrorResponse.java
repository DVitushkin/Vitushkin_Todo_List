package DVitushkin.Vitushkin_Todo_List.response;

import DVitushkin.Vitushkin_Todo_List.exception.AbstractErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
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
