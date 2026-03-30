package com.book45.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book45.domain.CartDTO;
import com.book45.domain.MemberVO;
import com.book45.service.CartService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/cart/*")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	/* 장바구니(책) 리스트 */
	@RequestMapping(value = "/cartList/{id}", method = RequestMethod.GET)
	public String getCartList(@PathVariable("id") String id, Model model) throws Exception {
		log.info("장바구니 리스트");
		
		model.addAttribute("cartList", cartService.getCartList(id));
		return "/cart/cartList";
	}
	
	/* 장바구니(앨범) 리스트 */
//	@RequestMapping(value = "/albumCartList/{id}", method = RequestMethod.GET)
//	public String getAlbumCartList(@PathVariable("id") String id, Model model) throws Exception {
//		log.info("장바구니 리스트");
//		
//		model.addAttribute("cartList", cartService.getAlbumCartList(id));
//		return "/cart";
//	}
	
	/* 장바구니에 책 추가 */
	@ResponseBody
	@RequestMapping(value = "/addBookCart", method = RequestMethod.POST)
	public String addBookCart(CartDTO cart, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		log.info("장바구니 책 추가");
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		log.info("cart: " + cart);
		if (member == null) {
			return "5";
		}
		
		int result = cartService.addBookCart(cart);
		rttr.addFlashAttribute("result", cart.getCartNum());
		return result + "";
	}
	
	/* 장바구니에 앨범 추가 */
	@RequestMapping(value = "/addAlbumCart", method = RequestMethod.POST)
	public String addAlbumCart(CartDTO cart, HttpServletRequest request, RedirectAttributes rttr) throws Exception{

		log.info("장바구니 앨범 추가");
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		if (member == null) {
			return "5";
		}
		
		int result = cartService.addAlbumCart(cart);
		rttr.addFlashAttribute("result", cart.getCartNum());
		return result + "";
	}
	
	/* 장바구니 상품 삭제 */
	@RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
	public String deleteCart(CartDTO cart, RedirectAttributes rttr) {
		log.info("장바구니 상품 삭제");
		log.info(cart);
		cartService.deleteCart(cart.getCartNum());
		return "redirect:/cart/cartList/" + cart.getId();
	}
	
	/* 장바구니 상품 수량 수정 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyCount(@ModelAttribute("cart") CartDTO cart) {
		log.info("장바구니 수량 수정");
		cartService.modifyCount(cart);
		
		return "redirect:/cart/cartList/" + cart.getId();
	}
	
	/* 장바구니 선택 삭제 */
	@ResponseBody
	@RequestMapping(value="/selectDelete",method = RequestMethod.POST)
	public int selectDelete(HttpSession session, @RequestParam(value ="checkBox[]") List<String> checkBoxArr,CartDTO cart) {
		MemberVO member = (MemberVO)session.getAttribute("member");
		String id = member.getId();
		
		int result = 0;
		int cartNum = 0;
		
		if(member != null) {
			cart.setId(id);
			
			for(String i : checkBoxArr) {
				cartNum = Integer.parseInt(i);
				cart.setCartNum(cartNum);
				cartService.selectDelete(cart);
			}
			result = 1;
		}
		return result;
	}
	
	/* 장바구니 상품 전체 삭제 */
	   @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	   public String deleteAll(CartDTO cart, HttpServletRequest request, RedirectAttributes rttr) {
	      log.info("장바구니 전체 삭제");
	      
	      HttpSession session = request.getSession();
	      String id = (String)session.getAttribute("id");
	      
	      if (cartService.deleteAll(id) == 1) {
	         rttr.addFlashAttribute("result", "deleteAll");
	      }
	      
	      log.info("아이디: " + id);
	      return "redirect:/cart/cartList/" + id;
	   }
}
