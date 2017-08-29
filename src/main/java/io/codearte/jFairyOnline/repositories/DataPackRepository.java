package io.codearte.jFairyOnline.repositories;

import io.codearte.jFairyOnline.model.DataPack;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
public interface DataPackRepository extends MongoRepository<DataPack, String> {

	DataPack getOneById(String dataPackId);
}
