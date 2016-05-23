package com.htmltemp.webapp.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created with IntelliJ IDEA.
 * User:lk
 * Date: 16-5-23
 * Time: 上午11:28
 * To change this template use File | Settings | File Templates.
 */
public class DirectoyDto {
    private String name;
    private String url;
    private String icon;
    public DirectoyDto(){

    }
    public DirectoyDto(String name,String url,String icon){
         this.name=name;
         this.url=url;
         this.icon=icon;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String toString()
    {
      return new ToStringBuilder(this).toString();
    }
}
