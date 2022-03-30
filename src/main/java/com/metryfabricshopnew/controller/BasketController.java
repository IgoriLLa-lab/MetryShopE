package com.metryfabricshopnew.controller;

import com.metryfabricshopnew.dto.BasketDTO;
import com.metryfabricshopnew.service.BasketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/basket")
    public String aboutBasket(Model model, Principal principal) {
        if (principal == null) {
        model.addAttribute("basket", new BasketDTO());
    } else {
        BasketDTO basketDTO = basketService.getBasketByUser(principal.getName());
        model.addAttribute("basket", basketDTO);
    }
        return "basket";
}
}
