package com.example.atomikosjta.repository.log;

import com.example.atomikosjta.entity.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-08 16:28
 */
public interface LogDao extends JpaRepository<Log, Integer> {
}
