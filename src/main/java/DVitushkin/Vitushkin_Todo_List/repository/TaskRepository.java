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
    @Query(value = "UPDATE Task SET status = :status, updatedAt = current_timestamp()")
    void changeStatusForAll(@Param("status") boolean status);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Task WHERE status = :status")
    void deleteAllByStatus(boolean status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Task SET status = :status, updatedAt = current_timestamp() WHERE id = :id")
    void setStatusById(@Param("id") int id, @Param("status") boolean status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Task SET text = :text, updatedAt = current_timestamp() WHERE id = :id")
    void setTextById(@Param("id") int id, @Param("text") String  text);
}
