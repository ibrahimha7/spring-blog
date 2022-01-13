package com.example.blog.AUTH;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findRoleById (Long id);
}
