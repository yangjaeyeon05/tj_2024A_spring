package example.day13;

import com.sun.source.tree.Tree;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Step2 {
    public static void main(String[] args) {

        // 1. 이진 트리
        TreeSet<Integer> scores = new TreeSet<>();
            // Set 인터페이스에는 Tree 자료구조의 메소드가 없다.

        // 2.
        scores.add(23);
        scores.add(10);
        scores.add(48);
        scores.add(15);
        scores.add(7);
        scores.add(22);
        scores.add(56);

        // 3.
        System.out.println("scores = " + scores);   // 오름차순 정렬

        // 4. 순회
        scores.forEach(score -> {
            System.out.println("score = " + score);
        });

        // 5. 트리 관련 메소드 제공
        System.out.println("가장 낮은 점수 , 가장 왼쪽 데이터 = " + scores.first());
        System.out.println("가장 높은 점수 , 가장 오른쪽 데이터 = " + scores.last());
        System.out.println("48보다 낮은 점수 = " + scores.lower(48));
        System.out.println("48보다 높은 점수 = " + scores.higher(48));
        System.out.println("48이거나 보다 낮은 점수 = " + scores.floor(48));
        System.out.println("48이거나 보다 높은 점수 = " + scores.ceiling(48));
        System.out.println("내림차순으로 반환 = " + scores.descendingSet());   // 내림차순 정렬
        System.out.println("48이상인 점수 = " + scores.tailSet(48 , true));  // true 이상
        System.out.println("48초과인 점수 = " + scores.tailSet(48 , false)); // false 초과
        System.out.println("48이하인 점수 = " + scores.headSet(48,true));    // 이하
        System.out.println("48미만인 점수 = " + scores.headSet(48,false));   // 미만
        System.out.println("10이상 48미만인 점수 = " + scores.subSet(10,true,48,false));

        // 6. TreeMap
        TreeMap<Integer , String > treeMap = new TreeMap<>();

        // 7.
        treeMap.put(23 , "apple");
        treeMap.put(10 , "forever");
        treeMap.put(48 , "ever");
        treeMap.put(15 , "base");
        treeMap.put(7 , "cherry");
        treeMap.put(22 , "guess");
        treeMap.put(56 , "zoo");
        System.out.println("treeMap = " + treeMap);

        // 8. 순회
        treeMap.entrySet().forEach(entry ->{
            System.out.println("entry = " + entry);
        });

        // 9.
        System.out.println("treeMap.firstEntry() = " + treeMap.firstEntry());
        System.out.println("treeMap.lastEntry() = " + treeMap.lastEntry());
        System.out.println("treeMap.lowerEntry(48) = " + treeMap.lowerEntry(48));
        System.out.println("treeMap.higherEntry(48) = " + treeMap.higherEntry(48));
        System.out.println("treeMap.floorEntry(48) = " + treeMap.floorEntry(48));
        System.out.println("treeMap.ceilingEntry(48) = " + treeMap.ceilingEntry(48));
        System.out.println("treeMap.descendingMap() = " + treeMap.descendingMap()); // 내림차순
        System.out.println("treeMap.tailMap(48,true) = " + treeMap.tailMap(48,true));
        System.out.println("treeMap.headMap(48,true) = " + treeMap.headMap(48,true));
        System.out.println("treeMap.subMap(10 , true , 48 , false) = " + treeMap.subMap(10 , true , 48 , false));


    }   // main end
}   // class end









