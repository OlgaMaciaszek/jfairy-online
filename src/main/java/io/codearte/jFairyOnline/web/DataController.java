package io.codearte.jFairyOnline.web;

import java.util.Optional;

import io.codearte.jFairyOnline.dto.DataPackDTO;
import io.codearte.jFairyOnline.model.DataPack;
import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jFairyOnline.services.DataService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
@Controller
@RequestMapping("/data")
public class DataController {

	static final String DATA_PACK = "dataPack";
	private static final String DATA_REVIEW = "dataReview";
	private static final String LANG = "lang";
	private static final String DATA_TYPE = "dataType";
	static final String DATA_INPUT_CONFIRM = "dataInputConfirm";

	private final DataService dataService;

	public DataController(DataService dataService) {
		this.dataService = dataService;
	}

	@GetMapping()
	public String displayDataInputForm(Model model) {
		model.addAttribute(DATA_PACK, new DataPackDTO());
		return "dataInputForm";
	}

	@GetMapping("/review")
	public String displayDataReviewForm(@RequestParam(value = LANG, required = false) Language language,
	                                    @RequestParam(value = DATA_TYPE, required = false) DataType dataType,
	                                    Model model) {
		Optional<DataPack> dataPackOpt = dataService.getFirstUnprocessedDataPack(language, dataType);
		if (dataPackOpt.isPresent()) {
			DataPackDTO dataPackDTO = new DataPackDTO(dataPackOpt.get());
			model.addAttribute(DATA_PACK, dataPackDTO);
			return DATA_REVIEW;
		} else {
			return "redirect:/admin/noPack";
		}
	}

	@GetMapping("/review/{dataPackId}")
	public String continueReview(@PathVariable("dataPackId") String dataPackId,
	                             Model model) {
		DataPack dataPack = dataService.getDataPack(dataPackId);
		model.addAttribute(DATA_PACK, new DataPackDTO(dataPack));
		return DATA_REVIEW;
	}

	@PostMapping
	public String addDataPack(@ModelAttribute DataPackDTO dto) {
		dataService.savePack(dto);
		return DATA_INPUT_CONFIRM;
	}

	@PostMapping("/delete")
	public String deleteDataItems(@ModelAttribute DataPackDTO dataPackDTO) {
		dataService.deleteDataItems(dataPackDTO.getId(), dataPackDTO.getDataItemsToDelete());
		return "redirect:/data/review/" + dataPackDTO.getId();
	}

	@PostMapping("/process")
	public String process(@ModelAttribute DataPackDTO dataPackDTO,
	                      RedirectAttributes redirectAttributes) {
		dataService.process(dataPackDTO.getId());
		redirectAttributes.addAttribute(LANG, dataPackDTO.getLanguage());
		redirectAttributes.addAttribute(DATA_TYPE, dataPackDTO.getDataType());
		return "redirect:/data/review";
	}
}
