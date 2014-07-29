package com.scrappy.sites.interpretor;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.scrappy.protogen.Scrape.Scraper;
import com.sun.istack.internal.Nullable;

/**
 * Registry for mapping scrape configurations with domain names.
 */
@Singleton
public class ScrapeConfigRegistry {

  private final Map<URI, Scraper> domainToScraperMap;

  @Inject
  public ScrapeConfigRegistry(Map<URI, Scraper> domainToScraperMap) {
    this.domainToScraperMap = domainToScraperMap;
  }

  /**
   * Adds a domain scrapper mapping to registry.
   * 
   * @param domain the domain name
   * @param scraper the scrapper configuration
   */
  public synchronized void addToConfig(URI domain, Scraper scraper) {
    Preconditions.checkNotNull(domain, "Domain can not be null");
    Preconditions.checkNotNull(scraper, "Scraper can not be null");
    this.domainToScraperMap.put(domain, scraper);
  }

  public synchronized void addToConfig(String domain, Scraper scraper) throws URISyntaxException {
    Preconditions.checkNotNull(domain, "Domain can not be null");
    Preconditions.checkNotNull(scraper, "Scraper can not be null");
    URI uri = new URI(domain);
    addToConfig(uri, scraper);
  }

  /**
   * Returns the scraper configuration on the basis of domain name.
   * 
   * @param domain the domain to lookup
   * @return the scraper configuration or null
   */
  public @Nullable
  Scraper getScraperConfig(URI domain) {
    return this.domainToScraperMap.get(domain);
  }

  /**
   * Returns the scraper configuration on the basis of domain name.
   * 
   * @param domain the domain to lookup
   * @return the scraper configuration or null
   */
  public @Nullable
  Scraper getScraperConfig(String domain) {
    if (null != domain) {
      try {
        URI uri = new URI(domain);
        return getScraperConfig(uri);
      } catch (URISyntaxException e) {
        // Intentionally ignore.
      }
    }

    return null;
  }
}
