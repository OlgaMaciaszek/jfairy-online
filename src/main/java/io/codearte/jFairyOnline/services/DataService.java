package io.codearte.jFairyOnline.services;

import java.util.List;
import java.util.Set;

import io.codearte.jFairyOnline.dto.DataPackDTO;
import io.codearte.jFairyOnline.model.DataItem;
import io.codearte.jFairyOnline.model.DataPack;
import io.codearte.jFairyOnline.model.GenderSpecificDataPack;
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
		Set<DataItem> dataItems = range(0, dto.getData().size() - 1)
				.mapToObj(num -> new DataItem(num, dto.getData().get(num)))
				.collect(toSet());
		DataPack dataPack = new DataPack(dto.getDataType(), dto.getLanguage(), dataItems);
		if (dataPack instanceof GenderSpecificDataPack) {
			((GenderSpecificDataPack) dataPack).setGender(dto.getGender());
		}
		return dataPackRepository.save(dataPack);
	}

	public List<DataPack> getDataPacks() {
		return dataPackRepository.findAll();
	}

	public DataPack getDataPack(String dataPackId) {
		return dataPackRepository.getOneById(dataPackId);
	}
}
