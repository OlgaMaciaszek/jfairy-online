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

	static final String ADMIN = "admin";
	static final String DATA_REVIEW = "dataReview";
	static final String INFO = "info";
	static final String NO_UNPROCESSED_DATA_PACKS = "There are no unprocessed data packs for the selected language and data type.";

	@GetMapping
	public String adminPanel(Model model) {
		model.addAttribute(DATA_REVIEW, new AdminDataReviewDTO());
		return ADMIN;
	}

	@GetMapping("/noPack")
	public String adminPanelDataPackNotFound(Model model) {
		model.addAttribute(INFO, NO_UNPROCESSED_DATA_PACKS);
		model.addAttribute(DATA_REVIEW, new AdminDataReviewDTO());
		return ADMIN;
	}
}
