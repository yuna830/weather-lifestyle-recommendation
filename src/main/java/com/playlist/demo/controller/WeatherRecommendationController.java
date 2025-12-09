package com.playlist.demo.controller;

import com.playlist.demo.model.RecommendationItem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class WeatherRecommendationController {

    // weather: sunny, rainy, snowy, cold, cloudy, foggy, thunder
    // type: music, food, outfit, activity, place, perfume
    private final Map<String, Map<String, List<RecommendationItem>>> recommendations =
            Map.of(
                    "sunny", Map.of(
                            "music", List.of(
                                    new RecommendationItem("좋은 날", "아이유", "/images/sunny/iu.jpg"),
                                    new RecommendationItem("빨간맛", "Red Velvet", "/images/sunny/red.jpg"),
                                    new RecommendationItem("Happy Things", "제이레빗", "/images/sunny/happy.jpg")
                            ),
                            "food", List.of(
                                    new RecommendationItem("아이스 아메리카노", "시원한 카페인 한 잔", "/images/sunny/americano.jpg"),
                                    new RecommendationItem("샐러드", "가볍게 즐기는 한 끼", "/images/sunny/salad.jpg")
                            ),
                            "outfit", List.of(
                                    new RecommendationItem("얇은 셔츠", "가볍고 편안한 데일리 룩", "/images/sunny/shirts.jpg"),
                                    new RecommendationItem("청바지", "활동성 좋고 편안한 데일리 룩", "/images/sunny/jins.jpg"),
                                    new RecommendationItem("데님 스커트", "산책하기 좋은 캐주얼 룩", "/images/sunny/skirt.jpg")
                            ),
                            "activity", List.of(
                                    new RecommendationItem("공원 산책", "햇빛 받으면서 가볍게 걷기", "/images/sunny/park.jpg"),
                                    new RecommendationItem("자전거 타기", "강변 or 공원 코스 추천", "/images/sunny/bike.jpg")
                            ),
                            "place", List.of(
                                    new RecommendationItem("루프탑 카페", "야외 테이블에서 햇살 즐기기", "/images/sunny/rooftop.jpg"),
                                    new RecommendationItem("강변 산책로", "음악 들으면서 걸어보기", "/images/sunny/river.jpg")
                            ),
                            "perfume", List.of(
                                    new RecommendationItem("시트러스 계열 향수", "레몬·오렌지 같은 상큼한 향", "/images/sunny/citrus.jpg"),
                                    new RecommendationItem("화이트 머스크", "가볍고 산뜻한 데일리 향", "/images/sunny/musk.jpg")
                            )
                    ),

                    "rainy", Map.of(
                            "music", List.of(
                                    new RecommendationItem("우산", "에픽하이 (feat. 윤하)", "/images/rainy/epikhigh.jpg"),
                                    new RecommendationItem("비도 오고 그래서", "헤이즈", "/images/rainy/heize.jpg"), 
                                    new RecommendationItem("폴킴", "비", "/images/rainy/paulkim.jpg")
                            ),
                            "food", List.of(
                                    new RecommendationItem("파전 + 막걸리", "전형적인 비 오는 날 메뉴", "/images/rainy/pajeon.jpg"),
                                    new RecommendationItem("라면", "창가에서 빗소리 들으며 먹기", "/images/rainy/ramen.jpg")
                            ),
                            "outfit", List.of(
                                    new RecommendationItem("방수 점퍼", "우산과 함께 입기 좋은 아우터", "/images/rainy/jumper.jpg"),
                                    new RecommendationItem("롱 부츠", "바닥이 젖어도 편한 신발", "/images/rainy/boots.jpg")
                            ),
                            "activity", List.of(
                                    new RecommendationItem("카페에서 책 읽기", "창가 자리에서 빗소리 감상", "/images/rainy/book.jpg"),
                                    new RecommendationItem("집에서 영화 보기", "감성 영화 한 편", "/images/rainy/movie.jpg")
                            ),
                            "place", List.of(
                                    new RecommendationItem("조용한 카페", "따뜻한 음료와 함께 쉬기", "/images/rainy/cafe.jpg"),
                                    new RecommendationItem("영화관", "집중해서 영화 보기 좋은 날", "/images/rainy/cinema.jpg")
                            ),
                            "perfume", List.of(
                                    new RecommendationItem("우디 향수", "비 냄새와 잘 어울리는 잔잔한 향", "/images/rainy/woody.jpg"),
                                    new RecommendationItem("머스크 계열", "포근한 느낌의 실내용 향기", "/images/sunny/musk.jpg")
                            )
                    ),

                    "snowy", Map.of(
                            "music", List.of(
                                    new RecommendationItem("All I Want for Christmas Is You", "Mariah Carey", "/images/snowy/aiwfc.jpg"),
                                    new RecommendationItem("Ariana Grande", "Santa Tell Me", "/images/snowy/santatellme.jpg"),
                                    new RecommendationItem("첫 눈", "EXO", "/images/snowy/firstsnow.jpg")
                            ),
                            "food", List.of(
                                    new RecommendationItem("붕어빵", "추운 길거리에서 먹는 겨울 간식", "/images/snowy/fish.jpg"),
                                    new RecommendationItem("어묵 국물", "손 녹이기 딱 좋은 국물", "/images/snowy/odeng.jpg")
                            ),
                            "outfit", List.of(
                                    new RecommendationItem("롱 패딩", "눈 오는 날 필수템", "/images/snowy/padding.jpg"),
                                    new RecommendationItem("장갑", "손 보호 필수 아이템", "/images/snowy/hand.jpg"), 
                                    new RecommendationItem("목도리", "목 보호 필수 아이템", "/images/snowy/scarf.jpg")

                            ),
                            "activity", List.of(
                                    new RecommendationItem("눈 사진 찍기", "크리스마스 느낌 사진 남기기", "/images/snowy/photo.jpg"),
                                    new RecommendationItem("집에서 크리스마스 영화 보기", "담요 덮고 따뜻하게", "/images/snowy/home.jpg")
                            ),
                            "place", List.of(
                                    new RecommendationItem("트리 있는 광장", "겨울 분위기 만끽하기", "/images/snowy/square.jpg"),
                                    new RecommendationItem("베이커리 카페", "따뜻한 빵과 함께 쉬기", "/images/snowy/bakery.jpg")
                            ),
                            "perfume", List.of(
                                    new RecommendationItem("바닐라 계열", "달콤하고 따뜻한 겨울 향", "/images/snowy/vanilla.jpg"),
                                    new RecommendationItem("앰버 향수", "포근한 니트 느낌의 향기", "/images/snowy/amber.jpg")
                            )
                    ),

                    "cold", Map.of(
                            "music", List.of(
                                    new RecommendationItem("주저하는 연인들을 위해", "잔나비", "/images/cold/forlovers.jpg"),
                                    new RecommendationItem("우주를 줄게", "볼빨간사춘기", "/images/cold/galaxy.jpg"),
                                    new RecommendationItem("10cm", "스토커", "/images/cold/10cm.jpg")
                            ),
                            "food", List.of(
                                    new RecommendationItem("고구마", "구워 먹으면 더 맛있는 간식", "/images/cold/potato.jpg"),
                                    new RecommendationItem("호빵", "전자레인지에 살짝 데워서", "/images/cold/hoppang.jpg")
                            ),
                            "outfit", List.of(
                                    new RecommendationItem("코트", "바람 막아주는 따뜻한 조합", "/images/cold/coat.jpg"),
                                    new RecommendationItem("목도리", "목 보호 필수 아이템", "/images/snowy/scarf.jpg"),
                                    new RecommendationItem("니트 스웨터", "실내·실외 모두 활용 가능", "/images/cold/knit.jpg")
                            ),
                            "activity", List.of(
                                    new RecommendationItem("홈카페 만들기", "따뜻한 라떼 한 잔", "/images/cold/latte.jpg"),
                                    new RecommendationItem("실내 운동", "러닝머신, 요가 등", "/images/cold/indoor.jpg")
                            ),
                            "place", List.of(
                                    new RecommendationItem("실내 쇼핑몰", "추위 피하며 돌아다니기", "/images/cold/mall.jpg"),
                                    new RecommendationItem("작은 공연장", "인디 공연 즐기기", "/images/cold/stage.jpg")
                            ),
                            "perfume", List.of(
                                    new RecommendationItem("스파이시 향수", "살짝 매운 향으로 따뜻한 느낌", "/images/cold/spicy.jpg"),
                                    new RecommendationItem("오리엔탈 계열", "깊고 풍부한 겨울 향", "/images/cold/oriental.jpg")
                            )
                    ),

                    "cloudy", Map.of(
                            "music", List.of(
                                    new RecommendationItem("Cloud", "윤하", "/images/cloudy/younha.jpg"),
                                    new RecommendationItem("라일락", "아이유", "/images/cloudy/lilac.jpg")
                            ),
                            "food", List.of(
                                    new RecommendationItem("파스타", "잔잔한 날 편하게 먹는 한 끼", "/images/cloudy/pasta.jpg"),
                                    new RecommendationItem("카페라테", "구름 낀 하늘 보며 마시기", "/images/cloudy/cafe.jpg")
                            ),
                            "outfit", List.of(
                                    new RecommendationItem("카디건", "실내에서 가볍게 걸치기", "/images/cloudy/cardigan.jpg"),
                                    new RecommendationItem("롱 스커트", "흐린 날 감성 룩", "/images/cloudy/skirt.jpg")
                            ),
                            "activity", List.of(
                                    new RecommendationItem("전시회 관람", "흐린 날 감성 충전", "/images/cloudy/gallery.jpg"),
                                    new RecommendationItem("카페에서 노트 정리", "생각 정리하기 좋은 날", "/images/cloudy/notebook.jpg")
                            ),
                            "place", List.of(
                                    new RecommendationItem("미술관", "조용히 작품 감상", "/images/cloudy/museum.jpg"),
                                    new RecommendationItem("북카페", "책 읽기 좋은 공간", "/images/cloudy/bookcafe.jpg")
                            ),
                            "perfume", List.of(
                                    new RecommendationItem("플로럴 향수", "부드럽고 은은한 꽃 향기", "/images/cloudy/floral.jpg"),
                                    new RecommendationItem("파우더리 향수", "부드러운 구름 느낌의 향", "/images/cloudy/powdery.jpg")
                            )
                    ),

                    "foggy", Map.of(
                            "music", List.of(
                                    new RecommendationItem("Love Poem", "아이유", "/images/foggy/lovepoem.jpg"),
                                    new RecommendationItem("밤편지", "아이유", "/images/foggy/letter.jpg")
                            ),
                            "food", List.of(
                                    new RecommendationItem("따뜻한 홍차", "안개 낀 날에 잘 어울리는 티", "/images/foggy/tea.jpg"),
                                    new RecommendationItem("크림 스튜", "부드럽고 포근한 맛", "/images/foggy/stew.jpg")
                            ),
                            "outfit", List.of(
                                    new RecommendationItem("트렌치코트", "안개 낀 날 감성 룩", "/images/foggy/trench.jpg"),
                                    new RecommendationItem("머플러", "목을 따뜻하게 감싸기", "/images/foggy/scarf.jpg")
                            ),
                            "activity", List.of(
                                    new RecommendationItem("도시 야경 산책", "안개 낀 도시 불빛 보기", "/images/foggy/walk.jpg"),
                                    new RecommendationItem("카페에서 글쓰기", "생각 정리하기 좋은 분위기", "/images/foggy/write.jpg")
                            ),
                            "place", List.of(
                                    new RecommendationItem("전망대 카페", "안개 낀 뷰 감상", "/images/foggy/viewcafe.jpg"),
                                    new RecommendationItem("조용한 골목 카페", "잔잔한 분위기 느끼기", "/images/foggy/alleycafe.jpg")
                            ),
                            "perfume", List.of(
                                    new RecommendationItem("그린 노트 향수", "이끼, 풀잎 느낌의 촉촉한 향", "/images/foggy/green.jpg"),
                                    new RecommendationItem("머스크 + 플로럴", "은은한 잔향이 오래 남는 향", "/images/foggy/mf.jpg")
                            )
                    ),

                    "thunder", Map.of(
                            "music", List.of(
                                    new RecommendationItem("Thunder", "Imagine Dragons", "/images/thunder/thunder.jpg"),
                                    new RecommendationItem("Monster", "EXO", "/images/thunder/exo.jpg")
                            ),
                            "food", List.of(
                                    new RecommendationItem("치킨", "천둥 번개엔 치킨과 탄산", "/images/thunder/chicken.jpg"),
                                    new RecommendationItem("피자", "집에서 먹는 파티 음식", "/images/thunder/pizza.jpg")
                            ),
                            "outfit", List.of(
                                    new RecommendationItem("편한 후드티", "집콕에 어울리는 간편 룩", "/images/thunder/hoodie.jpg"),
                                    new RecommendationItem("트레이닝 팬츠", "소파에 누워도 편한 바지", "/images/thunder/training.jpg")
                            ),
                            "activity", List.of(
                                    new RecommendationItem("게임하기", "번개 소리 들으며 몰입하기", "/images/thunder/game.jpg"),
                                    new RecommendationItem("넷플릭스 몰아보기", "폭풍우 날엔 정주행", "/images/thunder/netflix.jpg")
                            ),
                            "place", List.of(
                                    new RecommendationItem("집", "안전이 가장 중요합니다", "/images/thunder/home.jpg"),
                                    new RecommendationItem("실내 놀이터", "밖에 나가야 한다면 실내로", "/images/thunder/indoor.jpg")
                            ),
                            "perfume", List.of(
                                    new RecommendationItem("스모키 향수", "강렬하고 개성 있는 향", "/images/thunder/smoky.jpg"),
                                    new RecommendationItem("레더 계열", "다크하고 시크한 분위기", "/images/thunder/leather.jpg")
                            )
                    )
            );

    @GetMapping("/api/weather-recommend")
    public List<RecommendationItem> recommend(
            @RequestParam String weather,
            @RequestParam String type
    ) {
        String w = weather.toLowerCase();
        String t = type.toLowerCase();

        // 기본값: sunny
        Map<String, List<RecommendationItem>> byType =
                recommendations.getOrDefault(w, recommendations.get("sunny"));

        if (byType == null) {
            return List.of();
        }

        return byType.getOrDefault(t, List.of());
    }
}
