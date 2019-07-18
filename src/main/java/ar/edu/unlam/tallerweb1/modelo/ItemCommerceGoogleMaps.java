package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.modelo.ItemCommerceDecorator;

import java.util.Dictionary;


public class ItemCommerceGoogleMaps extends ItemCommerceDecorator {

    private String urlPinImage;

    public ItemCommerceGoogleMaps(ItemCommerce itemCommerce) {
        super(itemCommerce);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public Item getItem() {
        return super.getItem();
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
    }

    @Override
    public Commerce getCommerce() {
        return super.getCommerce();
    }

    @Override
    public void setCommerce(Commerce commerce) {
        super.setCommerce(commerce);
    }

    @Override
    public Double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(Double price) {
        super.setPrice(price);
    }

    @Override
    public Integer getStock() {
        return super.getStock();
    }

    @Override
    public void setStock(Integer stock) {
        super.setStock(stock);
    }


    public String getUrlPinImage(){
        return this.urlPinImage;
    }

    public void setUrlPinImage(String urlPinImage) {
        this.urlPinImage = urlPinImage;
    }
}
