package DVitushkin.Vitushkin_Todo_List.repository;

import DVitushkin.Vitushkin_Todo_List.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Transactional
    @Query(value ="UPDATE tasks SET status = :status", nativeQuery = true)
    void changeStatusForAll(@Param("status") boolean status);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tasks WHERE status = :status", nativeQuery = true)
    void deleteAllByStatus(boolean status);
}