import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static List<AItem> items;

    public static void main(String[] args) throws Exception {
        List<AItem> items = new ArrayList<>();
        AItem aItem1 = new AItem("a", "b");
        AItem aItem2 = new AItem("e", "c");
        AItem aItem3 = new AItem("f", "g");
        items.add(aItem2);
        items.add(aItem1);
        items.add(aItem3);


        List<BItem> bItems = new ArrayList<>();
        BItem bItem1 = new BItem("a", "b");
        BItem bItem2 = new BItem("d", "c");
        bItems.add(bItem1);
        bItems.add(bItem2);

        List<AItem> result3 = subtract(items, bItems);
    
        System.out.println(result3);

        System.out.println( merge(items, bItems));
    }

    private static List<Object> mergeAnotherOne(List<AItem> items, List<BItem> bItems) {
        //서로 다른 AItem 과 BItem을 원본 AItem과 합친다 모두 합친다.
        Map<String, Object> mergedItemsMap = Stream.concat(
            items.stream().map(item -> new AbstractMap.SimpleEntry<>(item.getKey() + item.getName(), item)),
            bItems.stream().map(item -> new AbstractMap.SimpleEntry<>(item.getKey() + item.getName(), item)))
            .distinct()
            .collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, duplicate) -> existing)
            );
        
        return new ArrayList<>(mergedItemsMap.values());
    }

    private static List<AItem> merge(final List<AItem> src, List<BItem> dst) {
        //서로 다른 AItem 과 BItem을 원본 AItem과 합친다
        List<AItem> mergedItems = Stream.concat(
                src.stream(),
                dst.stream().map(item -> new AItem(item.getKey(), item.getName()))
            )
            .distinct()
            .collect(Collectors.toList());

        return mergedItems;
    }

    private static List<AItem> subtract(List<AItem> items, List<BItem> bItems) {
        List<AItem> result3 = new ArrayList<>(items);
        for (BItem bItem : bItems) {
            result3.removeIf(
                aItem -> aItem.getKey().equals(bItem.getKey()) && aItem.getName().equals(bItem.getName())
            );
        }
        return result3;
    }
}


class AItem {
    private String key;
    private String name;

    public AItem(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AItem [key=" + key + ", name=" + name + "]";
    }
}

class BItem {
    private String key;
    private String name;

    public BItem(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BItem [key=" + key + ", name=" + name + "]";
    }
}