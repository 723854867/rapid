package org.rapid.sdk.sina.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.rapid.sdk.sina.enums.CardField;
import org.rapid.sdk.sina.response.tips.CardTips;
import org.rapid.util.CollectionUtil;
import org.rapid.util.reflect.BeanUtil;

import com.google.gson.annotations.SerializedName;

public class QueryCardResponse extends SinaResponse {

	private static final long serialVersionUID = -2451381090923256339L;
	
	private static CardField[] fields = CardField.values();
	
	static {
		Arrays.sort(fields, (o1, o2) -> o1.priority() - o2.priority());
	}

	@SerializedName("card_list")
	private String cardList;
	
	public String getCardList() {
		return cardList;
	}
	
	public void setCardList(String cardList) {
		this.cardList = cardList;
	}
	
	public List<CardTips> cardTips() {
		if (null == cardList)
			return CollectionUtil.emptyList();
		List<CardTips> tips = new ArrayList<CardTips>();
		StringTokenizer tokenizer = new StringTokenizer(cardList, "|");
		while (tokenizer.hasMoreElements()) {
			String value = tokenizer.nextToken();
			CardTips card = _parseCard(value);
			tips.add(card);
		}
		return tips;
	}
	
	private CardTips _parseCard(String value) {
		int idx = 0;
		StringTokenizer tokenizer = new StringTokenizer(value, "^");
		Map<String, String> params = new HashMap<String, String>();
		while (tokenizer.hasMoreElements()) {
			String property = tokenizer.nextToken();
			CardField field = fields[idx++];
			String param = field.process(property);
			params.put(field.mark(), param);
		}
		CardTips cardTips = new CardTips();
		return BeanUtil.mapToBean(params, cardTips);
	}
}
