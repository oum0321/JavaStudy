package com.book45.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class AlbumReviewPageDTO {

	List<AlbumReviewDTO> list;
	
	PageDTO pageInfo;
}
