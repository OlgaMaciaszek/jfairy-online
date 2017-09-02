package io.codearte.jFairyOnline.web;

import io.codearte.jFairyOnline.dto.AdminDataReviewDTO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final String ADMIN = "admin";
	private static final String DATA_REVIEW = "dataReview";
	private static final String INFO = "info";

	@GetMapping
	public String adminPanel(Model model) {
		model.addAttribute(DATA_REVIEW, new AdminDataReviewDTO());
		return ADMIN;
	}

	@GetMapping("/noPack")
	public String adminPanelDataPackNotFound(Model model) {
		model.addAttribute(INFO, "There are no unprocessed data packs for the selected language and data type.");
		model.addAttribute(DATA_REVIEW, new AdminDataReviewDTO());
		return ADMIN;
	}
}
