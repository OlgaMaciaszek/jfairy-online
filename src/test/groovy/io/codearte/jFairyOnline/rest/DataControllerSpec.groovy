package io.codearte.jFairyOnline.rest

import spock.lang.IgnoreIf

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.MediaType.APPLICATION_JSON

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
@IgnoreIf({!Boolean.valueOf(properties['integration'])})
class DataControllerSpec extends RestControllerSpec {

	def 'should create data pack'() {
		given:
			HttpHeaders headers = new HttpHeaders()
			headers.setContentType(APPLICATION_JSON)
			HttpEntity<String> request = new HttpEntity<String>(buildDataPack(), headers)
		when:
			ResponseEntity<Void> response = restTemplate.postForEntity(buildUrl('/data'), request,
					Void)
		then:
			response.statusCode == CREATED
			response.headers.getLocation().toString().contains('/rest/data/')
	}

	def 'should get data packs'() {
		when:
			ResponseEntity<String> response = restTemplate.getForEntity(buildUrl('/data'),
					String)
		then:
			response.statusCode == HttpStatus.OK
			response.body
	}

	def 'should get data pack'() {
		given:
			String dataPackId = createDataPack()
		when:
			ResponseEntity<String> response = restTemplate.getForEntity(buildUrl("/data/$dataPackId"),
					String)
		then:
			response.statusCode == HttpStatus.OK
			response.body
	}

	def 'should not delete data from data pack when user not authorised'() {
		given:
			String dataPackId = createDataPack()
		when:
			restTemplate.delete(buildUrl("/data/$dataPackId?dataItem=0"))
		then:
			String updatedDataPackBody = restTemplate.getForEntity(buildUrl("/data/$dataPackId"),
					String).body
			updatedDataPackBody.contains('Agatha')
	}

	def 'should not process data pack when user not authorised'() {
		given:
			String dataPackId = createDataPack()
		when:
			restTemplate.put(buildUrl("/data/$dataPackId/process"),
					new HttpEntity<String>(''))
		then:
			String updatedDataPackBody = restTemplate.getForEntity(buildUrl("/data/$dataPackId"),
					String).body
			!updatedDataPackBody.contains("\"processed\":true")
	}

	private String createDataPack() {
		HttpHeaders headers = new HttpHeaders()
		headers.setContentType(APPLICATION_JSON)
		HttpEntity<String> request = new HttpEntity<String>(buildDataPack(), headers)
		String location = restTemplate.postForLocation(buildUrl('/data'), request,
				Void)
		return location.substring(location.lastIndexOf('/') + 1)
	}

	private static String buildDataPack() {
		return "{ \"language\" : \"EN\", \"dataType\" : \"FEMALE_NAME\",\"data\": [\"Agatha\", \"Mary\"]}"
	}
}
