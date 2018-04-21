package com.kakaochatbot.domain;

import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;

import scala.collection.Seq;

public class ClassifySentenceVO 
{
	private int type;
	private String particleMessage;
	private Seq<KoreanTokenizer.KoreanToken> stemmed;
	private Seq<KoreanTokenizer.KoreanToken> tokens;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getParticleMessage() {
		return particleMessage;
	}
	public void setParticleMessage(String particleMessage) {
		this.particleMessage = particleMessage;
	}
	public Seq<KoreanTokenizer.KoreanToken> getStemmed() {
		return stemmed;
	}
	public void setStemmed(Seq<KoreanTokenizer.KoreanToken> stemmed) {
		this.stemmed = stemmed;
	}
	public Seq<KoreanTokenizer.KoreanToken> getTokens() {
		return tokens;
	}
	public void setTokens(Seq<KoreanTokenizer.KoreanToken> tokens) {
		this.tokens = tokens;
	}
	@Override
	public String toString() {
		return "ClassifySentenceVO [type=" + type + ", particleMessage=" + particleMessage + ", stemmed=" + stemmed
				+ ", tokens=" + tokens + "]";
	}
}
