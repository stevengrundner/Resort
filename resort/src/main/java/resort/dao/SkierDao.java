package resort.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import resort.entity.Skier;

public interface SkierDao extends JpaRepository<Skier, Long> {

}
