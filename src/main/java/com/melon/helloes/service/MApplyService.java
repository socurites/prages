package com.melon.helloes.service;

import java.util.Map;

import com.melon.helloes.domain.NetworkItem;

public interface MApplyService {
	Map<String, NetworkItem> getPowerNetwork(String artistName, int size);
}
