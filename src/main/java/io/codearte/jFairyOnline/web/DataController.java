package io.codearte.jFairyOnline.web;

import io.codearte.jFairyOnline.dto.DataPackDTO;
import io.codearte.jFairyOnline.services.DataService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
@Controller
@RequestMapping("/data")
public class DataController {

	private static final String DATA_PACK = "dataPack";

	private final DataService dataService;

	public DataController(DataService dataService) {
		this.dataService = dataService;
	}

	@GetMapping()
	public String displayDataInputForm(Model model) {
		model.addAttribute(DATA_PACK, new DataPackDTO());
		return "dataInputForm";
	}

	@PostMapping
	public String addDataPack(@ModelAttribute DataPackDTO dto) {
		dataService.savePack(dto);
		return "dataInputConfirm";
	}
}
