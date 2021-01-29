package com.stay.stay.repository;

import com.stay.stay.domain.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StampRepository extends JpaRepository<Stamp, Long> {

    @Query(value = "select a.Date as d, exists (select * from stamp where acquired_date=d and user_id = 1) as success\n" +
            "from (\n" +
            "    select curdate() - INTERVAL (a.a + (10 * b.a) + (100 * c.a) + (1000 * d.a) ) DAY as Date\n" +
            "    from (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as a\n" +
            "    cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as b\n" +
            "    cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as c\n" +
            "    cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as d\n" +
            ") a\n" +
            "where a.Date between '2021-01-01' and LAST_DAY('2021-01-01') order by d asc", nativeQuery = true)
    List<CalendarInterface> findStampForCalendar(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);
}
