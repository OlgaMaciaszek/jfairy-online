package io.codearte.jFairyOnline.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import io.codearte.jFairyOnline.dto.DataPackDTO;
import io.codearte.jFairyOnline.exceptions.IllegalDataPack;
import io.codearte.jFairyOnline.model.DataItem;
import io.codearte.jFairyOnline.model.DataPack;
import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jFairyOnline.repositories.DataPackRepository;

import org.springframework.stereotype.Service;

import static io.codearte.jFairyOnline.model.enums.DataType.CITY;
import static io.codearte.jFairyOnline.model.enums.DataType.COMPANY_NAME;
import static io.codearte.jFairyOnline.model.enums.DataType.FEMALE_LAST_NAME;
import static io.codearte.jFairyOnline.model.enums.DataType.FEMALE_NAME;
import static io.codearte.jFairyOnline.model.enums.DataType.MALE_LAST_NAME;
import static io.codearte.jFairyOnline.model.enums.DataType.MALE_NAME;
import static io.codearte.jFairyOnline.model.enums.DataType.STREET;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
@Service
public class DataService {

	private final DataPackRepository dataPackRepository;
	private final Map<DataType, BiConsumer<String, Language>> methodMap = new HashMap<>();

	public DataService(DataPackRepository dataPackRepository, NameService nameService, LastNameService lastNameService, CompanyNameService companyNameService, StreetService streetService, CityService cityService) {
		this.dataPackRepository = dataPackRepository;
		initialiseMethodMap(nameService, lastNameService, companyNameService, streetService, cityService);
	}

	public DataPack savePack(DataPackDTO dto) {
		Set<DataItem> dataItems = getDataItems(dto);
		DataPack dataPack = new DataPack(dto.getDataType(), dto.getLanguage(), dataItems);
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

	public DataPack process(String dataPackId) {
		DataPack dataPack = getUnprocessedDataPack(dataPackId);
		dataPack.getDataItems().forEach(item -> methodMap.get(dataPack.getDataType())
				.accept(item.getValue(), dataPack.getLanguage()));
		dataPack.setProcessed(true);
		return dataPackRepository.save(dataPack);
	}

	private void initialiseMethodMap(NameService nameService, LastNameService lastNameService, CompanyNameService companyNameService, StreetService streetService, CityService cityService) {
		methodMap.put(FEMALE_NAME, nameService::processFemale);
		methodMap.put(MALE_NAME, nameService::processMale);
		methodMap.put(FEMALE_LAST_NAME, lastNameService::processFemale);
		methodMap.put(MALE_LAST_NAME, lastNameService::processMale);
		methodMap.put(COMPANY_NAME, companyNameService::process);
		methodMap.put(STREET, streetService::process);
		methodMap.put(CITY, cityService::process);
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
}
