package com.stay.stay.repository;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public interface CalendarInterface {

    Date getD();
    Integer getSuccess();

}
