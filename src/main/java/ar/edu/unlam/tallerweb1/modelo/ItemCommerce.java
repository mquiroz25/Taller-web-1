package ar.edu.unlam.tallerweb1.modelo;

public interface ItemCommerce {

    public Long getId();

    public void setId(Long id);

    public Item getItem();

    public void setItem(Item item);

    public Commerce getCommerce();

    public void setCommerce(Commerce commerce);

    public Double getPrice();

    public void setPrice(Double price);

    public Integer getStock();

    public void setStock(Integer stock);
}
