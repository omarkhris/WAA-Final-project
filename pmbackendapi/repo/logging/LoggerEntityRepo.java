package com.miu.pmtbackendapi.repo.logging;

import com.miu.pmtbackendapi.domain.logging.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerEntityRepo extends JpaRepository<LoggerEntity, Long> {
}
