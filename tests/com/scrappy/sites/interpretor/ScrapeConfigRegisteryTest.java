package com.scrappy.sites.interpretor;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.scrappy.protogen.Scrape.Scraper;

/**
 * Unit test for {@link ScrapeConfigRegistry}.
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ScrapeConfigRegisteryTest {

  @Mock
  Map<URI, Scraper> mapper;

  private ScrapeConfigRegistry registry;

  @Before
  public void setUp() throws Exception {
    registry = new ScrapeConfigRegistry(mapper);
  }

  @Test
  public void test_add() throws URISyntaxException {
    URI uri = new URI("http://google.com");
    Scraper scraper = Scraper.newBuilder().buildPartial();
    registry.addToConfig(uri, scraper);
    verify(mapper).put(uri, scraper);
  }

  @Test
  public void test_get() throws URISyntaxException {
    URI uri = new URI("http://google.com");
    Scraper scraper = Scraper.newBuilder().buildPartial();
    when(mapper.get(uri)).thenReturn(scraper);
    assertEquals(scraper, registry.getScraperConfig(uri));
    verify(mapper).get(uri);
  }
}
