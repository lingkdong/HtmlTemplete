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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectoyDto that = (DirectoyDto) o;

        if (!icon.equals(that.icon)) return false;
        if (!name.equals(that.name)) return false;
        if (!url.equals(that.url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + icon.hashCode();
        return result;
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
