package ar.com.zeni.zeniwsdl.rest;

import java.util.Arrays;
import java.util.logging.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import ar.com.zeni.mail.MailProperties;

/**
 *
 * @author ogagliano
 *
 */
public class RestExecute {

	private String rest_uri;
	private String rest_timeOut;

	private static final Logger LOG = Logger.getLogger("org.apache.cxf.services.ZeniWSDL.ZeniWSDLSOAP.ZeniWSDL");
	private RestTemplate restTemplate;
	private HttpComponentsClientHttpRequestFactory factory;

	/**
	 *
	 * @param uri
	 * @param timeOut
	 * @throws Exception
	 */
	public RestExecute(String uri, String timeOut) throws Exception {
		rest_uri = uri;
		rest_timeOut = timeOut;
		factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(Integer.valueOf(rest_timeOut));
		factory.setConnectTimeout(Integer.valueOf(rest_timeOut));
	}

	/**
	 *
	 * @param dni
	 * @return
	 * @throws Exception
	 */
	public int send(MailProperties data) throws Exception {

		int result = -1;
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
			headers.setContentType(MediaType.APPLICATION_JSON);

			LOG.info("invoke post uri{" + rest_uri + "} timeout{" + rest_timeOut + "}>>> ");
			restTemplate = new RestTemplate(factory);

			HttpEntity<MailProperties> entity = new HttpEntity<MailProperties>(data);
			ResponseEntity<Integer> response = restTemplate.exchange(rest_uri, HttpMethod.POST, entity, Integer.class);

			if (response != null)
				result = response.getBody();

		} catch (Exception e) {
			LOG.info("Se produjo un error: " + e.getMessage());
			throw e;
		}
		return result;
	}

}
