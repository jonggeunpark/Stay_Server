
    /** 내 장소 상세조회 */
    @GetMapping("/{noticeIndex}")
    public ResponseEntity<Message> readNotice(@RequestHeader("userIndex") Long userId, @PathVariable("noticeIndex") Long noticeId) {
        NoticeDetailDto response = noticeService.readNotice(noticeId);

        Message message = new Message(StatusCode.OK, ResponseMessage.Read_notice, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
