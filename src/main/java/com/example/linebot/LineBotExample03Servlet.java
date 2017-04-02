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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.riversun.linebot.LineBotServlet;

import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;

/**
 * 
 * LINE BOT Example03<br>
 * 画像を受信して保存するServlet
 *
 */
@SuppressWarnings("serial")
public class LineBotExample03Servlet extends LineBotServlet {

	private static final String CHANNEL_SECRET = ResourceBundle.getBundle("credentials").getString("line.channel_secret");
	private static final String CHANNEL_ACCESS_TOKEN = ResourceBundle.getBundle("credentials").getString("line.channel_access_token");

	@Override
	protected ReplyMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {
		return new ReplyMessage(event.getReplyToken(), Arrays.asList(new TextMessage("画像を送ってね")));
	}

	@Override
	protected ReplyMessage handleImageMessageEvent(MessageEvent<ImageMessageContent> event) throws IOException {

		// Contentについてはこちら https://devdocs.line.me/ja/#content
		InputStream is = getContentStream(event.getMessage());

		// 画像ファイルの保存先パス
		String tempImageFilePath = System.getProperty("user.dir") + "/" + "line_img_" + System.currentTimeMillis() + ".jpg";

		// ファイルを保存
		Files.copy(is, Paths.get(tempImageFilePath));

		return new ReplyMessage(event.getReplyToken(), Arrays.asList(new TextMessage("画像をありがとう！")));

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
