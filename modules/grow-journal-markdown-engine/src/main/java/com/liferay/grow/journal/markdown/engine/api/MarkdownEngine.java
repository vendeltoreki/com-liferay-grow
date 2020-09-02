/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.grow.journal.markdown.engine.api;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.gitlab.GitLabExtension;
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.Arrays;

/**
 * @author Marcell Gyöpös
 */

@Component(immediate = true)
public class MarkdownEngine {

    private static Parser _parser = null;
    private static HtmlRenderer _renderer = null;

    private MutableDataSet _getOptions() {
        MutableDataSet options = new MutableDataSet();

        // uncomment to set optional extensions

        options.set(
                Parser.EXTENSIONS,
                Arrays.asList(
                        AnchorLinkExtension.create(), AutolinkExtension.create(),
                        DefinitionExtension.create(), EmojiExtension.create(),
                        FootnoteExtension.create(), GitLabExtension.create(),
                        StrikethroughExtension.create(), SuperscriptExtension.create(),
                        TablesExtension.create(), TaskListExtension.create(),
                        TocExtension.create()));


        // Use 2 dashes to be compatible with StackEdit

        options.set(TablesExtension.MIN_SEPARATOR_DASHES, 1);

        // uncomment to convert soft-breaks to hard breaks

        options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        return options;
    }

    public String convert(String content) {

        Node document = _parser.parse(content);

        String temp = _renderer.render(document);

        return temp;
    }

    @Activate
    protected void activate() {

        MutableDataSet options = _getOptions();

        Parser.Builder parserBuilder = Parser.builder(options);

        HtmlRenderer.Builder htmlRendererBuilder = HtmlRenderer.builder(options);

        _renderer = htmlRendererBuilder.build();
         _parser =  parserBuilder.build();
    }

    @Deactivate
    protected void deactive() {
        _renderer = null;
        _parser =  null;
    }


    private static final Log _log = LogFactoryUtil.getLog(MarkdownEngine.class);




}