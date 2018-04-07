package com.hackathon;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPIController {

	@Autowired
	private CallMicrosoftServices cognitiveServices;
	
	@RequestMapping("/videoBreakdown")
	public HttpResponse getVideoBreakdown(@RequestParam String id) {
		return cognitiveServices.getVideoBreakdown(id);
	}

	@RequestMapping("/videoState")
	public HttpResponse getVideoState(@RequestParam String id) {
		return cognitiveServices.getVideoState(id);
	}
	
	@RequestMapping("/videoInsightsWigetURL")
	public HttpResponse getVideoInsightsWidgetUrl(@RequestParam String id) {
		return cognitiveServices.getVideoInsightsWidgetUrl(id);
	}

	@RequestMapping("/videoPlayerWidgetURL")
	public HttpResponse getVideoPlayerWidgetUrl(@RequestParam String id) {
		return cognitiveServices.getVideoPlayerWidgetUrl(id);
	}
	
	@RequestMapping("/analyzeImage")
	public HttpResponse analyzeImage(@RequestParam String id) {
		return cognitiveServices.analyzeImage(id);
	}
	

}