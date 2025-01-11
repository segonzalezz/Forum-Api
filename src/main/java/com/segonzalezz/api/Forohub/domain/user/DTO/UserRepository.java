package com.segonzalezz.api.Forohub.domain.user.DTO;

import com.segonzalezz.api.Forohub.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
    UserDetails findByEmail(String Email);
    Page<User> findByActiveTrue(Pageable pagination);
}
