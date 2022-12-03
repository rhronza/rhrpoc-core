package cz.hronza.rhrpoc.core.api.dto;

public class SoldProductDto {
    private String productName;
    private String producer;
    private ProductType productType;

    public SoldProductDto() {
    }

    public String getProductName() {
        return productName;
    }

    public SoldProductDto setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public SoldProductDto setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public ProductType getProductType() {
        return productType;
    }

    public SoldProductDto setProductType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SoldProductDto{");
        sb.append("productName='").append(productName).append('\'');
        sb.append(", producer='").append(producer).append('\'');
        sb.append(", productType=").append(productType);
        sb.append('}');
        return sb.toString();
    }
}
