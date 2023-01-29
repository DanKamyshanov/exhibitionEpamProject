package com.epam.exhibitions.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class LandingTextTag extends SimpleTagSupport {

    private String input;

    public void setInput(String input){
        this.input = input;
    }

    StringWriter sw = new StringWriter();

    public void doTag() throws JspException, IOException{
        if(input != null){
            JspWriter out = getJspContext().getOut();
            out.println(input);
        } else{
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }
    }
}