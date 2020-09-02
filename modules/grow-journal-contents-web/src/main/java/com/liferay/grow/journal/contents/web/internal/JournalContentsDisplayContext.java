package com.liferay.grow.journal.contents.web.internal;


import com.liferay.asset.display.page.constants.AssetDisplayPageWebKeys;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.grow.journal.markdown.engine.api.MarkdownEngine;
import com.liferay.info.display.contributor.InfoDisplayObjectProvider;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.util.JournalConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class JournalContentsDisplayContext{

    public JournalContentsDisplayContext(HttpServletRequest request) {
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

    public DDMFormValues getDDMFormValues() throws PortalException {
        if (_ddmFormValues != null) {
            return _ddmFormValues;
        }

        if (_journalArticle == null) {
            return null;
        }

        DDMStructure ddmStructure = _journalArticle.getDDMStructure();

        if (Validator.isNull(ddmStructure)) {
            return _ddmFormValues;
        }

        String content = _journalArticle.getContent();

        if (Validator.isNull(content)) {
            return _ddmFormValues;
        }

        Registry registry = RegistryUtil.getRegistry();

        JournalConverter journalConverter = registry.getService(
                registry.getServiceReference(JournalConverter.class));

        Fields fields = journalConverter.getDDMFields(ddmStructure, content);

        if (fields == null) {
            return _ddmFormValues;
        }

        _ddmFormValues = journalConverter.getDDMFormValues(
                ddmStructure, fields);

        return _ddmFormValues;
    }


    public String getContent() throws PortalException {
        if (Validator.isNull(_ddmFormValues)) return "";

        String content = "";
        Value selectValue = null;
        for (DDMFormFieldValue value : _ddmFormValues.getDDMFormFieldValues()) {
            String valueName = value.getName();


            if(valueName.equals("select")){
                selectValue = value.getValue();
            }

            if (valueName.equals("content")) {
                Value contentValue = value.getValue();
               ;
                Map<Locale, String> valuesMap = contentValue.getValues();

                if (valuesMap.get(_themeDisplay.getLocale()) != null) {
                    content = valuesMap.get(_themeDisplay.getLocale());
                } else {
                    content = valuesMap.get(
                            contentValue.getDefaultLocale());
                }
            }
        }
        Map<Locale, String> contentValue =  selectValue.getValues();

        Map.Entry<Locale, String> entry = contentValue.entrySet().iterator().next();
        String value = entry.getValue();


        if("[\"html\"]".equals(value)){
            return content;
        }else {
            MarkdownEngine markdownEngine = new MarkdownEngine();
            return markdownEngine.convert(content);
        }

    }
    
    private DDMFormValues _ddmFormValues = null;
    private HttpServletRequest _httpServletRequest = null;
    private JournalArticle _journalArticle = null;
    private ThemeDisplay _themeDisplay = null;

}