package com.liferay.grow.journal.attachments.web.dto;

public class Attachment {

	public Attachment() {
		this._title = "Title Placeholder";
		this._url = "";
		this._size = "X.x Mb";
	}

	public String getSize() {
		return _size;
	}

	public String getTitle() {
		return _title;
	}

	public String getUrl() {
		return _url;
	}

	public void setSize(String size) {
		this._size = size;
	}

	public void setTitle(String _title) {
		this._title = _title;
	}

	public void setUrl(String _url) {
		this._url = _url;
	}

	private String _size;
	private String _title;
	private String _url;

}