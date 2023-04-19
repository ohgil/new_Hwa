package org.example;

import org.example.util.Util;

import java.util.Map;

public class Rq {
  String url;
  Map<String, String> params;
  String urlPath;

  public Rq(String url) {
    this.url = url;
    params = Util.getUrlParamsFromUrl(this.url);
    urlPath = Util.getUrlPathFromUrl(this.url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public int getIntParam(String paramName, int defaultValue) {
    if (params.containsKey(paramName) == false) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(params.get(paramName));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if (params.containsKey(paramName) == false) {
      return defaultValue;
    }

    return params.get(paramName);
  }

  public String getUrlPath() {
    return urlPath;
  }
}
