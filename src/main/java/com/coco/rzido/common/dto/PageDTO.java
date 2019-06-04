package com.coco.rzido.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter @Setter
public class PageDTO {
	private int currentPage = 0;
	private int limit = 10;
	
	public Pageable getPageable(){
		return PageRequest.of(currentPage - 1, limit /*, Sort.by("id")*/);
	}

	public Pageable getPageable(Sort sort){
		return PageRequest.of(currentPage - 1, limit , sort);
	}
}
