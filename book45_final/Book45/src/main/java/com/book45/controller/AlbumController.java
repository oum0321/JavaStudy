package com.book45.controller;



import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.book45.domain.AlbumReviewDTO;
import com.book45.domain.AlbumVO;
import com.book45.domain.Criteria;
import com.book45.domain.PageDTO;

import com.book45.service.AlbumReviewService;
import com.book45.service.AlbumService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/album/*")
@AllArgsConstructor
public class AlbumController {

	@Autowired
	private AlbumService service;
	
	@Autowired
	private AlbumReviewService reviewService;
	
//	@Autowired
//	private AlbumImageMapper imageMapper;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model){
		
		log.info("list : " +cri);
		
		model.addAttribute("list", service.getList(cri));	
		
		
		int total = service.getTotal(cri);
		log.info("total : " +total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	
	@GetMapping("/register")
	public void register() {}
	
	
	@PostMapping("/register")
	public String register(AlbumVO album, RedirectAttributes rttr) {
		
		log.info("register : "+album);
		
//		service.register(album);
		
		rttr.addFlashAttribute("result : " + album.getNum());
		
		return "redirect:/album/list";
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("productNum") Long productNum, @ModelAttribute("cri") Criteria cri, Model model) {
		
		log.info("/get 혹은 /modify 동작 : ");
		
		AlbumVO album = service.get(productNum);

		model.addAttribute("album", album);
		
	}
	
	@PostMapping("/modify")
	public String modify(AlbumVO album, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("Modify : " +album);
		
		if(service.modify(album)) {
			rttr.addFlashAttribute("result", "modify");
		}
		
		//log.info("Modify : " +album);
		return "redirect:/album/list" + cri.getListLink();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("productNum") Long productNum, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("remove..." +productNum);
			
		if(service.remove(productNum)) {
			rttr.addFlashAttribute("result", "delete");
		}
		
		return "redirect:/album/list" +cri.getListLink();
		
	}
	
	/* 리뷰 쓰기 */
	@GetMapping("/albumReviewEnroll/{id}")
	public String albumReviewEnrollWindowGET(@PathVariable("id")String id, Long productNum, Model model) {
	
			AlbumVO album = service.getProductNumName(productNum);
			model.addAttribute("album", album);
			model.addAttribute("id", id);
			
			return "/album/albumReviewEnroll";
			
	}
	

	/* 리뷰 수정 팝업창 */
	@GetMapping("/albumReviewUpdate")
	public String albumReviewUpdateWindowGET(AlbumReviewDTO dto, Model model) {
		
			AlbumVO album = service.getProductNumName(dto.getProductNum());
			model.addAttribute("album", album);
			model.addAttribute("albumReview", reviewService.getUpdateAlbumReview(dto.getNum()));
			model.addAttribute("id", dto.getId());
			
			return "/album/albumReviewUpdate";
	}
	
	@RequestMapping(value = "/categoryList/{catNum}", method = RequestMethod.GET)
	public String getCategoryInfo(@PathVariable("catNum") int catNum, HttpSession session, Criteria cri, Model model) {
		log.info("카테고리 선택 시 동작");

		String catName = "";
		
		switch(catNum) { case 1: catName = "Blu-ray"; break; case 2: catName = "J-POP"
		; break; case 3: catName = "LP(Vinyl) 전문관"; break; case 4: catName = "OST";
		break; case 5: catName = "POP"; break; case 6: catName = "가요"; break; case
		7: catName = "뮤직 DVD"; break; case 8: catName = "스타샵"; break; case 9:
		catName = "스페셜샵"; break; case 10: catName = "예약CD/LP"; break; case 11: catName =
		"재즈"; break; case 12: catName = "클래식"; break; }
		

		List<AlbumVO> albumCateList = service.getCategory(catName);
		model.addAttribute("category", albumCateList);
		int total = service.getTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		session.setAttribute("category", catName);
		albumCateList.forEach(n -> log.info(n));
		return "/album/categoryList";
	}
	
