package io.codearte.jFairyOnline.services

import io.codearte.jFairyOnline.dto.DataPackDTO
import io.codearte.jFairyOnline.model.DataItem
import io.codearte.jFairyOnline.model.DataPack
import io.codearte.jFairyOnline.repositories.DataPackRepository
import spock.lang.Specification
import spock.lang.Subject

import static io.codearte.jFairyOnline.model.enums.DataType.FEMALE_NAME
import static io.codearte.jFairyOnline.model.enums.Gender.FEMALE
import static io.codearte.jFairyOnline.model.enums.JFairyDataKey.FIRST_NAME
import static io.codearte.jFairyOnline.model.enums.Language.EN
import static java.util.Arrays.asList
import static java.util.Optional.of

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
class DataServiceSpec extends Specification {

	private static final String ID = 'id'

	@Subject
	private DataService dataService
	DataPackRepository dataPackRepository
	ValueService valueService

	def setup() {
		dataPackRepository = Mock(DataPackRepository)
		valueService = Mock(ValueService)
		dataService = new DataService(dataPackRepository, valueService)
	}

	def 'should save data pack'() {
		when:
			DataPackDTO dataPackDTO = new DataPackDTO()
			dataPackDTO.setData(['Ann', 'Mary'])
			dataService.savePack(dataPackDTO)
		then:
			1 * dataPackRepository.save(_ as DataPack)
	}

	def 'should get data packs'() {
		when:
			dataService.getDataPacks()
		then:
			1 * dataPackRepository.findAll()
	}

	def 'should get data pack by id'() {
		when:
			dataService.getDataPack(ID)
		then:
			1 * dataPackRepository.getOneById(ID)
	}

	def 'should delete data items'() {
		given:
			DataPack dataPack = new DataPack(FEMALE_NAME, EN, FEMALE,
					FIRST_NAME, new HashSet<DataItem>(asList(new DataItem(1, 'Ann'))))
		when:
			dataService.deleteDataItems(ID, [1L, 2L] as Long[])
		then:
			1 * dataPackRepository.save(_ as DataPack)
			1 * dataPackRepository.findOneById(ID) >> of(dataPack)
	}

}
