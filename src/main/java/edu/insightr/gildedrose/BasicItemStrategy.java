package edu.insightr.gildedrose;

public class BasicItemStrategy implements UpdateQualityStrategy {
    @Override
    public void updateQuality(Item item) {
        if( item.getQuality() > 0){
            item.setQuality(item.getQuality() - 1);
        }
        if ( item.getQuality() > 0
                && item.getSellIn() <= 0){
            item.setQuality(item.getQuality() -1);
        }
        item.setSellIn(item.getSellIn() -1);
    }
}
