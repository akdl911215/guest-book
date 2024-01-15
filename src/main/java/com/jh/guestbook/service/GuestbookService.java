package com.jh.guestbook.service;

import com.jh.guestbook.dto.GuestbookDTO;
import com.jh.guestbook.dto.PageRequestDTO;
import com.jh.guestbook.dto.PageResultDTO;
import com.jh.guestbook.entity.Guestbook;

public interface GuestbookService {

    GuestbookDTO read(Long gno);
    GuestbookDTO register(GuestbookDTO dto);

    GuestbookDTO remove(GuestbookDTO dto);

    GuestbookDTO modify(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

    default GuestbookDTO entityToDto(Guestbook entity) {
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .delDate(entity.getDelDate())
                .build();

        return dto;
    }
}
