package com.talent.base.lucence;

import org.apache.lucene.analysis.Analyzer;

public final class CommaAnalyzer extends Analyzer {
  
  public CommaAnalyzer() {
  }
  
  @Override
  protected TokenStreamComponents createComponents(final String fieldName) {
    return new TokenStreamComponents(new CommaTokenizer());
  }
}