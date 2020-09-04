package com.liferay.grow.journal.content.header.web.dto;


import java.util.Date;

/**
 * @author Marcell Gyöpös
 */
public class Header {
    public Header() {
        this._creator = "Creator Place Holder";
        this._title = "Title Place Holder";
        this._createDate = new Date().toString();
        this._viewCount = 0;
        this._parentPage = "Parent Page Placeholder";
    }

    public String getCreator() {
        return _creator;
    }

    public String getTitle() {
        return _title;
    }

    public String getCreateDate() {
        return _createDate;
    }

    public int getViewCount() {
        return _viewCount;
    }

    public String getParentPage() {
        return _parentPage;
    }

    public void setCreator(String _creator) {
        this._creator = _creator;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public void setCreateDate(String _createDate) {
        this._createDate = _createDate;
    }

    public void setViewCount(int _viewCount) {
        this._viewCount = _viewCount;
    }

    public void setParentPage(String parentPage) {
        this._parentPage = parentPage;
    }

    private String _creator;
    private String _title;
    private String _createDate;
    private int _viewCount;
    private String _parentPage;

}
