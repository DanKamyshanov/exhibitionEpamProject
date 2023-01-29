package com.epam.exhibitions.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public interface Action {
    String execute(HttpServletRequest request);
}
