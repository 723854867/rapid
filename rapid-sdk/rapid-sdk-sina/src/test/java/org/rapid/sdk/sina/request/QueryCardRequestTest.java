package org.rapid.sdk.sina.request;

import java.util.List;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.QueryCardResponse;
import org.rapid.sdk.sina.response.tips.CardTips;

public class QueryCardRequestTest extends SinaTest {

	@Test
	public void testQuerySingle() {
		QueryCardRequest request = new QueryCardRequest();
		request.setIdentityId("704965772327");
		request.setCardId("225140");
		QueryCardResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
		List<CardTips> cards = response.cardTips();
		for (CardTips card : cards)
			System.out.println(card.getCardId() + " " + card.getBankNo() + " " + card.getAccountName() + " " + card.getBankCode() 
			+ " " + card.getCardAttribute() + " " + card.getCardType() + " " + card.getTime());
	}
	
	@Test
	public void testQueryAll() {
		QueryCardRequest request = new QueryCardRequest();
		request.setIdentityId("704965772327");
		QueryCardResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
		List<CardTips> cards = response.cardTips();
		for (CardTips card : cards)
			System.out.println(card.getCardId() + " " + card.getBankNo() + " " + card.getAccountName() + " " + card.getBankCode() 
			+ " " + card.getCardAttribute() + " " + card.getCardType() + " " + card.getTime());
	}
}
