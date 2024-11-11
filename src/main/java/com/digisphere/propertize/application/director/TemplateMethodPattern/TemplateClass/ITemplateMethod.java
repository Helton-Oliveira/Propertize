package com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass;

import java.util.Map;

public interface ITemplateMethod {
    <T> T build(Map<String, String> data);
}
