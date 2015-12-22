/*
 * (@)# <<LanguageUtils.java>>
 *
 * 2013. 12. 30
 *
 * ====================================================================
 *
 * LOEN Entertainment, Software License, Version 1.0
 *
 * Copyright (c) 2010 LOEN. All rights reserved.
 *
 * DON'T COPY OR REDISTRIBUTE THIS SOURCE CODE WITHOUT PERMISSION.
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL LOEN Entertainment OR ITS
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * For more information on this product, please see
 * www.melon.com
 *
 */
package com.melon.helloes.util;

/**
 * Elasticesearch 언어처리 유틸 클래스.
 *
 * @since 2015-06-15
 * @author socurites
 */
public class MLanguageUtil {
	// 아래는 한->영 변환을 위한 AztTopic의 멤버 필드. 공통 모듈로 도출할 것.
	/* **********************************************
	 * 자음 모음 분리
	 * 설연수 -> ㅅㅓㄹㅇㅕㄴㅅㅜ,    바보 -> ㅂㅏㅂㅗ
	 * **********************************************/
	private static char[] arrChoSung = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138,
		0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148,
		0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
	/** 중성 - 가(ㅏ), 야(ㅑ), 뺨(ㅑ)*/
	private static char[] arrJungSung = { 0x314f, 0x3150, 0x3151, 0x3152,
			0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a,
			0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162,
			0x3163 };
	/** 종성 - 가(없음), 갈(ㄹ) 천(ㄴ) */
	private static char[] arrJongSung = { 0x0000, 0x3131, 0x3132, 0x3133,
			0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c,
			0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145,
			0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
	
	/* **********************************************
	 * 알파벳으로 변환
	 * 설연수 -> tjfdustn, 멍충 -> ajdcnd 
	 * **********************************************/
	/** 초성 - 가(ㄱ), 날(ㄴ) 닭(ㄷ) */
	private static String[] arrChoSungEng = { "r", "R", "s", "e", "E",
		"f", "a", "q", "Q", "t", "T", "d", "w",
		"W", "c", "z", "x", "v", "g" };
	
	/** 중성 - 가(ㅏ), 야(ㅑ), 뺨(ㅑ)*/
	private static String[] arrJungSungEng = { "k", "o", "i", "O",
		"j", "p", "u", "P", "h", "hk", "ho", "hl",
		"y", "n", "nj", "np", "nl", "b", "m", "ml",
		"l" };
	
	/** 종성 - 가(없음), 갈(ㄹ) 천(ㄴ) */
	private static String[] arrJongSungEng = { "", "r", "R", "rt",
		"s", "sw", "sg", "e", "f", "fr", "fa", "fq",
		"ft", "fx", "fv", "fg", "a", "q", "qt", "t",
		"T", "d", "w", "c", "z", "x", "v", "g" };
	
	/** 단일 자음 - ㄱ,ㄴ,ㄷ,ㄹ... (ㄸ,ㅃ,ㅉ은 단일자음(초성)으로 쓰이지만 단일자음으론 안쓰임) */
	private static String[] arrSingleJaumEng = { "r", "R", "rt",
		"s", "sw", "sg", "e","E" ,"f", "fr", "fa", "fq",
		"ft", "fx", "fv", "fg", "a", "q","Q", "qt", "t",
		"T", "d", "w", "W", "c", "z", "x", "v", "g" };
	
	/**
	 * word가 한글로 시작하는지 검사.
	 * 
	 * @param word
	 * @return
	 */
	public static boolean isStartedAsHangul(String word){
		String test = word.substring(0, 1);
		int ascii = (int) test.toUpperCase().charAt(0);
		int charset = getSortNumber(ascii);
		
		if ( charset == 1 ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ascii 코드의 언어 유형을 리턴(1-한글, 2-알파벳, 3-숫자, 4-특수문자).
	 * 
	 * 
	 * @param ascii(알파벳의 경우 대문자로 변환한 값의 ascii 코드를 전달)
	 * @return
	 */
	private static int getSortNumber(int ascii) {
		if ( (ascii >= 12593 && ascii <= 12686) || ( ascii >= 44032 && ascii <= 55203 )) {		// 한글
			return 1;
		} else if ( ascii >= 65 && ascii <= 90 ) {		// 알파벳
			return 2;
		} else if ( ascii >= 48 && ascii <= 57 ) {		// 숫자
			return 3;
		} else {										// 기타 특수문자
			return 4;
		}
	}
	
	/**
	 * 한글을 영어로 변환
	 * 
	 * @param word
	 * @return
	 */
	public static String convertKo2Eng(String word) {
		String result		= "";									// 결과 저장할 변수
		String resultEng	= "";									// 알파벳으로
		for (int i = 0; i < word.length(); i++) {
			
			/*  한글자씩 읽어들인다. */
			char chars = (char) (word.charAt(i) - 0xAC00);

			if (chars >= 0 && chars <= 11172) {
				/* A. 자음과 모음이 합쳐진 글자인경우 */

				/* A-1. 초/중/종성 분리 */
				int chosung 	= chars / (21 * 28);
				int jungsung 	= chars % (21 * 28) / 28;
				int jongsung 	= chars % (21 * 28) % 28;
				
				/* A-2. result에 담기 */
				result = result + arrChoSung[chosung] + arrJungSung[jungsung];
				
				/* 자음분리 */
				if (jongsung != 0x0000) {
					/* A-3. 종성이 존재할경우 result에 담는다 */
					result =  result + arrJongSung[jongsung];
				}

				/* 알파벳으로 */
				resultEng = resultEng + arrChoSungEng[chosung] + arrJungSungEng[jungsung];
				if (jongsung != 0x0000) {
					/* A-3. 종성이 존재할경우 result에 담는다 */
					resultEng =  resultEng + arrJongSungEng[jongsung];
				}

			} else {
				/* B. 한글이 아니거나 자음만 있을경우 */

				/* 자음분리 */
				result = result + ((char)(chars + 0xAC00));
				
				/* 알파벳으로 */
				if( chars>=34097 && chars<=34126){
					/* 단일자음인 경우 */
					int jaum 	= (chars-34097);
					resultEng = resultEng + arrSingleJaumEng[jaum];
				} else if( chars>=34127 && chars<=34147) {
					/* 단일모음인 경우 */
					int moum 	= (chars-34127);
					resultEng = resultEng + arrJungSungEng[moum];
				} else {
					/* 알파벳인 경우 */
					resultEng = resultEng + ((char)(chars + 0xAC00));
				}
			}//if
			
		}//for

		return resultEng;
	}
}
