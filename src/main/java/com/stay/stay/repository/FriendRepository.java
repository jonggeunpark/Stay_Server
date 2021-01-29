package com.stay.stay.repository;

import com.stay.stay.domain.Friend;
import com.stay.stay.dto.friend.FriendDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query(value = "SELECT *, rank()  over(ORDER BY is_private, current_record DESC) AS ranking\n" +
            "FROM\n" +
            "(\n" +
            "SELECT name, 'user' as table_name, profile_image, is_private, current_record FROM user\n" +
            "UNION\n" +
            "SELECT name, 'friend' as table_name, profile_image, is_private, current_record FROM friend\n" +
            "ORDER BY is_private, current_record DESC\n" +
            ") AS t", nativeQuery = true)
    List<RankInterface> findFriendByPrivateAndRecord(@Param("userId") Long userId);
}
