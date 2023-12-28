package com.miics.server.dao.repositories;

import com.miics.server.dao.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ResultRepositoryBean")
public interface IResultRepository extends JpaRepository<Result, Long> {
    List<Result> findAllByIsuNumber(Long isuNumber);
    List<Result> findAllByTestId(Long testId);


    Result findByTestIdAndIsuNumber(Long testId, Long isuNumber);

}