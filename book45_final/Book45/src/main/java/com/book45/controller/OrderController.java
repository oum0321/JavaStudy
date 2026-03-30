package com.book45.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.book45.domain.KakaoPayApprovalVO;
import com.book45.domain.KakaoPayVO;
import com.book45.domain.MemberVO;
import com.book45.domain.OrderCancelDTO;
import com.book45.domain.OrderDTO;
import com.book45.domain.OrderItemDTO;
import com.book45.domain.OrderListDTO;
import com.book45.domain.OrderPageDTO;
import com.book45.domain.OrderPageItemDTO;
import com.book45.service.MemberService;
import com.book45.service.OrderService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/order/*")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	private static final String HOST = "https://kapi.kakao.com";
	
	private KakaoPayVO kakaoPay;
	
	private KakaoPayApprovalVO kakaoPayApproval;
	
	@RequestMapping(value="/orderPage/{id}", method = RequestMethod.GET)
    public String orderPage(@PathVariable("id") String id, @RequestParam(required=false, value="isbn") Long isbn , @RequestParam(required=false, value="productNum") Long productNum, OrderPageDTO orderPage, Model model) {
       log.info("주문 페이지"); 
       log.info("id: " + id);
       log.info("orders: " + orderPage.getOrders());
       
       Long inputIsbn = null;
       Long inputProductNum = null;
       
       for(OrderPageItemDTO or : orderPage.getOrders()) {
          
          if(inputIsbn == null)
             inputIsbn = or.getIsbn();
          if(inputProductNum == null)
             inputProductNum = or.getProductNum();
          
          log.info("도서번호: " + inputIsbn);
          log.info("상품번호: " + inputProductNum);
       }
       
       if (inputProductNum == null) {
          if(inputIsbn != null) {
             model.addAttribute("orderBookList", orderService.getBooksInfo(orderPage.getOrders()));
             model.addAttribute("member", memberService.getMember(id));
          
             log.info("getBooksInfo: "+ orderService.getBooksInfo(orderPage.getOrders()));
          }
       } else if (inputIsbn == null) {
          if(inputProductNum != null) {
             model.addAttribute("orderAlbumList", orderService.getAlbumsInfo(orderPage.getOrders()));
             model.addAttribute("member", memberService.getMember(id));
          
             log.info("getAlbumsInfo: "+ orderService.getAlbumsInfo(orderPage.getOrders()));
          }
       } else if (inputIsbn != null && inputProductNum != null){
          
          List<OrderPageItemDTO> IsbnDTOList = new ArrayList<>();
          List<OrderPageItemDTO> ProductDTOList = new ArrayList<>();
          
          for(OrderPageItemDTO or : orderPage.getOrders()) {
             if(or.getIsbn() != null) IsbnDTOList.add(or);
             else if (or.getProductNum() != null) ProductDTOList.add(or);
          }
          
          model.addAttribute("orderBookList", orderService.getBooksInfo(IsbnDTOList)); 
          model.addAttribute("orderAlbumList", orderService.getAlbumsInfo(ProductDTOList));
          model.addAttribute("member", memberService.getMember(id));      
             
          log.info("getBooksInfo: "+ orderService.getBooksInfo(IsbnDTOList));
          log.info("getAlbumsInfo: "+ orderService.getAlbumsInfo(ProductDTOList));
       }
       
       return "/order/orderPage";
    }


	
//	@RequestMapping(value="/orderPage/{id}", method = RequestMethod.GET)
//	public String orderPage(@PathVariable("id") String id, @RequestParam(value = "isbn", required = false) Long isbn, @RequestParam(value = "productNum", required = false) Long productNum, OrderPageDTO orderPage, Model model) {
//		log.info("주문 페이지"); 
//		log.info("id: " + id);
//		log.info("orders: " + orderPage.getOrders());
//      
//      if (isbn != null && productNum == null) {
//         model.addAttribute("orderBookList", orderService.getBooksInfo(orderPage.getOrders()));
//         model.addAttribute("member", memberService.getMember(id));
//         
//         log.info("getBooksInfo: " + orderService.getBooksInfo(orderPage.getOrders()));
//      } else if (isbn == null && productNum != null) {
//         model.addAttribute("orderAlbumList", orderService.getAlbumsInfo(orderPage.getOrders()));
//         model.addAttribute("member", memberService.getMember(id));
//         
//         log.info("getAlbumsInfo: " + orderService.getAlbumsInfo(orderPage.getOrders()));
//      } else if (isbn != null && productNum != null) {
//         model.addAttribute("orderBookList", orderService.getBooksInfo(orderPage.getOrders()));
//         model.addAttribute("orderAlbumList", orderService.getAlbumsInfo(orderPage.getOrders()));
//         model.addAttribute("member", memberService.getMember(id));
//         
//         log.info("getBooksInfo: " + orderService.getBooksInfo(orderPage.getOrders()));
//         log.info("getAlbumsInfo: " + orderService.getAlbumsInfo(orderPage.getOrders()));
//      }
//      
//      return "/order/orderPage";
//	}
	
	@RequestMapping(value="/order", method = {RequestMethod.POST, RequestMethod.GET})
	public String orderPagePost(OrderDTO ord, HttpSession session, Model model) {
		Long isbn = null;
		Long productNum = null;
		
		for (OrderItemDTO oit : ord.getOrders()) {
			if (isbn == null) {
				isbn = oit.getIsbn();
			}
			
			if (productNum == null) {
				productNum = oit.getProductNum();
			}
		}
		
		if (productNum == null) {
			if (isbn != null) {
				orderService.orderBook(ord);
			}
		} else if (isbn == null) {
			if (productNum != null) {
				orderService.orderAlbum(ord);
			}
		}
		
		session.setAttribute("totalPrice", ord.getTotalPrice());
//		else if (isbn != null && productNum != null) {
//			List<OrderItemDTO> isbnList = new ArrayList();
//			List<OrderItemDTO> productNumList = new ArrayList();
//			
//			for (OrderItemDTO oit : ord.getOrders()) {
//				if (oit.getIsbn() != null) isbnList.add(oit);
//				if (oit.getProductNum() != null) productNumList.add(oit);
//				
//				orderService.orderItem(ord);
//			}
//		}
		
		log.info("주문내역: " + ord);
		
		return "redirect:/main";
	}
	
	/* 회원 주문 삭제 */
	   @RequestMapping(value="/memberOrderCancel")
	   public String membeOrderCancelPOST(OrderCancelDTO orderCancel) {
	      log.info(orderCancel.getId());
	      orderService.orderCancel(orderCancel);
	      return "redirect:/member/orderList?id=" + orderCancel.getId();
	   }
	
	/* 관리자 주문 삭제 */
	@RequestMapping(value="/adminOrderCancel")
	public String adminOrderCancelPOST(OrderCancelDTO orderCancel) {
		
		orderService.orderCancel(orderCancel);
		return "redirect:/admin/orderList?keyword=" + orderCancel.getKeyword() + "&amount="+orderCancel.getAmount() + "&pageNum="+orderCancel.getPageNum();
	}
	
	@RequestMapping(value = "/kakaoPay", method = RequestMethod.POST)
	public String kakaoPay() {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "	b3e49ec04e2a7363b44d16bbf20196af");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("item_name", "갤럭시S9");
        params.add("quantity", "1");
        params.add("total_amount", "2100");
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
            kakaoPay = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayVO.class);
            
            log.info("" + kakaoPay);
            
            return kakaoPay.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        return "/order/pay";
	}
	
	public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "	b3e49ec04e2a7363b44d16bbf20196af");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPay.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", "2100");
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
            kakaoPayApproval = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApproval);
          
            return kakaoPayApproval;
        
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        return null;
	}
	
	/* 회원 주문 상세보기 */
	@RequestMapping(value="/orderDetail", method=RequestMethod.GET)
	public void orderDetail(@RequestParam("orderNum") String orderNum, HttpSession session, Model model, OrderDTO order) {
		log.info("주문 내역 상세보기 페이지");
		log.info("주문번호: " + orderNum);
		MemberVO member = (MemberVO)session.getAttribute("member");
		String id = member.getId();
	  
		order.setId(id);
		order.setOrderNum(orderNum);

    	List<OrderListDTO> orderBookDetail = orderService.bookOrderDetail(order);
    	if (orderBookDetail.get(0).getIsbn() != null && orderBookDetail.get(0).getProductNum() == null) {
    		model.addAttribute("orderBookDetail", orderBookDetail);
    		model.addAttribute("orderDate", orderBookDetail.get(0).getOrderDate());
    		model.addAttribute("orderNum", orderBookDetail.get(0).getOrderNum());
    		model.addAttribute("name", orderBookDetail.get(0).getName());
    		model.addAttribute("zipCode", orderBookDetail.get(0).getZipCode());
    		model.addAttribute("addressRoad", orderBookDetail.get(0).getAddressRoad());
    		model.addAttribute("addressDetail", orderBookDetail.get(0).getAddressDetail());
    		model.addAttribute("phone", orderBookDetail.get(0).getPhone());
    		model.addAttribute("orderState", orderBookDetail.get(0).getOrderState());
    		session.setAttribute("orderBookDetail", orderBookDetail);
    	}
    	
		List<OrderListDTO> orderAlbumDetail = orderService.albumOrderDetail(order);
    	if (orderAlbumDetail.get(0).getProductNum() != null && orderAlbumDetail.get(0).getIsbn() == null) {
    		model.addAttribute("orderAlbumDetail", orderAlbumDetail);
    		model.addAttribute("orderDateA", orderAlbumDetail.get(0).getOrderDate());
    		model.addAttribute("orderNumA", orderAlbumDetail.get(0).getOrderNum());
    		model.addAttribute("nameA", orderAlbumDetail.get(0).getName());
    		model.addAttribute("zipCodeA", orderAlbumDetail.get(0).getZipCode());
    		model.addAttribute("addressRoadA", orderAlbumDetail.get(0).getAddressRoad());
    		model.addAttribute("addressDetailA", orderAlbumDetail.get(0).getAddressDetail());
    		model.addAttribute("phoneA", orderAlbumDetail.get(0).getPhone());
    		model.addAttribute("orderStateA", orderAlbumDetail.get(0).getOrderState());
    		session.setAttribute("orderAlbumDetail", orderAlbumDetail);
    	}
	}
	
	
}
