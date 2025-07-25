package com.Niharika.social.repository;

import com.Niharika.social.models.Reels;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {
    public List<Reels> findByUserId(Integer Id);
}
