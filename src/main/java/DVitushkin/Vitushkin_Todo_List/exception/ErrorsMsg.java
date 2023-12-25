package DVitushkin.Vitushkin_Todo_List.exception;

public interface ErrorsMsg {
    String ID_MUST_BE_POSITIVE = "ID must be grater than zero";
    String TODO_TEXT_NOT_NULL = "todo text not null";
    String TODO_TEXT_SIZE_NOT_VALID = "size not valid";
    String TODO_STATUS_NOT_NULL = "todo status not null";
    String TASK_NOT_FOUND = "task not found";
    String TASKS_PAGE_GREATER_OR_EQUAL_1 = "task page greater or equal 1";
    String TASKS_PER_PAGE_GREATER_OR_EQUAL_1 = "tasks per page greater or equal 1";
    String TASKS_PER_PAGE_LESS_OR_EQUAL_100 = "tasks per page less or equal 100";
    String HTTP_MESSAGE_NOT_READABLE_EXCEPTION = "Http request not valid";
}
