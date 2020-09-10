package com.liferay.grow.journal.content.header.web.internal;

import com.liferay.asset.display.page.constants.AssetDisplayPageWebKeys;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.grow.journal.content.header.web.dto.Header;
import com.liferay.info.display.contributor.InfoDisplayObjectProvider;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marcell Gyöpös
 */
public class JournalContentHeaderDisplayContext {

	public JournalContentHeaderDisplayContext(HttpServletRequest request) {
		_httpServletRequest = request;

		InfoDisplayObjectProvider infoDisplayObjectProvider =
			(InfoDisplayObjectProvider)request.getAttribute(
				AssetDisplayPageWebKeys.INFO_DISPLAY_OBJECT_PROVIDER);

		if (infoDisplayObjectProvider != null) {
			_journalArticle =
				(JournalArticle)infoDisplayObjectProvider.getDisplayObject();

			_assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
				JournalArticle.class.getName(),
				_journalArticle.getResourcePrimKey());
		}

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_initContentHeader();
	}

	public Header getFilledHeader() {
		return header;
	}

	private void _initContentHeader() {
		header = new Header();

		if (_journalArticle != null) {
			header.setCreator(_journalArticle.getUserName());
			header.setCreateDate(
				dateFormat.format(_journalArticle.getCreateDate()));
			header.setTitle(
				_journalArticle.getTitle(_themeDisplay.getLocale(), true));
			header.setViewCount(_assetEntry.getViewCount());
		}
	}

	private static final DateFormat dateFormat = new SimpleDateFormat(
		"MMM d, yyyy");

	private AssetEntry _assetEntry;
	private HttpServletRequest _httpServletRequest;
	private JournalArticle _journalArticle = null;
	private ThemeDisplay _themeDisplay;
	private Header header = null;

}