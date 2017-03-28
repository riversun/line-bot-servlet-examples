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
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.postback.PostbackContent;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.profile.UserProfileResponse;

/**
 * 
 * LINE BOT Example04<br>
 * Buttonsテンプレートメッセージ（ボタンによる複数選択）を返すServlet
 *
 */
public class LineBotExample04Servlet extends LineBotServlet {

	private static final long serialVersionUID = 1L;

	private static final String CHANNEL_SECRET = ResourceBundle.getBundle("credentials").getString("line.channel_secret");
	private static final String CHANNEL_ACCESS_TOKEN = ResourceBundle.getBundle("credentials").getString("line.channel_access_token");

	@Override
	protected ReplyMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {

		UserProfileResponse userProfile = getUserProfile(event.getSource().getUserId());

		String thumbnailImageUrl = "https://riversun.github.io/img/riversun_256.png";

		String title = "料理のジャンル選択";
		String text = userProfile.getDisplayName() + "さんは、どんな料理が好きですか？";

		Action japaneseCuisine = new PostbackAction("和食", "japanese");
		Action italianCuisine = new PostbackAction("イタリアン", "italian");
		Action frenchCuisine = new PostbackAction("フレンチ", "french");

		List<Action> actions = Arrays.asList(japaneseCuisine, italianCuisine, frenchCuisine);

		ButtonsTemplate buttonsTemplate = new ButtonsTemplate(thumbnailImageUrl, title, text, actions);

		String altText = title;

		return new ReplyMessage(event.getReplyToken(), new TemplateMessage(altText, buttonsTemplate));
	}

	@Override
	protected ReplyMessage handlePostbackEvent(PostbackEvent event) {
		// ButtonsTemplateでユーザーが選択した結果が、このPostBackEventとして返ってくる

		PostbackContent postbackContent = event.getPostbackContent();

		// PostbackActionで設定したdataを取得する
		String data = postbackContent.getData();

		final String replyText;

		if ("japanese".equals(data)) {
			replyText = "和食がお好きなんですね。";
		} else if ("italian".equals(data)) {
			replyText = "イタリアン、良いですよね。";
		} else {
			replyText = "フレンチ、私も食べたいです。";
		}

		return new ReplyMessage(event.getReplyToken(), Arrays.asList(new TextMessage(replyText)));
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
