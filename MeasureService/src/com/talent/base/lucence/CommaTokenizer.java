package com.talent.base.lucence;

import org.apache.lucene.analysis.util.CharTokenizer;
import org.apache.lucene.util.AttributeFactory;

public final class CommaTokenizer extends CharTokenizer {

	public CommaTokenizer() {
	}

	public CommaTokenizer(AttributeFactory factory) {
		super(factory);
	}

	@Override
	protected boolean isTokenChar(int c) {
		return !(44 == c);
	}
}