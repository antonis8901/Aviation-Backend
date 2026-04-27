package org.example.aviation.domain.repositories;

import org.example.aviation.domain.models.entities.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchesLogRepository extends JpaRepository<SearchLog, Long> {

}
