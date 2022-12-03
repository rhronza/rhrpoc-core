package cz.hronza.rhrpoc.core.api.dto;

import java.time.LocalDate;

public class SellerDto {
    private String name;
    private String address;
    private LocalDate birthday;

    public SellerDto() {
    }

    public String getName() {
        return name;
    }

    public SellerDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SellerDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public SellerDto setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SellerDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append('}');
        return sb.toString();
    }
}
