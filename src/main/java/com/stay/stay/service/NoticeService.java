package com.stay.stay.service;

import com.stay.stay.domain.Notice;
import com.stay.stay.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    private void saveNotice(Notice notice) { noticeRepository.save(notice); }
}
