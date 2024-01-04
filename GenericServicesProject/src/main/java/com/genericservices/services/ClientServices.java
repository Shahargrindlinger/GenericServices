package com.genericservices.services;

import org.springframework.stereotype.Service;

@Service
public class ClientServices {
	
	public double calculatefinalPrice(double price, double discount, double additionalDiscount) {
		double finalPrice = 0;
		double discountAmount = 0;

		discountAmount = (price * discount)/100;
		System.out.println("discountAmount = "+discountAmount);
		finalPrice = price - discountAmount;
		System.out.println("finalPrice = "+finalPrice);
		
		if (additionalDiscount > 0) {
			discountAmount = (finalPrice * additionalDiscount)/100;
			System.out.println("additionalDiscount = "+additionalDiscount);
			finalPrice = finalPrice - discountAmount;
		}

		System.out.println("Final Price including all discounts = "+finalPrice);
		return finalPrice;		
	}
}