//	/*첨부 파일 업로드*/
//	@PostMapping(value="/uploadFile", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<AlbumImageVO>> upload(MultipartFile[] albumPictureUrl) {
//		
//		/* 이미지 파일 체크 */
//		for(MultipartFile multipartFile: albumPictureUrl) {
//			
//			File checkfile = new File(multipartFile.getOriginalFilename());
//			String type = null;
//			
//			try {
//				type = Files.probeContentType(checkfile.toPath());
//				log.info("MIME TYPE: " + type);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			if(!type.startsWith("image")) {
//				List<AlbumImageVO> list = null;
//				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
//
//			}
//		}//for
//		
//		String uploadFolder = "/Users/chaerilim/upload/";
//
//		/* 날짜 폴더 경로 */
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		Date date = new Date();
//		
//		String str = sdf.format(date);
//		
//		String datePath = str.replace("-", File.separator);
//		
//		//폴더 생성
//		File uploadPath = new File(uploadFolder, datePath);
//		
//		// 기존에 날짜 폴더가 없을 때에만 새로운 폴더 생성
//		if (uploadPath.exists() == false) {
//			uploadPath.mkdirs();
//		}
//		
//		/* 향상된 for */
//		for (MultipartFile multipartFile : albumPictureUrl) {
//			
//		log.info("파일 이름 : " + multipartFile.getOriginalFilename());
//		log.info("파일 타입 : " + multipartFile.getContentType());
//		log.info("파일 크기 : " + multipartFile.getSize());
//		
//		}
//		
//		List<AlbumImageVO> list = new ArrayList();
//		
//		/* 향상된 for */
//		for (MultipartFile multipartFile : albumPictureUrl) {
//		
//		/* 이미지 정보 객체 */
//		AlbumImageVO vo = new AlbumImageVO();
//		
//		/* 파일 이름 */
//		String uploadFileName = multipartFile.getOriginalFilename();
//		vo.setFileName(uploadFileName);
//		vo.setUploadPath(datePath);
//		
//		/* UUID 적용 파일 이름 */
//		String uuid = UUID.randomUUID().toString();
//		vo.setUuid(uuid);
//		
//		uploadFileName = uuid + "_" + uploadFileName;
//		
//		/* 파일 위치, 파일 이름을 합친 File 객체 */
//		File saveFile = new File(uploadPath, uploadFileName);
//		
//		/* 파일 저장*/
//		try {
//			multipartFile.transferTo(saveFile);
//			
//			File thumbnail = new File(uploadPath, "s_" + uploadFileName);
//			BufferedImage bo_image = ImageIO.read(saveFile);
//			BufferedImage bt_image = new BufferedImage(220, 300, BufferedImage.TYPE_3BYTE_BGR);
//			
//			Graphics2D graphic = bt_image.createGraphics();
//			
//			graphic.drawImage(bo_image, 0, 0, 220, 300, null);
//			
//			ImageIO.write(bt_image, "jpg", thumbnail);
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		list.add(vo);
//		
//		} //for
//		
//		ResponseEntity<List<AlbumImageVO>> result = new ResponseEntity<List<AlbumImageVO>>(list, HttpStatus.OK);
//		
//		return result;
//	}
	
//	@GetMapping("/display")
//	public ResponseEntity<byte[]> getImage(String fileName){
//		
//		log.info("이미지 가져오기 ...."+fileName);
//		
//		File file = new File("//Users//chaerilim//upload//" + fileName);
//		
//		ResponseEntity<byte[]> result = null;
//		
//		try {
//			
//			HttpHeaders header = new HttpHeaders();
//			
//			header.add("Content-type", Files.probeContentType(file.toPath()));
//			
//			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
//			
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
//	/* 이미지 파일 삭제 */
//	@PostMapping("/deleteFile")
//	public ResponseEntity<String> deleteFile(String fileName){
//		
//		log.info("이미지 사진 삭제........" + fileName);
//		
//		File file = null;
//		
//		try {
//			/* 썸네일 이미지 삭제 */
//			file = new File("/Users/chaerilim/upload/" + URLDecoder.decode(fileName, "UTF-8"));
//			file.delete();
//			
//			/* 원본 파일 삭제 */
//			String originFileName = file.getAbsolutePath().replace("s_", "");
//			
//			log.info("원본 파일 삭제.. : " + originFileName);
//			
//			file = new File(originFileName);
//			
//			file.delete();	
//		} catch(Exception e) {
//			
//			e.printStackTrace();
//			
//			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
//		}
//		
//		return new ResponseEntity<String>("success", HttpStatus.OK);
//		
//	}
	
//	/* 이미지 정보 반환 */
//	@GetMapping(value="/getAlbumImageList", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<AlbumImageVO>> getAlbumImageList(Long productNum){
//		
//		log.info("getAlbumImageList.... Controller : " +productNum);
//		
//		return new ResponseEntity<List<AlbumImageVO>>(imageMapper.getAlbumImageList(productNum), HttpStatus.OK);
//	}
	
	
	
}
