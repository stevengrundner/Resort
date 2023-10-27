package resort.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import resort.entity.Resort;

public interface ResortDao extends JpaRepository<Resort, Long> {

}
