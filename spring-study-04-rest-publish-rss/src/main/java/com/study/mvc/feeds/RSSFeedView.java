package com.study.mvc.feeds;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;


/*
 *<?xml version="1.0" encoding="UTF-8"?>
 *<rss version="2.0">
 *  <channel>
 *    <title>World Soccer Tournaments</title>
 *    <link>tennis.org</link>
 *    <description>FIFA World Soccer Tournament Calendar</description>
 *    <lastBuildDate>Thu, 22 Oct 2020 17:46:56 GMT</lastBuildDate>
 *    <item>
 *      <title>World Cup - Posted by FIFA</title>
 *      <link>www.fifa.com/worldcup/</link>
 *      <pubDate>Thu, 22 Oct 2020 17:46:56 GMT</pubDate>
 *      <author>FIFA</author>
 *    </item>
 *    <item>
 *      <title>U-20 World Cup - Posted by FIFA</title>
 *      <link>www.fifa.com/u20worldcup/</link>
 *      <pubDate>Thu, 22 Oct 2020 17:46:56 GMT</pubDate>
 *      <author>FIFA</author>
 *    </item>
 *    <item>
 *      <title>U-17 World Cup - Posted by FIFA</title>
 *      <link>www.fifa.com/u17worldcup/</link>
 *      <pubDate>Thu, 22 Oct 2020 17:46:56 GMT</pubDate>
 *      <author>FIFA</author>
 *    </item>
 *    <item>
 *      <title>Confederations Cup - Posted by FIFA</title>
 *      <link>www.fifa.com/confederationscup/</link>
 *      <pubDate>Thu, 22 Oct 2020 17:46:56 GMT</pubDate>
 *      <author>FIFA</author>
 *    </item>
 *  </channel>
 *</rss>
 */
public class RSSFeedView extends AbstractRssFeedView {

    /**
     * RSS 피드의 메타데이터 정보를 할당
     * @param model 피드 데이터가 담겨있다
     * @param feed 피드를 처리하는데 필요한 ROME의 feed 객체
     * @param request HttpServletRequest
     */
    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
        feed.setTitle("World Soccer Tournaments");
        feed.setDescription("FIFA World Soccer Tournament Calendar");
        feed.setLink("tennis.org");

        @SuppressWarnings({"unchecked"})
        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");

        feed.setLastBuildDate(tournamentList.stream().map( TournamentContent::getPublicationDate).sorted().findFirst().orElse(null) );
    }

    /**
     * @param model RSS 피드 데이터가 담겨있다
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return 아톰피드의 순환 엘리먼트를 담고 있는 Entry 객체의 리스트를 반환한다.
     */
    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        @SuppressWarnings({"unchecked"})
        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");

        return tournamentList.stream().map(this::toItem).collect(Collectors.toList());
    }

    private Item toItem(TournamentContent tournament) {
        Item item = new Item();
        item.setAuthor(tournament.getAuthor());
        item.setTitle(String.format("%s - Posted by %s", tournament.getName(), tournament.getAuthor()));
        item.setPubDate(tournament.getPublicationDate());
        item.setLink(tournament.getLink());
        return item;
    }
}
