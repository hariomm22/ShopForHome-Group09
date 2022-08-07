package com.shopforhome.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopforhome.dtos.OrderDetailsDTO;
import com.shopforhome.dtos.OrderResponseDTO;
import com.shopforhome.dtos.PlaceOrderDTO;
import com.shopforhome.dtos.Response;
import com.shopforhome.entity.Cart;
import com.shopforhome.entity.Customer;
import com.shopforhome.entity.Order;
import com.shopforhome.entity.OrderDetails;
import com.shopforhome.service.AddressService;
import com.shopforhome.service.CartService;
import com.shopforhome.service.CustomerService;
import com.shopforhome.service.OrderDetailService;
import com.shopforhome.service.OrderService;
import com.shopforhome.service.PaymentService;
import com.shopforhome.service.ProductService;


@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired OrderService orderService;
	@Autowired CustomerService customerService;
	@Autowired AddressService addressService;
	@Autowired PaymentService paymentService;
	@Autowired OrderDetailService orderDetailsService;	
	@Autowired ProductService productService;	
	@Autowired CartService cartservice;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PlaceOrderDTO dto) {	
		System.out.println(dto);
		//Address address=addressService.saveAddress(dto.getAddress());
		//Payment payment=paymentService.savePayment(dto.getPayment());
		Order order=new Order();
		//order.setAddress(address);
		//order.setPayment(payment);
		//order.setPaymethod(dto.getPaymethod());
		Customer customer=customerService.findById(dto.getCustomerid());
		order.setCustomer(customer);
		Order orders=orderService.saveOrder(order);
		
		for(Cart cart : cartservice.findByuserid(dto.getCustomerid())) {
			OrderDetails od=new OrderDetails();
			od.setOrder(orders);
			od.setQty(cart.getQty());
			od.setProduct(cart.getProduct());			
			orderDetailsService.saveOrderDetails(od);
		}
		cartservice.clearCart(customer);
		return Response.success("Order placed");
	}
	
	@GetMapping("/confirm/{id}")
	public ResponseEntity<?> confirmOrder(@PathVariable("id") int id) {
		orderService.confirm(id);
		return Response.success("Confirmed");
	}
	
	@GetMapping
	public List<Order> findAllOrders(Optional<Integer> custid) {
		List<Order> result=null;
		if(custid.isPresent()) {
			Customer customer=customerService.findById(custid.get());
			 result= orderService.getCustomerOrders(customer);
		}else {
			result = orderService.getAllOrders();
		}
		return result;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOrderById(@PathVariable("id") int id) {
		Order order = orderService.findById(id);
		List<OrderDetails> details=orderDetailsService.findByOrder(order);
		List<OrderDetailsDTO> detailsdto=new ArrayList<OrderDetailsDTO>();
		details.forEach(od -> {
			OrderDetailsDTO dto=OrderDetailsDTO.fromEntity(od);
			detailsdto.add(dto);
		});
		OrderResponseDTO result=new OrderResponseDTO();
		result.setOrder(order);
		result.setDetails(detailsdto);
		return Response.success(result);
	}
}
