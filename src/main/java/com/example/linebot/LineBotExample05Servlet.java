/* 
 *  Copyright (c) 2017 Tom Misawa, riversun.org@gmail.com
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a
 *  copy of this software and associated documentation files (the "Software"),
 *  to deal in the Software without restriction, including without limitation
 *  the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *  and/or sell copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 *  DEALINGS IN THE SOFTWARE.
 *  
 */
package com.example.linebot;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.riversun.linebot.LineBotServlet;

import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.postback.PostbackContent;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;

/**
 * 
 * LINE BOT Example05<br>
 * Buttonsテンプレートメッセージ（ボタンによる複数選択でリンクを返す）を返すServlet
 *
 */
public class LineBotExample05Servlet extends LineBotServlet {

	private static final long serialVersionUID = 1L;

	private static final String CHANNEL_SECRET = ResourceBundle.getBundle("credentials").getString("line.channel_secret");
	private static final String CHANNEL_ACCESS_TOKEN = ResourceBundle.getBundle("credentials").getString("line.channel_access_token");

	@Override
	protected ReplyMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {

		String thumbnailImageUrl = "https://riversun.github.io/img/riversun_256.png";

		String title = "好きな検索エンジン";
		String text = "好きな検索エンジンを選んでください";

		Action google = new URIAction("Google", "https://www.google.co.jp");
		Action bing = new URIAction("Bing", "https://www.bing.com");
		Action other = new PostbackAction("その他", "other");

		List<Action> actions = Arrays.asList(google, bing, other);

		ButtonsTemplate buttonsTemplate = new ButtonsTemplate(thumbnailImageUrl, title, text, actions);

		String altText = title;

		return new ReplyMessage(event.getReplyToken(), new TemplateMessage(altText, buttonsTemplate));
	}

	@Override
	protected ReplyMessage handlePostbackEvent(PostbackEvent event) {

		PostbackContent postbackContent = event.getPostbackContent();

		String data = postbackContent.getData();

		if ("other".equals(data)) {
			return new ReplyMessage(event.getReplyToken(), new TextMessage("他の検索エンジンが好きなんですね。"));
		} else {
			return null;
		}

	}

	@Override
	protected ReplyMessage handleDefaultMessageEvent(Event event) {
		// overrideしていないメッセージを受信した場合は何もしない(nullを返す)
		return null;
	}

	@Override
	public String getChannelSecret() {
		return CHANNEL_SECRET;
	}

	@Override
	public String getChannelAccessToken() {
		return CHANNEL_ACCESS_TOKEN;
	}

}
