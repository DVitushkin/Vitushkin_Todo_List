package DVitushkin.Vitushkin_Todo_List.exception;

import lombok.Getter;

@Getter
public enum ErrorsCode {
    ID_MUST_BE_POSITIVE(29, "ID must be grater than zero"),
    TODO_TEXT_NOT_NULL(31, "todo text not null"),
    TODO_TEXT_SIZE_NOT_VALID(32, "size not valid"),
    TODO_STATUS_NOT_NULL(33, "todo status not null"),
    TASK_NOT_FOUND(34, "task not found"),
    TASKS_PAGE_GREATER_OR_EQUAL_1(37, "task page greater or equal 1"),
    TASKS_PER_PAGE_GREATER_OR_EQUAL_1(38, "tasks per page greater or equal 1"),
    TASKS_PER_PAGE_LESS_OR_EQUAL_100(39, "tasks per page less or equal 100"),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION(47, "Http request not valid");

    private final Integer statusCode;
    private final String msg;

    ErrorsCode(Integer statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    public static Integer getErrCodeByErrMsg(String msg) {
        for (ErrorsCode e : ErrorsCode.values()) {
            if (e.msg.equals(msg)) {
                return e.getStatusCode();
            }
        }
        return 0;
    }
}
