package com.melon.helloes.runner;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.*;
/*
import org.bitbucket.eunjeon.mecab_ko_lucene_analyzer.MeCabKoTokenizer;
import org.bitbucket.eunjeon.mecab_ko_lucene_analyzer.StandardPosAppender;
import org.bitbucket.eunjeon.mecab_ko_lucene_analyzer.TokenizerOption;
import org.bitbucket.eunjeon.mecab_ko_lucene_analyzer.tokenattributes.PartOfSpeechAttribute;
import org.bitbucket.eunjeon.mecab_ko_lucene_analyzer.tokenattributes.SemanticClassAttribute;

import com.melon.helloes.domain.MToken;
import com.melon.helloes.domain.TermCount;

public class MMecabKoPosTagger {
	private static final int DECOMPOUND_MIN_LENGTH = 9999; // 복합명사 분해하지 않음
	private static final int NOUNMAP_MAX_SIZE = 20;
	private static final int NOUNMAP_MIN_COUNT_PER_SONG = 3;
	private static final int NOUNMAP_MIN_COUNT_PER_ARTIST = 5;
	private TokenizerOption option = new TokenizerOption();

	public List<MToken> analyzeLine(String line) {
		Tokenizer tokenizer = createTokenizer(new StringReader(line), DECOMPOUND_MIN_LENGTH);

		OffsetAttribute extOffset = tokenizer.addAttribute(OffsetAttribute.class);
		PositionIncrementAttribute posIncrAtt = tokenizer.addAttribute(PositionIncrementAttribute.class);
		PositionLengthAttribute posLengthAtt = tokenizer.addAttribute(PositionLengthAttribute.class);
		CharTermAttribute term = tokenizer.addAttribute(CharTermAttribute.class);
		TypeAttribute type = tokenizer.addAttribute(TypeAttribute.class);
		SemanticClassAttribute semanticClass = tokenizer.addAttribute(SemanticClassAttribute.class);
		PartOfSpeechAttribute pos = tokenizer.addAttribute(PartOfSpeechAttribute.class);

		List<MToken> tokens = new ArrayList<MToken>();
		try {
			tokenizer.reset();
			while (tokenizer.incrementToken() == true) {
				MToken token = new MToken();
				token.setTerm(new String(term.buffer(), 0, term.length()));
				token.setType(type.type());
				token.setPos(pos.partOfSpeech());
				token.setSematicClass(semanticClass.semanticClass());
				token.setPosition(posIncrAtt.getPositionIncrement());
				token.setLength(posLengthAtt.getPositionLength());
				token.setStartOffset(extOffset.startOffset());
				token.setEndOffset(extOffset.endOffset());

				tokens.add(token);

			}
			tokenizer.end();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tokens;
	}

	public TreeSet<TermCount> getNounMap(String... lines) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		TreeSet<TermCount> termSet = new TreeSet<TermCount>();

		for (String line : lines) {
			List<MToken> tokens = this.analyzeLine(line);

			for (MToken token : tokens) {
				if ((token.getType().equals("NNG") || token.getType().equals("NNP")) && token.getTerm().length() > 1) {

					if (map.containsKey(token.getTerm())) {
						map.put(token.getTerm(), map.get(token.getTerm()) + 1);
					} else {
						map.put(token.getTerm(), 1);
					}
				}
			}
		}

		for (String term : map.keySet()) {
			if (map.get(term) >= NOUNMAP_MIN_COUNT_PER_SONG) {
				termSet.add(new TermCount(term, map.get(term)));
			}
		}

		return termSet;
	}

	public TreeSet<TermCount> getFilteredNounMap(List<TreeSet<TermCount>> sets) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		TreeSet<TermCount> termSet = new TreeSet<TermCount>();

		for (TreeSet<TermCount> set : sets) {
			Iterator<TermCount> iterator = set.iterator();

			while (iterator.hasNext()) {
				TermCount next = iterator.next();

				if (map.containsKey(next.getTerm())) {
					map.put(next.getTerm(), map.get(next.getTerm()) + next.getCount());
				} else {
					map.put(next.getTerm(), next.getCount());
				}
			}
		}

		for (String term : map.keySet()) {
			if (map.get(term) >= NOUNMAP_MIN_COUNT_PER_ARTIST) {
				termSet.add(new TermCount(term, map.get(term)));
			}
		}

		return termSet;
	}

	private Tokenizer createTokenizer(StringReader reader, int compoundNounMinLength) {
		option.compoundNounMinLength = compoundNounMinLength;
		Tokenizer tokenizer = new MeCabKoTokenizer(reader, option, new StandardPosAppender(option));
		return tokenizer;
	}

	public static void main(String[] args) {
		MMecabKoPosTagger tagger = new MMecabKoPosTagger();
		// List<MToken> tokens = tagger.posTagLine("나는아이유를사랑안합니다");
		// List<MToken> tokens = tagger.posTagLine("나는틴탑를사랑안합니다");
		// List<MToken> tokens = tagger.analyzeLine("너는 내 취향저격 내 취향저격");
		//
		// for ( MToken token : tokens ) {
		// System.out.println(token);
		// }

		// String lines = "너는 내 취향저격 내 취향저격@@@말하지 않아도 느낌이 와@@@머리부터 발끝까지 다@@@너는 내 취향저격 난 너를 보면@@@가지고 싶어서 안달이 나@@@자기 전까지도
		// 생각이 나@@@pow@@@가는 발목 아래 운동화와@@@청색 스키니진의 완벽한 조화@@@살짝 큰 듯한 가디건 아래@@@뚝 떨어진 긴 생머리가 참 고와@@@수줍은 눈웃음도@@@멍 때리는 듯한
		// 모습도@@@예쁘게만 보이고 가슴이 막 떨려@@@왜 이제야 나타났니@@@you're my 취향저격@@@oh oh 너의 가녀린 미소@@@oh oh 나를 보는 눈빛도@@@흠잡을 데가 없어@@@한시도
		// 지루할 틈이 없어 perfect@@@oh oh 애교 섞인 목소리@@@oh oh 가까워지는 우리@@@왜 이제야 나타났어@@@드디어 사랑이 찾아왔어@@@너는 내 취향저격 내 취향저격@@@말하지 않아도
		// 느낌이 와@@@머리부터 발끝까지 다@@@너는 내 취향저격 난 너를 보면@@@가지고 싶어서 안달이 나@@@자기 전까지도 생각이 나@@@pow@@@A girl 잠시 말 좀 물을게@@@그 까칠한 말투는
		// 어디서 또 배웠대@@@you work and walk@@@and spit words like a boss@@@wonder woman@@@현실판은 너의 여성스러움@@@Oh 느낌적인 느낌@@@또 뭔가
		// 매혹적인 눈빛에 매료돼@@@섬세한 그 손길@@@또 뭔가 어정쩡한 몸짓@@@I like it babe@@@oh oh 너의 가녀린 미소@@@oh oh 나를 보는 눈빛도@@@흠잡을 데가 없어@@@한시도
		// 지루할 틈이 없어 perfect@@@oh oh 애교 섞인 목소리@@@oh oh 가까워지는 우리@@@왜 이제야 나타났어@@@드디어 사랑이 찾아왔어@@@너는 내 취향저격 내 취향저격@@@말하지 않아도
		// 느낌이 와@@@머리부터 발끝까지 다@@@너는 내 취향저격 난 너를 보면@@@가지고 싶어서 안달이 나@@@자기 전까지도 생각이 나@@@pow@@@난 니가 정말 좋아@@@또 가끔 하품하는
		// 모습까지도@@@내 스타일이야@@@오 마음이 잘 맞아@@@내 가슴이 벅차올라@@@니가 날 부를 때마다@@@입가에 웃음이 끊이질 않아@@@Oh girl you know that I love
		// you@@@너는 내 취향저격 내 취향저격@@@말하지 않아도 느낌이 와@@@머리부터 발끝까지 다@@@너는 내 취향저격 난 너를 보면@@@가지고 싶어서 안달이 나@@@자기 전까지도 생각이
		// 나@@@pow@@@난 니가 정말 좋아@@@또 가끔 하품하는 모습까지도@@@내 스타일이야@@@난 니가 정말 좋아@@@또 가끔 하품하는 모습까지도@@@내 스타일이야";
		// TreeSet<TermCount> nounSet = tagger.getNounMap(lines.split("@@@"));
		//
		// System.out.println(nounSet);
		//
		// String lines2 = "Ya ready uh@@@Da da da da dalat da da na@@@Dat da na 왜 이럴까 난@@@오늘따라@@@익숙한 스킨쉽에도 심장이 뛰고@@@신경
		// 쓰여 괜히@@@너의 눈치를 보게 돼@@@평소와 같은@@@목소리로 부를 때도@@@오묘한 듯 애매한 게@@@익숙한 듯 설레이네@@@이건 뭔가 간지러운 느낌이 와@@@친구인 니가 오늘따라 왜@@@여자로
		// 보일까@@@뭐지 이 오묘한 느낌은 뭐지@@@그러고 보니 니가 원래 이렇게@@@예뻤었나 오늘따라@@@내가 취했나@@@왜 오늘따라 니가 더 예뻐 보일까@@@지금 너에게 반하는 중@@@왜 오늘따라 니가
		// 여자로 보일까@@@점점 너에게 빠지는 중@@@Sing it like@@@Da da da da dalat da da na@@@Dat da na 왜 이럴까 난@@@오늘따라@@@음 신기함 난지
		// 넌지@@@모르겠지만 있네 문제가@@@Uh uh 감긴가 온몸이 떨리고@@@왜 심장이 뛸까@@@너와 내가 그어왔던@@@친한 친구라는 선@@@니 하이힐 뒤에 넌@@@내 바로 맞은편@@@가까워진 얼굴
		// 아름다운 너@@@오늘따라 심장이@@@네게 반응하는 걸 uh@@@이건 뭔가 간지러운 느낌이 와@@@친구인 니가 오늘따라 왜@@@여자로 보일까@@@뭐지 이 오묘한 느낌은 뭐지@@@그러고 보니 니가
		// 원래 이렇게@@@예뻤었나 오늘따라@@@화장이 잘 됐나@@@왜 오늘따라 니가 더 예뻐 보일까@@@지금 너에게 반하는 중@@@왜 오늘따라 니가 여자로 보일까@@@점점 너에게 빠지는 중@@@All my
		// fellas say@@@Da da da da dalat da da na@@@Dat da na 왜 이럴까 난@@@오늘따라@@@자꾸 쳐다보지마 eh@@@오늘따라 왠지 어색하니까 woo@@@내 맘 넌
		// 모르겠지만 eh@@@복잡한 이 감정 어쩌면@@@이건 사랑일지도 몰라@@@오늘따라 니가 더 예뻐 보일까@@@지금 너에게 반하는 중@@@왜 오늘따라 니가 여자로 보일까@@@점점 너에게 빠지는
		// 중@@@All my fellas say@@@Da da da da dalat da da na@@@Dat da na 왜 이럴까 난@@@오늘따라@@@뭔가 달라";
		// TreeSet<TermCount> nounSet2 = tagger.getNounMap(lines2.split("@@@"));
		// System.out.println(nounSet2);
		//
		// List<TreeSet<TermCount>> sets = new ArrayList<TreeSet<TermCount>>();
		// sets.add(nounSet);
		// sets.add(nounSet2);
		// TreeSet<TermCount> filteredNounMap = tagger.getFilteredNounMap(sets);
		// System.out.println(filteredNounMap);

	}
}
*/