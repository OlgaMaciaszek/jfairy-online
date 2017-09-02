package io.codearte.jFairyOnline.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import io.codearte.jFairyOnline.dto.DataPackDTO;
import io.codearte.jFairyOnline.exceptions.IllegalDataPack;
import io.codearte.jFairyOnline.model.DataItem;
import io.codearte.jFairyOnline.model.DataPack;
import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.JFairyDataKey;
import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jFairyOnline.repositories.DataPackRepository;

import org.springframework.stereotype.Service;

import static io.codearte.jFairyOnline.model.enums.DataType.FEMALE_LAST_NAME;
import static io.codearte.jFairyOnline.model.enums.DataType.FEMALE_NAME;
import static io.codearte.jFairyOnline.model.enums.DataType.MALE_LAST_NAME;
import static io.codearte.jFairyOnline.model.enums.DataType.MALE_NAME;
import static io.codearte.jFairyOnline.model.enums.Gender.FEMALE;
import static io.codearte.jFairyOnline.model.enums.Gender.MALE;
import static io.codearte.jFairyOnline.model.enums.JFairyDataKey.FIRST_NAME;
import static io.codearte.jFairyOnline.model.enums.JFairyDataKey.LAST_NAME;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
@Service
public class DataService {

	private final DataPackRepository dataPackRepository;
	private final ValueService valueService;

	public DataService(DataPackRepository dataPackRepository, ValueService valueService) {
		this.dataPackRepository = dataPackRepository;
		this.valueService = valueService;
	}

	public DataPack savePack(DataPackDTO dto) {
		DataPack dataPack = new DataPack(dto.getDataType(), dto.getLanguage(), getGender(dto),
				getJFairyDataKey(dto), getDataItems(dto));
		return dataPackRepository.save(dataPack);
	}

	public List<DataPack> getDataPacks() {
		return dataPackRepository.findAll();
	}

	public DataPack getDataPack(String dataPackId) {
		return dataPackRepository.getOneById(dataPackId);
	}

	private Set<DataItem> getDataItems(DataPackDTO dto) {
		return range(0, dto.getData().size())
				.mapToObj(num -> new DataItem(num, dto.getData().get(num)))
				.collect(toSet());
	}

	public void deleteDataItems(String dataPackId, Long[] dataItemIds) {
		DataPack dataPack = getUnprocessedDataPack(dataPackId);
		if (dataItemIds == null) {
			dataPackRepository.delete(dataPackId);
		} else {
			deleteSelectedDataItems(dataItemIds, dataPack);
		}
	}

	private Gender getGender(DataPackDTO dto) {
		if (FEMALE_NAME.equals(dto.getDataType()) || FEMALE_LAST_NAME.equals(dto.getDataType())) {
			return FEMALE;
		} else if (MALE_NAME.equals(dto.getDataType()) || MALE_LAST_NAME.equals(dto.getDataType())) {
			return MALE;
		}
		return null;
	}

	public Optional<DataPack> getFirstUnprocessedDataPack(Language language, DataType dataType) {
		if (language != null && dataType != null) {
			return dataPackRepository.findFirstByLanguageAndDataTypeAndProcessedFalse(language, dataType);
		} else if (language != null) {
			return dataPackRepository.findFirstByLanguageAndProcessedFalse(language);
		} else if (dataType != null) {
			return dataPackRepository.findFirstByDataTypeAndProcessedFalse(dataType);
		} else {
			return dataPackRepository.findFirstByProcessedFalse();
		}
	}

	private DataPack getUnprocessedDataPack(String dataPackId) {
		Optional<DataPack> dataPackOpt = dataPackRepository.findOneById(dataPackId);
		return dataPackOpt.filter(pack -> !pack.isProcessed())
				.orElseThrow(() -> new IllegalDataPack(dataPackId));
	}

	private void deleteSelectedDataItems(Long[] dataItemIds, DataPack dataPack) {
		Set<DataItem> updatedDataItems = dataPack.getDataItems().stream().filter(dataItem -> !Arrays.asList(dataItemIds)
				.contains(dataItem.getId())).collect(Collectors.toSet());
		dataPack.setDataItems(updatedDataItems);
		dataPackRepository.save(dataPack);
	}

	private JFairyDataKey getJFairyDataKey(DataPackDTO dto) {
		if (FEMALE_NAME.equals(dto.getDataType()) || MALE_NAME.equals(dto.getDataType())) {
			return FIRST_NAME;
		} else if (FEMALE_LAST_NAME.equals(dto.getDataType()) || MALE_LAST_NAME.equals(dto.getDataType())) {
			return LAST_NAME;
		}
		return JFairyDataKey.valueOf(dto.getDataType().name());
	}

	public DataPack process(String dataPackId) {
		DataPack dataPack = getUnprocessedDataPack(dataPackId);
		dataPack.getDataItems().forEach(item -> valueService.process(item.getValue(), dataPack.getLanguage(),
				dataPack.getjFairyDataKey(), dataPack.getGender()));
		dataPack.setProcessed(true);
		return dataPackRepository.save(dataPack);
	}
}
