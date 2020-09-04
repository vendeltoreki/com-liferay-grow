package com.liferay.grow.journal.content.header.web.internal;

import com.liferay.asset.display.page.constants.AssetDisplayPageWebKeys;
import com.liferay.info.display.contributor.InfoDisplayObjectProvider;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

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

        if (infoDisplayObjectProvider != null)
            _journalArticle =
                    (JournalArticle)infoDisplayObjectProvider.getDisplayObject();

        _themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
                WebKeys.THEME_DISPLAY);
    }



    private HttpServletRequest _httpServletRequest;
    private JournalArticle _journalArticle = null;
    private ThemeDisplay _themeDisplay;

}