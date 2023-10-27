package resort.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import resort.entity.Run;

public interface RunDao extends JpaRepository<Run, Long> {

}
