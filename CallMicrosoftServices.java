package com.hackathon;

import org.apache.http.HttpResponse;

public interface CallMicrosoftServices {
	
	public HttpResponse getVideoBreakdown(String id);

	public HttpResponse getVideoState(String id);
	
	public HttpResponse getVideoInsightsWidgetUrl(String id);

	public HttpResponse getVideoPlayerWidgetUrl(String id);
	
	public HttpResponse analyzeImage(String id);
	
}
