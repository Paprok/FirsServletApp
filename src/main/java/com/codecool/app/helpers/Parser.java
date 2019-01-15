package com.codecool.app.helpers;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public interface Parser {
    public Map<String, String> parseRequest(HttpServletRequest request) throws IOException;
}
