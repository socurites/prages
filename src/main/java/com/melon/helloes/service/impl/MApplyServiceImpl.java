package com.melon.helloes.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.melon.helloes.domain.NetworkItem;
import com.melon.helloes.service.MApplyService;

/**
 * 컨텐츠 검색 서비스
 * 
 * @author socurites
 *
 */
@Service
public class MApplyServiceImpl extends MAbstractSearchServiceImpl implements MApplyService {
	private static final String AGG_NAME = "list";

	public Map<String, NetworkItem> getPowerNetwork(String artistName, int size) {
		int currentDepth = 0;
		Set<String> visitedArtists = new HashSet<String>();

		Map<String, NetworkItem> items = new HashMap<String, NetworkItem>();
		NetworkItem rootItem = new NetworkItem();
		rootItem.setArtistName(artistName);
		rootItem.setType("아티스트");
		items.put(rootItem.getName(), rootItem);
		visitedArtists.add(rootItem.getName());

		// depth 1
		List<NetworkItem> nextVisitItems = visitCyclic(items, visitedArtists, rootItem, size);

		// depth 2
		List<NetworkItem> nextNextVisitItems = new ArrayList<NetworkItem>();
		for (NetworkItem nextVisitItem : nextVisitItems) {
			List<NetworkItem> nextNextVisitItemsP2 = visitCyclic(items, visitedArtists,
					items.get(nextVisitItem.getName()), size);
			if (nextNextVisitItemsP2 != null) {
				nextNextVisitItems.addAll(nextNextVisitItemsP2);
			}

			NetworkItem networkItem = items.get("윤현상");

			System.out.println("here");
			break;

		}

		// depth 3
		for (NetworkItem nextNextVisitItem : nextNextVisitItems) {
			visitCyclic(items, visitedArtists, nextNextVisitItem, size);
		}

		return items;
	}

	private List<NetworkItem> visitCyclic(Map<String, NetworkItem> items, Set<String> visitedArtists,
			NetworkItem rootItem, int size) {
		if (rootItem.getName().equals("윤현상")) {
			System.out.println(rootItem);
		}

		List<NetworkItem> dependentComposers = getDependentComposers(items, rootItem, size);
		List<NetworkItem> dependentLyricists = getDependentLyricists(items, rootItem, size);
		List<NetworkItem> dependentArrangers = getDependentArrangers(items, rootItem, size);

		List<NetworkItem> dependentNonArtists = new ArrayList<NetworkItem>();
		dependentNonArtists.addAll(dependentComposers);
		dependentNonArtists.addAll(dependentLyricists);
		dependentNonArtists.addAll(dependentArrangers);

		List<NetworkItem> nextVisitItems = null;
		for (NetworkItem dependentItem : dependentNonArtists) {
			if (!visitedArtists.contains(dependentItem.getName())) {
				visitedArtists.add(dependentItem.getName());

				nextVisitItems = getDependentArtist(items, dependentItem, size);
			} else {
				rootItem.addDependArtist(dependentItem.getArtistName());
			}

			if (rootItem.getName().equals("윤현상")) {
				NetworkItem networkItem = items.get("윤현상");

				System.out.println("here");
			}

		}

		NetworkItem networkItem = items.get("윤현상");

		System.out.println("here");

		return nextVisitItems;
	}

	private List<NetworkItem> getDependentComposers(Map<String, NetworkItem> items, NetworkItem parentItem, int size) {
		return this.getDependents(items, parentItem, size, "작곡가", "artist_composer_aggs.dsl", false);
	}

	private List<NetworkItem> getDependentLyricists(Map<String, NetworkItem> items, NetworkItem parentItem, int size) {
		return this.getDependents(items, parentItem, size, "작사가", "artist_lyricist_aggs.dsl", false);
	}

	private List<NetworkItem> getDependentArrangers(Map<String, NetworkItem> items, NetworkItem parentItem, int size) {
		return this.getDependents(items, parentItem, size, "편곡자", "artist_arranger_aggs.dsl", false);
	}

	private List<NetworkItem> getDependentArtist(Map<String, NetworkItem> items, NetworkItem parentItem, int size) {
		return this.getDependents(items, parentItem, size, "아티스트", "nonartist_artist_aggs.dsl", true);
	}

	private List<NetworkItem> getDependents(Map<String, NetworkItem> items, NetworkItem parentItem, int size,
			String type, String dslLocation, boolean isArtistSearch) {
		List<NetworkItem> dependentItems = new ArrayList<NetworkItem>();

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("term", parentItem.getArtistName());
		paramMap.put("size", size + "");

		String result = doSearch(paramMap, "helloes/search_dsl/apply/" + dslLocation, "count");
		JsonElement root = new JsonParser().parse(result);
		JsonArray jsonArray = null;
		NetworkItem dependentItem = null;
		if (root.getAsJsonObject().get("hits").getAsJsonObject().get("total").getAsInt() > 0) {
			jsonArray = root.getAsJsonObject().get("aggregations").getAsJsonObject().get(AGG_NAME).getAsJsonObject()
					.get("buckets").getAsJsonArray();

			for (JsonElement jsonElement : jsonArray) {
				if (!jsonElement.getAsJsonObject().get("key").getAsString().equals(parentItem.getArtistName())) {
					if (items.containsKey(jsonElement.getAsJsonObject().get("key").getAsString().replaceAll(" ", ""))) {
						dependentItem = items
								.get(jsonElement.getAsJsonObject().get("key").getAsString().replaceAll(" ", ""));
					} else {
						dependentItem = new NetworkItem();
						dependentItem.setArtistName(jsonElement.getAsJsonObject().get("key").getAsString());
						dependentItem.setType(type);
						items.put(dependentItem.getName(), dependentItem);
					}
					dependentItems.add(dependentItem);

					if (isArtistSearch) {
						dependentItem.addDependArtist(parentItem.getArtistName());
					} else {
						parentItem.addDependArtist(dependentItem.getArtistName());
					}
				}
			}
		}

		return dependentItems;
	}

	public static void main(String[] args) {
		MApplyServiceImpl service = new MApplyServiceImpl();
		Map<String, NetworkItem> powerNetwork = service.getPowerNetwork("아이유", 5);

		Gson gson = new Gson();
		System.out.println(gson.toJson(powerNetwork));
	}

}
