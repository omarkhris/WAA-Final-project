package com.miu.pmtbackendapi.repo.logging;

import com.miu.pmtbackendapi.domain.logging.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionEntityRepo extends JpaRepository<ExceptionEntity, Long> {
}
