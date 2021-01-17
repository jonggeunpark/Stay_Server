package com.stay.stay.service;

import com.stay.stay.domain.Notice;
import com.stay.stay.dto.notice.NoticeDetailDto;
import com.stay.stay.dto.notice.NoticeDto;
import com.stay.stay.exception.NotFoundException;
import com.stay.stay.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    private void saveNotice(Notice notice) { noticeRepository.save(notice); }

    private Notice findById(Long noticeId) { return noticeRepository.findById(noticeId).orElseThrow(()-> new NotFoundException("Notice Not Found")); }

    public List<NoticeDto> readNoticeAll(Long userId) {

        List<NoticeDto> response = new ArrayList<>();
        List<Notice> noticeList = noticeRepository.findAll();

        for(Notice notice: noticeList) {
            NoticeDto noticeDto = NoticeDto.builder()
                    .id(notice.getId())
                    .title(notice.getTitle())
                    .createdDate(notice.getCreated_date())
                    .build();

            response.add(noticeDto);
        }

        return response;
    }
}
