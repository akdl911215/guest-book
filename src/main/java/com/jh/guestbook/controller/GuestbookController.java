package com.jh.guestbook.controller;

import com.jh.guestbook.dto.GuestbookDTO;
import com.jh.guestbook.dto.PageRequestDTO;
import com.jh.guestbook.dto.PageResultDTO;
import com.jh.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/guestbook", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 Annotation
public class GuestbookController {

    private final GuestbookService service;

//    @GetMapping("/")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

//        return "redirect:/guestbook/list";

        return "Hello World";
    }

    @GetMapping("/list")
    public PageResultDTO list(PageRequestDTO pageRequestDTO) {
        log.info("list........." + pageRequestDTO);

        PageResultDTO response =  service.getList(pageRequestDTO);
        log.info("response : "  + response);

        return response;
    }

    @GetMapping("/read")
    public GuestbookDTO read(@RequestParam(value = "gno") long gno) {
        log.info("read gno : " + gno);

        GuestbookDTO response = service.read(gno);

        log.info("response : " + response);

        return response;
    }

    @PostMapping("/register")
    public GuestbookDTO registerPost(GuestbookDTO dto) {

        log.info("dto.... " + dto);

        // 새로 추가된 엔티티의 번호
        GuestbookDTO response = service.register(dto);

        return response;
    }
}
