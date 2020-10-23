package com.study.mvc.feeds;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;

/**
 * 
 *<?xml version="1.0" encoding="UTF-8"?>
 *<feed xmlns="http://www.w3.org/2005/Atom">
 * <title>Grand Slam Tournaments</title>
 * <id>tag:tennis.org</id>
 * <updated>2020-10-22T17:42:53Z</updated>
 * <entry>
 *   <title>Australian Open - Posted by ATP</title>
 *   <id>tag:tennis.org,2020-10-23:1</id>
 *   <updated>2020-10-22T17:42:53Z</updated>
 *   <summary>Australian Open - www.australianopen.com</summary>
 * </entry>
 * <entry>
 *   <title>Roland Garros - Posted by ATP</title>
 *   <id>tag:tennis.org,2020-10-23:2</id>
 *   <updated>2020-10-22T17:42:53Z</updated>
 *   <summary>Roland Garros - www.rolandgarros.com</summary>
 * </entry>
 * <entry>
 *   <title>Wimbledon - Posted by ATP</title>
 *   <id>tag:tennis.org,2020-10-23:3</id>
 *   <updated>2020-10-22T17:42:53Z</updated>
 *   <summary>Wimbledon - www.wimbledon.org</summary>
 * </entry>
 * <entry>
 *   <title>US Open - Posted by ATP</title>
 *   <id>tag:tennis.org,2020-10-23:4</id>
 *   <updated>2020-10-22T17:42:53Z</updated>
 *   <summary>US Open - www.usopen.org</summary>
 * </entry>
 *</feed>
 *
 */
public class AtomFeedView extends AbstractAtomFeedView {

    /**
     * 아톰 피드의 메타데이터 정보를 할당
     * @param model 피드 데이터가 담겨있다
     * @param feed 피드를 처리하는데 필요한 ROME의 feed 객체
     * @param request HttpServletRequest
     */
    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {
        feed.setId("tag:tennis.org");
        feed.setTitle("Grand Slam Tournaments");

        @SuppressWarnings({"unchecked"})
        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");

        feed.setUpdated(tournamentList.stream().map(TournamentContent::getPublicationDate).sorted().findFirst().orElse(null));

    }

    /**
     * @param model 아톰 피드 데이터가 담겨있다
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return 아톰피드의 순환 엘리먼트를 담고 있는 Entry 객체의 리스트를 반환한다.
     */
    @Override
    protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        @SuppressWarnings({"unchecked"})
        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");
        return tournamentList.stream().map(this::toEntry).collect(Collectors.toList());
    }

    private Entry toEntry(TournamentContent tournament) {
        Entry entry = new Entry();
        String date = String.format("%1$tY-%1$tm-%1$td", tournament.getPublicationDate());
        entry.setId(String.format("tag:tennis.org,%s:%d", date, tournament.getId()));
        entry.setTitle(String.format("%s - Posted by %s", tournament.getName(), tournament.getAuthor()));
        entry.setUpdated(tournament.getPublicationDate());

        Content summary = new Content();
        summary.setValue(String.format("%s - %s", tournament.getName(), tournament.getLink()));
        entry.setSummary(summary);
        return entry;
    }
}
