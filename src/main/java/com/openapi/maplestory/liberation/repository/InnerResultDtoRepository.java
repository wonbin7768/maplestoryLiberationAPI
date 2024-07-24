package com.openapi.maplestory.liberation.repository;

import com.openapi.maplestory.liberation.domain.entity.ResultInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnerResultDtoRepository  extends JpaRepository<ResultInfo , Long> {
}
