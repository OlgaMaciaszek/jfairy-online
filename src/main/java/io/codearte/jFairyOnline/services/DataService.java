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
import io.codearte.jFairyOnline.repositories.DataPackRepository;

import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
@Service
public class DataService {

	private final DataPackRepository dataPackRepository;

	public DataService(DataPackRepository dataPackRepository) {
		this.dataPackRepository = dataPackRepository;
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
		Optional<DataPack> dataPackOpt = dataPackRepository.findOneById(dataPackId);
		DataPack dataPack = dataPackOpt.filter(pack -> !pack.isProcessed())
				.orElseThrow(() -> new IllegalDataPack(dataPackId));
		if (dataItemIds == null) {
			dataPackRepository.delete(dataPackId);
		} else {
			deleteSelectedDataItems(dataItemIds, dataPack);
		}
	}

	private void deleteSelectedDataItems(Long[] dataItemIds, DataPack dataPack) {
		Set<DataItem> updatedDataItems = dataPack.getDataItems().stream().filter(dataItem -> !Arrays.asList(dataItemIds)
				.contains(dataItem.getId())).collect(Collectors.toSet());
		dataPack.setDataItems(updatedDataItems);
		dataPackRepository.save(dataPack);
	}
}
