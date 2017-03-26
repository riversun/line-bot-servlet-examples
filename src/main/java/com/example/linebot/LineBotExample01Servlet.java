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
import java.util.ResourceBundle;

import org.riversun.linebot.LineBotServlet;

import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.profile.UserProfileResponse;

/**
 * 
 * LINE BOT Example01<br>
 * テキストメッセージを受信してテキストメッセージで返事をするServlet
 *
 */
@SuppressWarnings("serial")
public class LineBotExample01Servlet extends LineBotServlet {

	// CHANNEL_SECRETとCHANNEL_ACCESS_TOKENはそのまま文字列で指定可能
	private static final String CHANNEL_SECRET = ResourceBundle.getBundle("credentials").getString("line.channel_secret");
	private static final String CHANNEL_ACCESS_TOKEN = ResourceBundle.getBundle("credentials").getString("line.channel_access_token");

	@Override
	protected ReplyMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {

		// ユーザーがBOTに送信したメッセージ
		TextMessageContent userMessage = event.getMessage();

		// ユーザーのProfileを取得する
		UserProfileResponse userProfile = getUserProfile(event.getSource().getUserId());

		// BOTからの返信メッセージ
		String botResponseText = userProfile.getDisplayName() + "さん、"
				+ "「" + userMessage.getText() + "」って言いましたね";

		TextMessage textMessage = new TextMessage(botResponseText);

		return new ReplyMessage(event.getReplyToken(), Arrays.asList(textMessage));
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
