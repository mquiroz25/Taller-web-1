package ar.edu.unlam.tallerweb1.modelo;

public abstract class ItemCommerceDecorator implements ItemCommerce {
    private final ItemCommerce itemCommerceDecorated;

    public ItemCommerceDecorator(ItemCommerce itemCommerce){
        this.itemCommerceDecorated = itemCommerce;
    }

    @Override
    public Long getId() {
        return itemCommerceDecorated.getId();
    }

    @Override
    public void setId(Long id) {
        itemCommerceDecorated.setId((id));
    }

    @Override
    public Item getItem() {
        return itemCommerceDecorated.getItem();
    }

    @Override
    public void setItem(Item item) {
        itemCommerceDecorated.setItem(item);
    }

    @Override
    public Commerce getCommerce() {
        return itemCommerceDecorated.getCommerce();
    }

    @Override
    public void setCommerce(Commerce commerce) {
        itemCommerceDecorated.setCommerce(commerce);
    }

    @Override
    public Double getPrice() {
        return itemCommerceDecorated.getPrice();
    }

    @Override
    public void setPrice(Double price) {
        itemCommerceDecorated.setPrice(price);
    }

    @Override
    public Integer getStock() {
        return itemCommerceDecorated.getStock();
    }

    @Override
    public void setStock(Integer stock) {
        itemCommerceDecorated.setStock(stock);
    }
}
