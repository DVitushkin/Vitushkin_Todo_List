package DVitushkin.Vitushkin_Todo_List.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseSuccessResponse {
    private Integer statusCode;
    private Boolean success;

    public static BaseSuccessResponse ok() {
        return BaseSuccessResponse.builder()
                .statusCode(1)
                .success(true)
                .build();
    }
}
