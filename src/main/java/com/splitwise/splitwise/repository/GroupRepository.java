
package com.splitwise.splitwise.repository;

import com.splitwise.splitwise.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByMembersId(Long userId);
}