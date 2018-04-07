package com.hackathon;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CallMicrosoftServicesImpl implements CallMicrosoftServices {


    private static final Logger logger = LogManager.getLogger("HelloWorld");
    private static final Logger LOGGER = LogManager.getLogger("CallMicrosoftServicesImpl");
	
	/* (non-Javadoc)
	 * @see com.hackathon.CallMicrosoftServices#getVideoBreakdown(java.lang.String)
	 * 
	 * This Method Retrieves the Breakdown of Video into frames and sends it to JS.
	 */
	@Override
	public HttpResponse getVideoBreakdown(String id) {
		try {
			URIBuilder builder = new URIBuilder(Constants.MICROSOFT_API_URL + id);
			builder.setParameter("language", "en");
			URI uri = builder.build();
			HttpResponse response = callApi(uri);
			LOGGER.info(response.getEntity());
			return response;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}

	}
	
	
	
	/* (non-Javadoc)
	 * @see com.hackathon.CallMicrosoftServices#analyzeImage(java.lang.String)
	 * 
	 * This Method Retrieves the Analysis of Each Image for Identifying Emotions.
	 */
	@Override
	public HttpResponse analyzeImage(String id) {
		try {

			HttpClient httpclient = HttpClients.createDefault();
			URIBuilder builder = new URIBuilder(Constants.FACE_EMOTIONS_URL);
			builder.setParameter("returnFaceId", "true");
			builder.setParameter("returnFaceLandmarks", "false");
			builder.setParameter("returnFaceAttributes", "{string}");
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", Constants.API_KEY);

			// Request body
			StringEntity reqEntity = new StringEntity("{body}");
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			LOGGER.info(response.getEntity());
			return response;
		} catch (Exception e) {
			LOGGER.error(null);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hackathon.CallMicrosoftServices#getVideoState(java.lang.String)
	 * 
	 * Retrieves Video State of the ID sent.
	 */
	@Override
	public HttpResponse getVideoState(String id) {
		try {
			URIBuilder builder = new URIBuilder(Constants.MICROSOFT_API_URL + id + "/State");
			builder.setParameter("language", "en");
			URI uri = builder.build();
			HttpResponse response = callApi(uri);
			LOGGER.info(response.getEntity());
			return response;
		} catch (Exception e) {
			LOGGER.error(null);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.hackathon.CallMicrosoftServices#getVideoInsightsWidgetUrl(java.lang.String)
	 * Retrieves Insight Widget URL for GUI.
	 */
	@Override
	public HttpResponse getVideoInsightsWidgetUrl(String id) {
		try {
			URIBuilder builder = new URIBuilder(Constants.MICROSOFT_API_URL + id + "/InsightsWidgetUrl");
			builder.setParameter("language", "en");
			URI uri = builder.build();
			HttpResponse response = callApi(uri);
			LOGGER.info(response.getEntity());
			return response;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.hackathon.CallMicrosoftServices#getVideoPlayerWidgetUrl(java.lang.String)
	 * Retrives Video Player Widget for GUI
	 */
	@Override
	public HttpResponse getVideoPlayerWidgetUrl(String id) {
		try {
			URIBuilder builder = new URIBuilder(Constants.MICROSOFT_API_URL + id + "/PlayerWidgetUrl");
			builder.setParameter("language", "en");
			URI uri = builder.build();
			HttpResponse response = callApi(uri);
			LOGGER.info(response.getEntity());
			return response;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}

	/**
	 * @param uri
	 * @return
	 * 
	 * Hit's the Cognitive API and returns Results.
	 */
	private HttpResponse callApi(URI uri) {
		try {
			HttpGet request = new HttpGet(uri);
			HttpClient httpclient = HttpClients.createDefault();
			request.setHeader("Ocp-Apim-Subscription-Key", Constants.API_KEY);
			StringEntity reqEntity = new StringEntity("{body}");
			((HttpResponse) request).setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			LOGGER.info(response.getEntity());
			return response;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}

	}

}
