package com.liferay.wiki.engine.markdown.pegdown.ast;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import org.pegdown.LinkRenderer;
import org.pegdown.Parser;
import org.pegdown.PegDownProcessor;
import org.pegdown.ast.RootNode;
public class LiferayPegDownProcessor extends PegDownProcessor {

	public LiferayPegDownProcessor(Parser parser) {
		super(parser);
	}

	@Override
	public String markdownToHtml(
		char[] markdownSource, LinkRenderer linkRenderer) {

		RootNode rootNode = parseMarkdown(markdownSource);

		LiferayToHtmlSerializer liferayToHtmlSerializer =
			new LiferayToHtmlSerializer(linkRenderer);

		String html = liferayToHtmlSerializer.toHtml(rootNode);

		return stripIds(html);
	}

	protected String stripIds(String content) {
		int index = content.indexOf("[](id=");

		if (index == -1) {
			return content;
		}

		StringBundler sb = new StringBundler();

		do {
			int x = content.indexOf(StringPool.EQUAL, index);

			int y = content.indexOf(StringPool.CLOSE_PARENTHESIS, x);

			if (y != -1) {
				int z = content.indexOf("</h", y);

				if (z != (y + 1)) {
					sb.append(content.substring(0, y + 1));
				}
				else {
					sb.append(
						StringUtil.trimTrailing(content.substring(0, index)));
				}

				content = content.substring(y + 1);
			}
			else {
				if (_log.isWarnEnabled()) {
					String msg = content.substring(index);

					// Get the invalid id text from the content

					int spaceIndex = content.indexOf(StringPool.SPACE);

					if (spaceIndex != -1) {
						msg = content.substring(index, spaceIndex);
					}

					_log.warn(
						"Missing ')' for web content containing header id " +
							msg);
				}

				// Since no close parenthesis remains in the content, stop
				// stripping out IDs and simply include all of the remaining
				// content

				break;
			}
		}
		while ((index = content.indexOf("[](id=")) != -1);

		sb.append(content);

		return sb.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LiferayPegDownProcessor.class);

}