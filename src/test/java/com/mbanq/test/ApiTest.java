package com.mbanq.test;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.mbanq.entity.AuthTokenInfo;
import com.mbanq.entity.Product;

public class ApiTest {
	public static final String REST_SERVICE_URI = "http://localhost:8080/mbanq-product-api";

	public static final String AUTH_SERVER_URI = "http://localhost:8080/mbanq-product-api/oauth/token";

	public static final String QPM_PASSWORD_GRANT = "?grant_type=password&username=mary&password=123";

	public static final String QPM_ACCESS_TOKEN = "?access_token=";

	/*
	 * Prepare HTTP Headers.
	 */
	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	/*
	 * Add HTTP Authorization header, using Basic-Authentication to send
	 * client-credentials.
	 */
	private static HttpHeaders getHeadersWithClientCredentials() {
		String plainClientCredentials = "admin:secret";
		String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));

		HttpHeaders headers = getHeaders();
		headers.add("Authorization", "Basic " + base64ClientCredentials);
		return headers;
	}

	@SuppressWarnings({ "unchecked" })
	private static AuthTokenInfo sendTokenRequest() {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI + QPM_PASSWORD_GRANT, HttpMethod.POST,
				request, Object.class);
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
		AuthTokenInfo tokenInfo = null;

		if (map != null) {
			tokenInfo = new AuthTokenInfo();
			tokenInfo.setAccess_token((String) map.get("access_token"));
			tokenInfo.setToken_type((String) map.get("token_type"));
			tokenInfo.setRefresh_token((String) map.get("refresh_token"));
			tokenInfo.setExpires_in((int) map.get("expires_in"));
			tokenInfo.setScope((String) map.get("scope"));
			System.out.println(tokenInfo);
		} else {
			System.out.println("No user exist----------");

		}
		return tokenInfo;
	}

	private static void createProduct(AuthTokenInfo tokenInfo) {
		Assert.notNull(tokenInfo, "Authenticate first please......");
		System.out.println("\nTesting create Product API----------");
		RestTemplate restTemplate = new RestTemplate();
		Product product = new Product("TTT10", 10);
		HttpEntity<Object> request = new HttpEntity<Object>(product, getHeaders());
		URI uri = restTemplate.postForLocation(
				REST_SERVICE_URI + "/admin/api/create-product/" + QPM_ACCESS_TOKEN + tokenInfo.getAccess_token(),
				request, Product.class);
		System.out.println("Create Product Complete");

	}

	private static void updateProduct(AuthTokenInfo tokenInfo) {
		Assert.notNull(tokenInfo, "Authenticate first please......");
		System.out.println("\nTesting update Product API----------");
		RestTemplate restTemplate = new RestTemplate();
		Product product = new Product(15, "Pen", 1);
		HttpEntity<Object> request = new HttpEntity<Object>(product, getHeaders());
		ResponseEntity<Product> response = restTemplate.exchange(
				REST_SERVICE_URI + "/admin/api/update-product/" + QPM_ACCESS_TOKEN + tokenInfo.getAccess_token(),
				HttpMethod.PUT, request, Product.class);
		System.out.println(product);
	}

	private static void deleteProduct(AuthTokenInfo tokenInfo) {
		Assert.notNull(tokenInfo, "Authenticate first please......");
		System.out.println("\nTesting delete product API----------");
		RestTemplate restTemplate = new RestTemplate();
		Product product = new Product();
		product.setId(17);
		HttpEntity<Object> request = new HttpEntity<Object>(product, getHeaders());
		restTemplate.exchange(
				REST_SERVICE_URI + "/admin/api/delete-product/" + QPM_ACCESS_TOKEN + tokenInfo.getAccess_token(),
				HttpMethod.DELETE, request, Product.class);
		System.out.println("id " + product.getId() + " has been deleted");
	}

	public static void main(String args[]) {
		AuthTokenInfo tokenInfo = sendTokenRequest();
		createProduct(tokenInfo);
		updateProduct(tokenInfo);
		deleteProduct(tokenInfo);

	}
}
