package com.book45.mapper;

import java.util.List;

import com.book45.domain.AlbumVO;
import com.book45.domain.Criteria;

public interface AlbumMapper {

	//public List<AlbumVO> getList();
	
	public List<AlbumVO> getListWithPaging(Criteria cri);
	
	public void insert(AlbumVO album);
	
	public void insertSelectKey(AlbumVO album);
	
	public AlbumVO read(Long productNum);
	
	public int delete(Long productNum);
	
	public int update(AlbumVO album);
	
	public int getTotalCount(Criteria cri);
	
	public AlbumVO getProductNumName(Long productNum); // 댓글추가부
	
	public List<AlbumVO> getCategory(String Category);
	
////	이미지 등록
//	public void imageEnroll(AlbumImageVO vo);
//	
//	/* 지정 상품 이미지 전체 삭제 */
//	public void deleteImageAll(Long productNum);
//	
//	/* 어제자 날짜 이미지 리스트 */
//	public List<AlbumImageVO> checkFileList();
//	
//	/* 지정 상품 이미지 정보 얻기 */
//	public List<AlbumImageVO> getAlbumImageInfo(Long productNum);
	
}
