package cz.hronza.rhrpoc.core.api.dto;

import java.util.List;

public class SellerAndSoldProductsDto {
    private SellerDto sellerDto;
    private List<SoldProductDto> soldProduct;

    public SellerAndSoldProductsDto() {
    }

    public SellerDto getSellerDto() {
        return sellerDto;
    }

    public SellerAndSoldProductsDto setSellerDto(SellerDto sellerDto) {
        this.sellerDto = sellerDto;
        return this;
    }

    public List<SoldProductDto> getSoldProduct() {
        return soldProduct;
    }

    public SellerAndSoldProductsDto setSoldProduct(List<SoldProductDto> soldProduct) {
        this.soldProduct = soldProduct;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SellerAndSoldProductsDto{");
        sb.append("sellerDto=").append(sellerDto);
        sb.append(", soldProduct=").append(soldProduct);
        sb.append('}');
        return sb.toString();
    }
}
