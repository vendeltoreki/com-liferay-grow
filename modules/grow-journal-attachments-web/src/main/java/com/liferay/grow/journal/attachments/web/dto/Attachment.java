package com.liferay.grow.journal.attachments.web.dto;

public class Attachment {

    public Attachment() {
        this._title = "Title Placeholder";
        this._url = "";
        this._size = "X.x Mb";
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String _url) {
        this._url = _url;
    }

    public String getSize() {
        return _size;
    }

    public void setSize(String size) {
        this._size = size;
    }

    private String _title;
    private String _url;
    private String _size;
}
