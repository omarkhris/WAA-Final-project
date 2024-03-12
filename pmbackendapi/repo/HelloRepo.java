package com.miu.pmtbackendapi.repo;

import com.miu.pmtbackendapi.domain.Hello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepo extends JpaRepository<Hello,Long> {
}
