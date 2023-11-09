package com.dongyang.core.domain.favorite.repository;

import com.dongyang.core.domain.favorite.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
